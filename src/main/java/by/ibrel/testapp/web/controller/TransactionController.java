package by.ibrel.testapp.web.controller;

import by.ibrel.testapp.LoadSettings;
import by.ibrel.testapp.logic.model.Commission;
import by.ibrel.testapp.logic.bean.TransactionDto;
import by.ibrel.testapp.logic.dao.CardDaoImpl;
import by.ibrel.testapp.logic.dao.HolderDaoImpl;
import by.ibrel.testapp.logic.dao.TransactionDaoImpl;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
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
import com.google.gson.JsonObject;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

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
        CardDao cardDao = new CardDaoImpl();
        HolderDao holderDao = new HolderDaoImpl(cardDao);
        TransactionDao transactionDao = new TransactionDaoImpl(holderDao,cardDao);
        this.cardService = new CardServiceImpl(cardDao);
        this.holderService = new HolderServiceImpl(holderDao);
        this.transactionService = new TransactionServiceImpl(transactionDao);
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

        logger.info("Get data");

        PrintWriter out = response.getWriter();

        final Holder sender = holderService.creteHolder(holderName,
                cardService.createCard(numberCardHolder, validityHolderCard));
        final Holder recipient = holderService.creteHolder(nameRecipient,
                cardService.createCard(numberCardRecipient, recipientValidity));
        final Transaction transaction = transactionService.createTransaction(sender, recipient, getCommission(currency), new BigDecimal(amount));

        JsonObject myObj = new JsonObject();

        if(transaction != null)
            myObj.addProperty("success", true);

        myObj.add("resp", new Gson().toJsonTree(new TransactionDto(transaction)));

        out.println(myObj);
        out.close();
    }

    private Commission getCommission(String currency){
        final Commission[] commission = {null};

        loadSettings.getSettings().forEach(c -> {
            if (c.getCurrency().getName().equalsIgnoreCase(currency)){
                 commission[0] = c;
            }
        });

        logger.info("Commission " + commission[0].toString());
        return commission[0];
    }
}
