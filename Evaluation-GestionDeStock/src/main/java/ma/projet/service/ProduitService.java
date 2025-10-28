package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Produit;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    @Override
    public void create(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Produit getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Produit p = session.get(Produit.class, id);
        session.close();
        return p;
    }

    @Override
    public List<Produit> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createQuery("from Produit").list();
        session.close();
        return produits;
    }

    @Override
    public void update(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    // Produits dont prix > 100
    public List<Produit> produitsPrixSup100() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createNamedQuery("Produit.findByPrixSup").list();
        session.close();
        return produits;
    }
}
