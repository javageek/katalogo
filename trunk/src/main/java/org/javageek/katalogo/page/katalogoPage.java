package org.javageek.katalogo.page;

import org.javageek.katalogo.KatalogoSession;
import wicket.markup.html.WebPage;

public class katalogoPage extends WebPage {
    public KatalogoSession getkatalogoSession() {
        return (KatalogoSession)getSession();
    }
}
