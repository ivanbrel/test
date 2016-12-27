package by.ibrel.testapp.logic.dao.impl;

import java.util.Set;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public interface CrudDao<T> {

    T getOne(int id);

    Set<T> getAll();

    boolean insert(T t);

    boolean update(T t);

    boolean delete(int id);
}
