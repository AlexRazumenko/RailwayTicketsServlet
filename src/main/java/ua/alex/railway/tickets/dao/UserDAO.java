package ua.alex.railway.tickets.dao;

import ua.alex.railway.tickets.entity.User;

public interface UserDAO extends GenericDAO<User> {
    User findByEmail(String email);
}
