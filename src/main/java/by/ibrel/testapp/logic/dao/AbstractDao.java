package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CrudDao;

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
public abstract class AbstractDao<T> implements CrudDao<T>{

    private final Class<T> clazz;
    private final ConnectionFactory connectionFactory;

    public AbstractDao(Class<T> clazz, ConnectionFactory connectionFactory) {
        this.clazz = clazz;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public T getOne(int id) {

        final String SQL = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id=" + id;

        Connection connection = connectionFactory.getConnection();

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

        Connection connection = connectionFactory.getConnection();

        try (Statement stmt = connection.createStatement()){

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

        Connection connection = connectionFactory.getConnection();

        try (Statement stmt = connection.createStatement()){

            int i = stmt.executeUpdate(SQL_DELETE);
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    abstract T extractEntityFromResultSet(ResultSet rs) throws SQLException;
}
