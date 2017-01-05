package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.model.Card;
import by.ibrel.testapp.logic.model.Holder;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */

@Transactional
public class HolderDaoImpl extends AbstractDao<Holder> implements HolderDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final CardDao cardDao;

    public HolderDaoImpl(final CardDao cardDao) {
        super(Holder.class);
        this.cardDao = cardDao;
    }

    @Override
    public Holder insert(Holder holder) {

        logger.debug("Try insert holder - " + holder.toString());

        final String SQL_INSERT_HOLDER = "INSERT INTO holder VALUES (NULL, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_INSERT_HOLDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, holder.getName());
            ps.setInt(2, holder.getCard().getId());

            int affectedRows  = ps.executeUpdate();

            if(affectedRows  == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    holder.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        logger.debug("Successfully insert holder - " + holder.toString());

        return holder;
    }

    @Override
    public boolean update(Holder holder) {

        final String SQL_UPDATE_HOLDER = "UPDATE holder SET name=?, card_id=? WHERE id=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_UPDATE_HOLDER)){

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

        final Card card = cardDao.getOne(rs.getInt("card_id"));

        return new Holder(rs.getInt("id"), rs.getString("name"), card);
    }
}
