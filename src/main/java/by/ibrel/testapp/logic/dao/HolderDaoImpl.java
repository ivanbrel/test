package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class HolderDaoImpl extends AbstractDao<Holder> implements HolderDao {

    private final ConnectionFactory connectionFactory;
    private final CardDao cardDao;

    public HolderDaoImpl(final ConnectionFactory connectionFactory, final CardDao cardDao) {
        super(Holder.class, connectionFactory);
        this.connectionFactory = connectionFactory;
        this.cardDao = cardDao;
    }

    @Override
    public boolean insert(final Holder holder) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_INSERT_HOLDER = "INSERT INTO holder VALUES (NULL, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_HOLDER)) {
            ps.setString(1, holder.getName());
            ps.setInt(2, holder.getCard().getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(final Holder holder) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_UPDATE_HOLDER = "UPDATE holder SET name=?, card=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_HOLDER)){

            ps.setString(1, holder.getName());
            ps.setInt(2, holder.getCard().getId());
            ps.setInt(3, holder.getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    Holder extractEntityFromResultSet(ResultSet rs) throws SQLException {

        final Card card = cardDao.getOne(rs.getInt("card"));

        return new Holder(rs.getInt("id"), rs.getString("name"), card);
    }
}
