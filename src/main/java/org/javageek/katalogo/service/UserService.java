package org.javageek.katalogo.service;

import org.javageek.katalogo.model.User;

public interface UserService {
    User authenticate(String email, String password);

    /**
     * Emails the password to the user.
     *
     * TODO:  this really should generate a new password and email it out since eventually passwords will be encrypted
     * in the database.
     * @param email
     * @return true if the email is valid and the password is emailed.
     */
    boolean emailPassword(String email);
}
