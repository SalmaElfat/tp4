package services;

import dao.IDao;
import entities.salle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class salleservice implements IDao<salle>{

    @Override
    public boolean create(salle o) {
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
    public boolean delete(salle o) {
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
    public boolean update(salle o) {
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
    public salle findById(int id) {
        Session session = null;
        Transaction tx = null;
        salle salle = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salle = session.get(salle.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return salle;
    }

    @Override
    public List<salle> findAll() {
        Session session = null;
        Transaction tx = null;
        List<salle> salles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salles = session.createQuery("from salle ", salle.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
        return salles;
    }


}
