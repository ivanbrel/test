package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CrudDao;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */

@Transactional
public abstract class AbstractDao<T> implements CrudDao<T>{

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getOne(int id) {

        final String SQL = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id=" + id;

        Connection connection = ConnectionFactory.getConnection();

        try (Statement stmt = connection.createStatement()){

            ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next())
            {
                return extractEntityFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<T> getAll() {

        final String SQL_GET_ALL = "SELECT * FROM " + clazz.getSimpleName();

        try (Statement stmt = ConnectionFactory.getConnection().createStatement()){

            ResultSet rs = stmt.executeQuery(SQL_GET_ALL);
            Set hashSet = new HashSet();

            while(rs.next())
            {
                T t = extractEntityFromResultSet(rs);
                hashSet.add(t);
            }

            return hashSet;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {

        final String SQL_DELETE = "DELETE FROM " + clazz.getSimpleName() + " WHERE id="  + id;

        try (Statement stmt = ConnectionFactory.getConnection().createStatement()){

            int i = stmt.executeUpdate(SQL_DELETE);
            if(i == 1) {

                logger.debug("Successfully delete row with ID - " + id + "(" + clazz.getSimpleName() +")");

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    abstract T extractEntityFromResultSet(ResultSet rs) throws SQLException;
}
