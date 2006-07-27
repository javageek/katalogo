package org.javageek.katalogo;

import wicket.Component;
import wicket.RestartResponseAtInterceptPageException;
import wicket.authorization.Action;
import wicket.authorization.IAuthorizationStrategy;
import wicket.authorization.strategies.CompoundAuthorizationStrategy;
import org.javageek.katalogo.page.Login;

public class KatalogoAuthorizationStrategy extends CompoundAuthorizationStrategy {
    /**
     * Checks that pages marked with {@link Authorized} have a valid user.
     */
    @SuppressWarnings("unchecked")
    private static final class ProtectedComponentChecker implements
        IAuthorizationStrategy {
        public boolean isInstantiationAuthorized(final Class componentClass) {
            if(componentClass.getAnnotation(Authorized.class) != null
                && ((KatalogoSession)KatalogoSession.get()).getUser() == null) {
                throw new RestartResponseAtInterceptPageException(Login.class);
            }
            return true;
        }

        public boolean isActionAuthorized(final Component component, final Action action) {
            return true;
        }
    }

    public KatalogoAuthorizationStrategy() {
        add(new ProtectedComponentChecker());
    }
}
