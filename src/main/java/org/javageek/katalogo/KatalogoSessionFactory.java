package org.javageek.katalogo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@SuppressWarnings({"FieldCanBeLocal"})
public class KatalogoSessionFactory extends AnnotationSessionFactoryBean implements ApplicationContextAware {
    private ApplicationContext context;
    private static final Log log = LogFactory.getLog(KatalogoSessionFactory.class);

    /**
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    public final void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
