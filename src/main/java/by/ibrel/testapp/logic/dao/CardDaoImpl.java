package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.model.Card;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.*;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */

@Transactional
public class CardDaoImpl extends AbstractDao<Card> implements CardDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    public CardDaoImpl() {
        super(Card.class);
    }

    @Override
    public Card insert(Card card) {

        logger.debug("Try insert card " + card.toString());

        final String SQL_INSERT_CARD = "INSERT INTO card VALUES (NULL, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_INSERT_CARD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, card.getNumberCard());
            ps.setDate(2, new java.sql.Date(card.getValidity().getTime()));

            int affectedRows  = ps.executeUpdate();
            if(affectedRows  == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        logger.debug("Successfully insert card " + card.toString());
        return card;
    }

    @Override
    public boolean update(Card card) {

        final String SQL_UPDATE_CARD = "UPDATE card SET numbercard=?, validity=? WHERE id=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_UPDATE_CARD)){

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
        return new Card(rs.getInt("id"), rs.getInt("numbercard"), rs.getString("validity"));
    }
}
