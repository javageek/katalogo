package org.javageek.katalogo.page;

import org.javageek.katalogo.service.UserService;
import wicket.markup.html.form.Form;
import wicket.markup.html.form.TextField;
import wicket.markup.html.link.BookmarkablePageLink;
import wicket.markup.html.panel.FeedbackPanel;
import wicket.model.CompoundPropertyModel;
import wicket.spring.injection.annot.SpringBean;

public class ForgottenPassword extends KatalogoPage {
    public ForgottenPassword() {
        add(new FeedbackPanel("feedback"));
        add(new PasswordForm("form"));
        add(new BookmarkablePageLink("loginLink", Login.class));
    }

    private static final class PasswordForm extends Form {
        @SpringBean
        UserService service;
        private String email;

        public PasswordForm(final String id) {
            super(id);
            setModel(new CompoundPropertyModel(this));
            add(new TextField("email"));
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(final String address) {
            this.email = address;
        }

        @Override
        public void onSubmit() {
            if(service.emailPassword(getEmail())) {
                info("Your password has been emailed to you.  Check your email and click the login link"
                    + " below to login.");
            } else {
                error("No user account was found for that email address.  Check the address and try again.");
            }
        }
    }

}
