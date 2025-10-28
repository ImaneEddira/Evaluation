package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        }
    }

    @Override
    public List<Projet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        }
    }

    // 🔹 Charger un projet avec ses tâches (pour éviter LazyInitializationException)
    public Projet getByIdWithTaches(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Projet> query = session.createQuery(
                    "select distinct p from Projet p " +
                            "left join fetch p.employeTaches et " +
                            "left join fetch et.tache " +
                            "where p.id = :id", Projet.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    // 🔹 Récupérer les tâches planifiées
    public List<Tache> getTachesPlanifiees(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery(
                    "select t from Tache t where t.projet.id = :pid", Tache.class);
            query.setParameter("pid", projetId);
            return query.list();
        }
    }

    // 🔹 Récupérer les tâches réalisées avec dates réelles
    public List<EmployeTache> getTachesRealisees(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<EmployeTache> query = session.createQuery(
                    "select et from EmployeTache et " +
                            "join fetch et.tache " +
                            "where et.tache.projet.id = :pid", EmployeTache.class);
            query.setParameter("pid", projetId);
            return query.list();
        }
    }
}
