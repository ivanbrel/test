package by.ibrel.testapp.logic.dao;

import by.ibrel.testapp.LoadSettings;
import by.ibrel.testapp.logic.dao.impl.CardDao;
import by.ibrel.testapp.logic.dao.impl.HolderDao;
import by.ibrel.testapp.logic.dao.impl.TransactionDao;
import by.ibrel.testapp.logic.model.Holder;
import by.ibrel.testapp.logic.model.Transaction;

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
public class TransactionDaoImpl extends AbstractDao<Transaction> implements TransactionDao {

    private final ConnectionFactory connectionFactory;
    private final HolderDao holderDao;
    private final CardDao cardDao;

    public TransactionDaoImpl(ConnectionFactory connectionFactory, HolderDao holderDao, CardDao cardDao) {
        super(Transaction.class, connectionFactory);
        this.connectionFactory = connectionFactory;
        this.holderDao = holderDao;
        this.cardDao = cardDao;
    }

    @Override
    public boolean insert(final Transaction transaction) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_INSERT_TRANSACTION = "INSERT INTO TRANSACTION VALUES (NULL, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_TRANSACTION)) {

            ps.setInt(1, transaction.getSender().getId());
            ps.setInt(2, transaction.getRecipient().getId());
            ps.setBigDecimal(3,transaction.getTransferAmount());
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
    public boolean update(final Transaction transaction) {

        Connection connection = connectionFactory.getConnection();
        final String SQL_UPDATE_TRANSACTION = "UPDATE TRANSACTION SET SENDER=?, RECIPIENT=?, TRANSFERAMOUNT=?  WHERE ID=?";

        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TRANSACTION)){

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

        final Holder sender = holderDao.getOne(rs.getInt("SENDER"));
        final Holder recipient = holderDao.getOne(rs.getInt("RECIPIENT"));
        return new Transaction(rs.getInt("ID"), sender, recipient, LoadSettings.getInstance().getSettings(),
                rs.getBigDecimal("TRANSFERAMOUNT"));

    }
}
