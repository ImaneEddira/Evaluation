package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.save(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Employe e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.update(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Employe e) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.delete(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Employe getById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> getAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Employe", Employe.class).list();
        }
    }

    // 🔹 Méthode spécifique : récupérer les tâches d’un employé
    public List<Tache> getTachesByEmploye(int idEmp) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = s.createQuery(
                    "select et.tache from EmployeTache et where et.employe.id = :id", Tache.class);
            query.setParameter("id", idEmp);
            return query.list();
        }
    }

    // 🔹 Méthode spécifique : récupérer les projets gérés par un employé (en tant que chef de projet)
    public List<ma.projet.classes.Projet> getProjetsByEmploye(int idEmp) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<ma.projet.classes.Projet> query = s.createQuery(
                    "from Projet p where p.chef.id = :id", ma.projet.classes.Projet.class);
            query.setParameter("id", idEmp);
            return query.list();
        }
    }
}
