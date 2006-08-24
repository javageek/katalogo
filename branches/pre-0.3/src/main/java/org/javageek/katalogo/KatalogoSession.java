package org.javageek.katalogo;

import org.javageek.katalogo.model.User;
import wicket.protocol.http.WebSession;

public class KatalogoSession extends WebSession {
    private User user;

    public KatalogoSession(final KatalogoApplication app) {
        super(app);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User sessionUser) {
        this.user = sessionUser;
    }
}
