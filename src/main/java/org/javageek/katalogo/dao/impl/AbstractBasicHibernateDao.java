package org.javageek.katalogo.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractBasicHibernateDao<T> {
    private static final Log log = LogFactory.getLog(AbstractBasicHibernateDao.class);
    private Class _clazz;

    private SessionFactory sessionFactory;

    /**
     * Construct.
     *
     * @param clazz
     */
    public AbstractBasicHibernateDao(final Class clazz) {
        try {
            _clazz = Class.forName(getClass().getGenericSuperclass().toString().split("[<>]")[1]);
        } catch(ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public void save(final T obj) {
        getSession().saveOrUpdate(obj);
    }

    @SuppressWarnings("unchecked")
    public T load(final long id) {
        return (T)getSession().get(_clazz, id);
    }

    @SuppressWarnings("unchecked")
    public T merge(final T obj) {
        return (T)getSession().merge(obj);
    }

    public void delete(final T obj) {
        getSession().delete(obj);
    }

    public void detach(final T obj) {
        getSession().evict(obj);
    }

    public void flush() {
        getSession().flush();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(final SessionFactory factory) {
        sessionFactory = factory;
    }

    protected Query createQuery(final String queryString) {
        return getSession().createQuery(queryString);
    }

    protected Query createQuery(final String hql, final Object... params) {
        final Query q = createQuery(hql);
        for(int i = 0; i < params.length; i++) {
            q.setParameter(i, params[i]);
        }
        return q;
    }

    @SuppressWarnings({"unchecked"})
    protected List<T> list(final String name) {
        return (List<T>)getSession().getNamedQuery(name).list();
    }

    @SuppressWarnings({"unchecked"})
    protected List<T> list(final String name, final Object... params) {
        final Query query = getSession().getNamedQuery(name);
        for(int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return (List<T>)query.list();
    }
}
