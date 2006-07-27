package org.javageek.katalogo.dao.impl;

import java.util.List;

import org.javageek.katalogo.dao.UserDao;
import org.javageek.katalogo.model.User;

public class UserDaoImpl extends AbstractBasicHibernateDao<User> implements UserDao {
    /**
     * Construct.
     *
     * @param clazz
     */
    public UserDaoImpl() {
        super(User.class);
    }

    public User authenticate(final String email, final String password) {
        final List<User> users = list("User.authenticate", email, password);
        User user = null;
        if(!users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

    public User findByEmail(final String email) {
        final List<User> users = list("User.byEmail", email);
        return !users.isEmpty() ? users.get(0) : null;
    }
}
