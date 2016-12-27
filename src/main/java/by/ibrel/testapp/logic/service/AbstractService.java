package by.ibrel.testapp.logic.service;

import by.ibrel.testapp.logic.dao.AbstractDao;
import by.ibrel.testapp.logic.service.impl.CommonService;

import java.util.Set;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class AbstractService<T> implements CommonService<T> {

    private AbstractDao<T> abstractDao;

    public AbstractService(AbstractDao<T> abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public T getOne(int id) {
        return abstractDao.getOne(id);
    }

    @Override
    public Set<T> getAll() {
        return abstractDao.getAll();
    }

    @Override
    public void delete(int id) {
        abstractDao.delete(id);
    }

    @Override
    public void update(T t) {
        abstractDao.update(t);
    }

    @Override
    public void save(T t) {
        abstractDao.insert(t);
    }
}
