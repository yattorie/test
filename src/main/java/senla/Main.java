package senla;

import senla.dao.TransactionDao;
import senla.dao.impl.TransactionDaoImpl;
import senla.util.ConnectionManager;
import senla.util.ErrorMessages;
import senla.util.Messages;
import senla.util.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {

            connection = ConnectionManager.getConnection();
            TransactionManager transactionManager = new TransactionManager(connection);
            TransactionDao transactionDAO = new TransactionDaoImpl(connection);

           try{

               transactionManager.beginTransaction();
               transactionDAO.findAllTransactions().forEach(System.out::println);
               transactionManager.commit();

           } catch (Exception e) {
               System.out.println(ErrorMessages.TRANSACTION_FAILED.getDescription());
               transactionManager.rollback();
           }

            try {
                transactionManager.beginTransaction();
                System.out.println(Messages.TRANSACTION_DELETE_START.getMessage());
                transactionDAO.delete(8);
                transactionManager.commit();
                System.out.println(Messages.TRANSACTION_DELETE_SUCCESS.getMessage());
            } catch (Exception e) {
                System.out.println(ErrorMessages.TRANSACTION_FAILED.getDescription());
                transactionManager.rollback();
            }

        } finally {
            ConnectionManager.releaseConnection(connection);
        }
    }
}




