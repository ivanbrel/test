package by.ibrel.testapp;

import by.ibrel.testapp.logic.bean.Commission;
import by.ibrel.testapp.logic.dao.CardDaoImpl;
import by.ibrel.testapp.logic.dao.ConnectionFactory;
import by.ibrel.testapp.logic.dao.HolderDaoImpl;
import by.ibrel.testapp.logic.dao.TransactionDaoImpl;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


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
    public void daoTest() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        CardDao cardDao = new CardDaoImpl(connectionFactory);
        HolderDao holderDao = new HolderDaoImpl(connectionFactory,cardDao);
        TransactionDao transactionDao = new TransactionDaoImpl(connectionFactory,holderDao,cardDao);

        Card card = new Card(1234567,"2017-01-02");
        cardDao.insert(card);
        cardDao.getOne(card.getId());
        cardDao.getAll();
        cardDao.delete(card.getId());

        Holder holder = new Holder("Kolya", card);
        holderDao.insert(holder);
        holderDao.getAll();
        holderDao.getOne(holder.getId());
        holderDao.delete(holder.getId());

        // settings test
        LoadSettings.getInstance().saveSettings();
        Commission commission = LoadSettings.getInstance().getSettings();

        //transaction test
        Transaction transaction = new Transaction(holder,holder,commission,BigDecimal.TEN);
        transactionDao.insert(transaction);
        transactionDao.getAll();
        transactionDao.getOne(transaction.getId());
        transactionDao.delete(transaction.getId());
    }

}
