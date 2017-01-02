package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.service.impl.HolderService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class HolderServiceImpl extends AbstractService<Holder> implements HolderService{

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final HolderDao holderDao;

    public HolderServiceImpl(HolderDao holderDao) {
        super((AbstractDao<Holder>) holderDao);
        this.holderDao = holderDao;
    }

    @Override
    public Holder creteHolder(String name, Card card){
        Holder holder = new Holder(name, card);
        save(holder);

        logger.info("Create holder " + holder.toString());
        return holder;
    }
}
