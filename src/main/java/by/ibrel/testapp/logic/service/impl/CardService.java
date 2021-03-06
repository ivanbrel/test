package by.ibrel.testapp.logic.service.impl;

import by.ibrel.testapp.logic.model.Card;

import java.util.Date;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public interface CardService extends CommonService<Card>{

    Card createCard(int numberCard, String validityHolderCard);
}
