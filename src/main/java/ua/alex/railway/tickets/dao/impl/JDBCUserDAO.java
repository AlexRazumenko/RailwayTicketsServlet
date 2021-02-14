package ua.alex.railway.tickets.dao.impl;

import ua.alex.railway.tickets.dao.UserDAO;
import ua.alex.railway.tickets.dao.mapper.UserMapper;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDAO implements UserDAO {

    private Connection connection;

    public JDBCUserDAO() {
        try {
            this.connection = ConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(User user) {

        System.out.println("In DAO:\n" + user);

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                 connection.prepareStatement
                         ("INSERT INTO users (id, email, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?, ?)",
                                 Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, ConnectionPoolHolder.getAndUpdateMaxId());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getRole().name());
                ps.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Error creating new user");
        }
    }

    @Override
    public User findById(long id) {
//        Map<Integer, User> users = new HashMap<>();
        final String query = "select from users where id = " + id;
        User user = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
//                user = userMapper.makeUnique(users, user);
            }

        } catch (SQLException e) {
            System.out.println("Wrong query");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();

        final String query = "select * from users";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                         connection.prepareStatement("INSERT INTO users (id, email, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setLong(1, user.getId());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getRole().name());
                ps.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Error updating user");
        }
    }

    @Override
    public void delete(long id) {
        final String query = "delete from users where id = "+ id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {
//        Map<Integer, User> users = new HashMap<>();

        final String query = "select * from users where email = '" + email + "'";
        User user = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
//                user = userMapper.makeUnique(users, user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Wrong query");

        }
        return user;
    }




    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
