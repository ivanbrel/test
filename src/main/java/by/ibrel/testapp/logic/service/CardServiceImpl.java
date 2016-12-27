package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.service.impl.CardService;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class CardServiceImpl extends AbstractService<Card> implements CardService {

    private final CardDao cardDao;

    public CardServiceImpl(CardDao cardDao) {
        super((AbstractDao<Card>) cardDao);
        this.cardDao = cardDao;
    }
}
