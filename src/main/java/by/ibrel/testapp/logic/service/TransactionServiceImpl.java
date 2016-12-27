package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Transaction;
import by.ibrel.testapp.logic.service.impl.TransactionService;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class TransactionServiceImpl extends AbstractService<Transaction> implements TransactionService {

    private final TransactionDao transactionDao;

    public TransactionServiceImpl(TransactionDao transactionDao) {
        super((AbstractDao<Transaction>) transactionDao);
        this.transactionDao = transactionDao;
    }
}
