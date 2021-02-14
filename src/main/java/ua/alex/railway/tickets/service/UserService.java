package ua.alex.railway.tickets.service;

import ua.alex.railway.tickets.dao.UserDAO;
import ua.alex.railway.tickets.dao.factory.DAOFactory;
import ua.alex.railway.tickets.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private DAOFactory daoFactory;

    public UserService() {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (UserDAO dao = daoFactory.createUserDAO()){
            return dao.findAll();
        }
    }

    public User findUserById(long id) {
        try (UserDAO dao = daoFactory.createUserDAO()){
            return dao.findById(id);
        }
    }

    public User findUserByEmail(String email) {
        try (UserDAO dao = daoFactory.createUserDAO()){
            return dao.findByEmail(email);
        }
    }

    public void createUser(User user) {
        try (UserDAO dao = daoFactory.createUserDAO()){
            dao.create(user);
        }
    }

    public void updateUser (User user) {
        try (UserDAO dao = daoFactory.createUserDAO()){
            dao.update(user);
        }
    }

public void deleteUser (int id) {
    try (UserDAO dao = daoFactory.createUserDAO()){
        dao.delete(id);
    }
}

}
