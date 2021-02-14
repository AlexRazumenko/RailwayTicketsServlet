package ua.alex.railway.tickets.utils;

import ua.alex.railway.tickets.entity.User;

public class Utils {

    private static final User EMPTY_USER =
            User.userBuilder().withId(0L).build();

    public static User getEmptyUser() {
        return EMPTY_USER;
    }




}
