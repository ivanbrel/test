package by.ibrel.testapp.web.controller;

import by.ibrel.testapp.LoadSettings;
import by.ibrel.testapp.logic.bean.Commission;
import by.ibrel.testapp.logic.dao.CardDaoImpl;
import by.ibrel.testapp.logic.dao.ConnectionFactory;
import by.ibrel.testapp.logic.dao.HolderDaoImpl;
import by.ibrel.testapp.logic.dao.TransactionDaoImpl;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;
import by.ibrel.testapp.logic.service.CardServiceImpl;
import by.ibrel.testapp.logic.service.HolderServiceImpl;
import by.ibrel.testapp.logic.service.TransactionServiceImpl;
import by.ibrel.testapp.logic.service.impl.CardService;
import by.ibrel.testapp.logic.service.impl.HolderService;
import by.ibrel.testapp.logic.service.impl.TransactionService;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (29.12.2016)
 * @datechange (29.12.2016)
 */
public class TransactionController extends HttpServlet{

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final CardService cardService;
    private final HolderService holderService;
    private final TransactionService transactionService;
    private final LoadSettings loadSettings;

    public TransactionController() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.cardService = new CardServiceImpl(new CardDaoImpl(connectionFactory));
        this.holderService = new HolderServiceImpl(new HolderDaoImpl(connectionFactory, new CardDaoImpl(connectionFactory)));
        this.transactionService = new TransactionServiceImpl(new TransactionDaoImpl(connectionFactory, new HolderDaoImpl(connectionFactory, new CardDaoImpl(connectionFactory)), new CardDaoImpl(connectionFactory)));
        this.loadSettings = LoadSettings.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer numberCardHolder = Integer.valueOf(request.getParameter("numberCard"));
        String holderName = request.getParameter("holdersName");
        String validityHolderCard = request.getParameter("validity");

        Integer numberCardRecipient = Integer.valueOf(request.getParameter("numberCardRecipient"));
        String nameRecipient = request.getParameter("nameRecipient");
        String recipientValidity = request.getParameter("recipientValidity");

        String currency = request.getParameter("currency");
        String amount = request.getParameter("amount");

        logger.info("получили данные");
        // Про валидацию ничего не сказано , я её и не буду делать , допустим пользователь ввёл коРэтные данные

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();

        Holder sender = creteHolder(holderName, createCard(numberCardHolder, convertStringToDate(validityHolderCard)));

        Holder recipient = creteHolder(nameRecipient, createCard(numberCardRecipient, convertStringToDate(recipientValidity)));

        BigDecimal bigDecimal = new BigDecimal(amount);

        Transaction transaction = createTransaction(sender, recipient, getCommission(currency), bigDecimal);

        JsonElement transactionObj = gson.toJsonTree(transaction);

        if(transaction != null)
            myObj.addProperty("success", false);

        myObj.add("transaction", transactionObj);
        out.println(myObj);

        out.close();
    }

    private Card createCard(Integer numberCard, Date validityHolderCard){
        Card card = new Card(numberCard, validityHolderCard);
        cardService.save(card);

        logger.info("создали карту " + card.toString());
        return card;
    }

    private Holder creteHolder(String name, Card card){
        Holder holder = new Holder(name, card);
        holderService.save(holder);

        logger.info("создали черкана " + holder.toString());
        return holder;
    }

    private Transaction createTransaction(Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount){
        Transaction transaction = new Transaction(sender, recipient, commission, transferAmount);
        transactionService.save(transaction);

        logger.info("Транзакция создана " + transaction.toString());
        return transaction;
    }

    private Date convertStringToDate(String date){

        DateFormat df = new SimpleDateFormat("yyyy-mm-DD");

        Date startDate = null;

        try {
            startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        logger.info("Канвертнули дату " + date);
        return startDate;
    }

    private Commission getCommission(String currency){

        Commission commission = loadSettings.getSettings();

        logger.info("коммисия " + commission.toString());
        return commission;
    }
}
