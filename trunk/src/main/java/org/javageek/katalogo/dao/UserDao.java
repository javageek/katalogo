package org.javageek.katalogo.dao;

import org.javageek.katalogo.model.User;

public interface UserDao extends BaseDao<User>{
    User authenticate(String email, String password);

    User findByEmail(String address);
}
