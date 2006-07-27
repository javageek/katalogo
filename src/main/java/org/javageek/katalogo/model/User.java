package org.javageek.katalogo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name="User.authenticate", query="from User u where u.email=? and u.password=?"),
    @NamedQuery(name="User.byEmail", query="from User u where u.email=?")
})
public class User implements Persistent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30)
    private String email;
    @Column(length = 30)
    private String firstName;
    @Column(length = 30)
    private String lastName;
    @Column(length=30)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(final Long userID) {
        this.id = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String name) {
        this.lastName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String address) {
        this.email = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String value) {
        this.password = value;
    }

    @Transient
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
