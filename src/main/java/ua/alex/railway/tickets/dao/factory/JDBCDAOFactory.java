package ua.alex.railway.tickets.dao.factory;

import ua.alex.railway.tickets.dao.StationDAO;
import ua.alex.railway.tickets.dao.TicketDAO;
import ua.alex.railway.tickets.dao.TrainDAO;
import ua.alex.railway.tickets.dao.UserDAO;
import ua.alex.railway.tickets.dao.impl.JDBCUserDAO;
import ua.alex.railway.tickets.dao.impl.JdbcStationDao;
import ua.alex.railway.tickets.dao.impl.JdbcTicketDao;
import ua.alex.railway.tickets.dao.impl.JdbcTrainDao;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDAOFactory extends DAOFactory {
    private Connection connection;

    public JDBCDAOFactory() throws SQLException{
        this.connection  = ConnectionPoolHolder.getConnection();;
    }

    @Override
    public UserDAO createUserDAO() {
        return new JDBCUserDAO();
    }

    @Override
    public StationDAO createStationDAO() {
        return new JdbcStationDao();
    }

    @Override
    public TrainDAO createTrainDAO() {
        return new JdbcTrainDao();
    }

    @Override
    public TicketDAO createTicketDAO() {
        return new JdbcTicketDao();
    }

//    private Connection getConnection(){
//        try {
//            return dataSource.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
