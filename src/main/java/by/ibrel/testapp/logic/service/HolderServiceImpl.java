package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.service.impl.HolderService;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class HolderServiceImpl extends AbstractService<Holder> implements HolderService{

    private final HolderDao holderDao;

    public HolderServiceImpl(HolderDao holderDao) {
        super((AbstractDao<Holder>) holderDao);
        this.holderDao = holderDao;
    }
}
