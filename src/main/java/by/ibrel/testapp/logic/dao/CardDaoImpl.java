package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.model.Card;

import java.sql.*;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
public class CardDaoImpl extends AbstractDao<Card> implements CardDao {

    private final ConnectionFactory connectionFactory;

    public CardDaoImpl(final ConnectionFactory connectionFactory) {
        super(Card.class, connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public boolean insert(final Card card) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_INSERT_CARD = "INSERT INTO card VALUES (NULL, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_CARD)) {
            ps.setInt(1, card.getNumberCard());
            ps.setDate(2, new java.sql.Date(card.getValidity().getTime()));
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
    public boolean update(final Card card) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_UPDATE_CARD = "UPDATE card SET numbercard=?, validity=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_CARD)){

            ps.setInt(1, card.getNumberCard());
            ps.setDate(2, (Date) card.getValidity());
            ps.setInt(3, card.getId());
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
    Card extractEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Card(rs.getInt("id"), rs.getInt("numbercard"), rs.getDate("validity"));
    }
}
