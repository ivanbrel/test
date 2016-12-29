package by.ibrel.testapp;

import by.ibrel.testapp.logic.dao.CardDaoImpl;
import by.ibrel.testapp.logic.dao.ConnectionFactory;
import by.ibrel.testapp.logic.dao.HolderDaoImpl;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.service.HolderServiceImpl;
import by.ibrel.testapp.logic.service.impl.HolderService;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDao {

    protected Server server;
    private Main main;

    @Before
    public void aJettyServer() throws Exception {
        main = new Main(8080);
        main.start();
    }

    @After
    public void stopServer() throws Exception {
        main.stop();
    }

    @Test
    public void holderDaoTest() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        CardDao cardDao = new CardDaoImpl(connectionFactory);

        HolderDao holderDao = new HolderDaoImpl(connectionFactory,cardDao);

        HolderService holderService = new HolderServiceImpl(holderDao);

        holderService.getAll().forEach(holder -> System.out.println(holder.toString()));

        Card card = new Card(123,convertStringToDate("1993-05-15"));

        cardDao.insert(card);

        Holder holder = new Holder("Kolya", card);

        holderDao.insert(holder);
    }

    private Date convertStringToDate(String date){

        DateFormat df = new SimpleDateFormat("yyyy-mm-DD");

        Date startDate = null;

        try {
            startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

}
