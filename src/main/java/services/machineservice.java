package services;
import dao.IDao;
import entities.machine;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class machineservice implements IDao<machine> {
    @Override
    public boolean create(machine o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean delete(machine o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean update(machine o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public machine findById(int id) {
        Session session = null;
        Transaction tx = null;
        machine machine = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            machine = session.get(machine.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return machine;
    }

    @Override
    public List<machine> findAll() {
        Session session = null;
        Transaction tx = null;
        List<machine> machines = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            machines = session.createQuery("from machine ", machine.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return machines;
    }

    public List<machine> findBetweenDate(Date d1, Date d2) {
        Session session = null;
        Transaction tx = null;
        List<machine> machines = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            machines = session.getNamedQuery("findBetweenDate")
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return machines;
    }
}
