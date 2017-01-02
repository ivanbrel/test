package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.bean.Commission;
import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;
import by.ibrel.testapp.logic.service.impl.TransactionService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class TransactionServiceImpl extends AbstractService<Transaction> implements TransactionService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final TransactionDao transactionDao;

    public TransactionServiceImpl(TransactionDao transactionDao) {
        super((AbstractDao<Transaction>) transactionDao);
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction createTransaction(Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount){
        Transaction transaction = new Transaction(sender, recipient, commission, transferAmount);
        save(transaction);

        logger.info("Create transaction " + transaction.toString());
        return transaction;
    }
}
