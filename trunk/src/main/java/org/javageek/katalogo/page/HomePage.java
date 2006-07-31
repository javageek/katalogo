package org.javageek.katalogo.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import wicket.markup.html.basic.Label;
import org.javageek.katalogo.Authorized;

@Authorized
public class HomePage extends KatalogoPage {
    private static final Log log = LogFactory.getLog(HomePage.class);

    public HomePage() {
        log.debug("home page!");
        add(new Label("user", getkatalogoSession().getUser().getFullName()));
    }
}
