package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.service.impl.CardService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */

@Transactional
public class CardServiceImpl extends AbstractService<Card> implements CardService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final CardDao cardDao;

    public CardServiceImpl(CardDao cardDao) {
        super((AbstractDao<Card>) cardDao);
        this.cardDao = cardDao;
    }

    @Override
    public Card createCard(int numberCard, String validityHolderCard) {
        return save(new Card(numberCard, validityHolderCard));
    }
}
