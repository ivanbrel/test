package by.ibrel.testapp.logic.service.impl;

import by.ibrel.testapp.logic.bean.Commission;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;

import java.math.BigDecimal;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public interface TransactionService extends CommonService<Transaction> {

    Transaction createTransaction(Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount);
}
