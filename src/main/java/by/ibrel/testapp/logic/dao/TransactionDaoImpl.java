package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.LoadSettings;
import by.ibrel.testapp.logic.model.Commission;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
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

@Transactional
public class TransactionDaoImpl extends AbstractDao<Transaction> implements TransactionDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private final HolderDao holderDao;
    private final CardDao cardDao;

    public TransactionDaoImpl(HolderDao holderDao, CardDao cardDao) {
        super(Transaction.class);
        this.holderDao = holderDao;
        this.cardDao = cardDao;
    }

    @Override
    public Transaction insert(final Transaction transaction) {

        logger.debug("Try insert transaction - " + transaction.toString());

        final String SQL_INSERT_TRANSACTION = "INSERT INTO TRANSACTION VALUES (NULL, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_INSERT_TRANSACTION)) {

            ps.setInt(1, transaction.getSender().getId());
            ps.setInt(2, transaction.getRecipient().getId());
            ps.setBigDecimal(3,transaction.getTransferAmount());
            int i = ps.executeUpdate();
            int affectedRows  = ps.executeUpdate();

            if(affectedRows  == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        logger.debug("Successfully insert transaction - " + transaction.toString());

        return transaction;
    }

    @Override
    public boolean update(final Transaction transaction) {

        final String SQL_UPDATE_TRANSACTION = "UPDATE TRANSACTION SET SENDER=?, RECIPIENT=?, TRANSFERAMOUNT=?  WHERE ID=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(SQL_UPDATE_TRANSACTION)){

            ps.setInt(1, transaction.getSender().getId());
            ps.setInt(2, transaction.getRecipient().getId());
            ps.setBigDecimal(3, transaction.getTransferAmount());
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
    Transaction extractEntityFromResultSet(ResultSet rs) throws SQLException {

        Commission c = null;

        LoadSettings.getInstance().getSettings().forEach(commission -> commission = c);

        final Holder sender = holderDao.getOne(rs.getInt("SENDER"));
        final Holder recipient = holderDao.getOne(rs.getInt("RECIPIENT"));
        return new Transaction(rs.getInt("ID"), sender, recipient, c, rs.getBigDecimal("TRANSFERAMOUNT"));

    }
}
