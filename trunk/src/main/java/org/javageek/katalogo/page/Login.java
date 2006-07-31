package org.javageek.katalogo.page;

import org.javageek.katalogo.KatalogoSession;
import org.javageek.katalogo.model.User;
import org.javageek.katalogo.service.UserService;
import wicket.markup.html.form.Form;
import wicket.markup.html.form.PasswordTextField;
import wicket.markup.html.form.TextField;
import wicket.markup.html.link.BookmarkablePageLink;
import wicket.markup.html.panel.FeedbackPanel;
import wicket.model.PropertyModel;
import wicket.spring.injection.annot.SpringBean;
import wicket.util.value.ValueMap;

public class Login extends KatalogoPage {
    public Login() {
        add(new FeedbackPanel("feedback"));
        add(new LoginForm("form"));
        add(new BookmarkablePageLink("passwordLink", ForgottenPassword.class));
    }

    private static final class LoginForm extends Form {
        @SpringBean UserService service;
        private final ValueMap properties = new ValueMap();

        public LoginForm(final String id) {
            super(id);
            add(new TextField("email", new PropertyModel(properties, "email")));
            add(new PasswordTextField("password", new PropertyModel(properties, "password")));
        }

        @Override
        public void onSubmit() {
            final KatalogoSession session =
                ((KatalogoPage)getPage()).getkatalogoSession();
            final User user = service.authenticate(properties.getString("email"), properties.getString("password"));
            if(user == null) {
                error("Invalid email or password.");
            } else {
                session.setUser(user);
                setResponsePage(getApplication().getHomePage());
            }
        }
    }
}
