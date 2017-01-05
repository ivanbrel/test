package by.ibrel.testapp.logic.service.impl;

import java.util.Set;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public interface CommonService<T> {

    T getOne(int id);

    Set<T> getAll();

    void delete(int id);

    void update(T t);

    T save(T t);
}
