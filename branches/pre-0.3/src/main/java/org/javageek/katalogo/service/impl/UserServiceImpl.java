package org.javageek.katalogo.service.impl;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.javageek.katalogo.dao.UserDao;
import org.javageek.katalogo.model.User;
import org.javageek.katalogo.service.UserService;
import org.javageek.katalogo.KatalogoException;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public User authenticate(final String email, final String password) {
        return userDao.authenticate(email, password);
    }

    public boolean emailPassword(final String address) {
        final User user = userDao.findByEmail(address);
        boolean found = false;
        if(user != null) {
            found = true;
            try {
                final SimpleEmail email = new SimpleEmail();
                email.setHostName("mail.myserver.com");
                email.addTo(user.getEmail(), user.getFullName());
                email.setFrom("katalogo", "Password Reminder");
                email.setSubject("Reminder Email");
                email.setMsg("Your password is " + user.getPassword());
                email.send();
            } catch(EmailException e) {
                throw new KatalogoException(e.getMessage());
            }

        }
        return found;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(final UserDao dao) {
        this.userDao = dao;
    }
}
