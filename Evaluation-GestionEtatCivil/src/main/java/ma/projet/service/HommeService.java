package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public void create(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.persist(o);
        tx.commit();
        s.close();
    }

    @Override
    public Homme getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Homme h = s.get(Homme.class, id);
        s.close();
        return h;
    }

    @Override
    public List<Homme> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Homme> list = s.createQuery("from Homme", Homme.class).list();
        s.close();
        return list;
    }

    @Override
    public void update(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.merge(o);
        tx.commit();
        s.close();
    }

    @Override
    public void delete(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.remove(o);
        tx.commit();
        s.close();
    }

    // ⚡ Méthode : afficher les épouses d’un homme entre deux dates
    public List<Mariage> getEpousesEntreDates(int idHomme, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery(
                        "FROM Mariage m WHERE m.homme.id = :id AND m.dateDebut BETWEEN :d1 AND :d2",
                        Mariage.class)
                .setParameter("id", idHomme)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        s.close();
        return list;
    }
}
