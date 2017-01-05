package by.ibrel.testapp;

import by.ibrel.testapp.logic.model.Commission;
import by.ibrel.testapp.logic.dao.CardDaoImpl;
import by.ibrel.testapp.logic.dao.HolderDaoImpl;
import by.ibrel.testapp.logic.dao.TransactionDaoImpl;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;
import ch.qos.logback.classic.Logger;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class TestDao {

    protected Server server;
    private Main main;
    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

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
    public void daoTest() throws Exception {

        CardDao cardDao = new CardDaoImpl();
        HolderDao holderDao = new HolderDaoImpl(cardDao);
        TransactionDao transactionDao = new TransactionDaoImpl(holderDao,cardDao);

        Card card = new Card(1234567,"2017-01-02");
        cardDao.insert(card);

        cardDao.getOne(card.getId());
        cardDao.getAll();
//        cardDao.delete(card.getId());

        Holder holder = new Holder("Kolya", card);
        holderDao.insert(holder);
        holderDao.getAll();
        holderDao.getOne(holder.getId());
//        holderDao.delete(holder.getId());

        // settings test
        LoadSettings.getInstance().saveSettings();
        Commission commission = LoadSettings.getInstance().getSettings().get(1);

        //transaction test
        Transaction transaction = new Transaction(holder,holder,commission,BigDecimal.TEN);
        transactionDao.insert(transaction);
        transactionDao.getAll();
        transactionDao.getOne(transaction.getId());
//        transactionDao.delete(transaction.getId());
    }

}
