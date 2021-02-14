package ua.alex.railway.tickets.dao.factory;

import ua.alex.railway.tickets.dao.StationDAO;
import ua.alex.railway.tickets.dao.TicketDAO;
import ua.alex.railway.tickets.dao.TrainDAO;
import ua.alex.railway.tickets.dao.UserDAO;

import java.sql.SQLException;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public abstract UserDAO createUserDAO();
    public abstract StationDAO createStationDAO();
    public abstract TrainDAO createTrainDAO();
    public abstract TicketDAO createTicketDAO();

    public static DAOFactory getInstance() throws SQLException {

    if(daoFactory ==null)
    {
        synchronized (DAOFactory.class) {
            if (daoFactory == null) {
                DAOFactory temp = new JDBCDAOFactory();
                daoFactory = temp;
            }
        }
    }
        return daoFactory;
}

}
