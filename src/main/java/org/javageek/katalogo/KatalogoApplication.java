package org.javageek.katalogo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javageek.katalogo.page.HomePage;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import wicket.ISessionFactory;
import wicket.markup.parser.IMarkupFilter;
import wicket.markup.parser.filter.PrependContextPathHandler;
import wicket.spring.injection.annot.AnnotSpringWebApplication;

public class KatalogoApplication extends AnnotSpringWebApplication implements ISessionFactory {
    private static final Log log = LogFactory.getLog(KatalogoApplication.class);

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public KatalogoSession newSession() {
        return new KatalogoSession(this);
    }

    @Override
    protected ISessionFactory getSessionFactory() {
        return this;
    }

    @Override
    protected void init() {
        super.init();
        getAjaxSettings().setAjaxDebugModeEnabled(true);
        getSecuritySettings().setAuthorizationStrategy(new KatalogoAuthorizationStrategy());

        checkForSchema();

        log.debug("katalogo started.");
    }

    private void checkForSchema() {
        try {
            final DataSource dataSource = (DataSource)getBean(DataSource.class);
            final Connection con = dataSource.getConnection();
            ResultSet rs = null;
            try {
                final LocalSessionFactoryBean bean = (LocalSessionFactoryBean)getBean(LocalSessionFactoryBean.class);
                try {
                    rs = con.createStatement().executeQuery("select * from users");
                    if(Boolean.valueOf(getWicketServlet().getInitParameter("update.database"))) {
                        bean.updateDatabaseSchema();
                    }
                } catch(SQLException e) {
                    log.info("Schema doesn't exist.  Building now.");
                    bean.createDatabaseSchema();
//                    final User user = new User();
//                    user.setEmail("katalogo@javageek.org");
//                    user.setPassword("k4t4l0g0");
//                    user.setFirstName("Katalogo");
//                    user.setLastName("User");

//                    log.info("Creating default user.");
//                    con.createStatement().executeUpdate("insert into users (email, password, firstName, lastName) "
//                            + "values ('katalogo@javageek.org', 'k4t4l0g0', 'Katalogo', 'User')");
//                    log.info("Default user created.");
                }
            } finally {
                if(rs != null) {
                    rs.close();
                }
                con.close();
            }
        } catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Object getBean(final Class clazz) {
        return getSpringContextLocator().getSpringContext().getBeansOfType(clazz).values().iterator().next();
    }

    public IMarkupFilter[] getAdditionalMarkupHandler() {
        return new IMarkupFilter[]{new PrependContextPathHandler(this)};
    }
}
