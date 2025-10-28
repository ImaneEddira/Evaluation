package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

public class FemmeService {

    // 🔹 CRUD basique
    public void create(Femme f) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.persist(f);
        tx.commit();
        s.close();
    }

    public List<Femme> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> femmes = s.createQuery("from Femme", Femme.class).list();
        s.close();
        return femmes;
    }

    // ✅ 1. Nombre d’enfants d’une femme entre deux dates (JPQL)
    public int getNombreEnfants(int idFemme, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        TypedQuery<Long> q = s.createQuery(
                "SELECT SUM(m.nbrEnfant) FROM Mariage m " +
                        "WHERE m.femme.id = :idFemme " +
                        "AND m.dateDebut BETWEEN :d1 AND :d2", Long.class);

        q.setParameter("idFemme", idFemme);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);

        Long result = q.getSingleResult();
        s.close();

        return result != null ? result.intValue() : 0;
    }

    // ✅ 2. Femmes mariées au moins deux fois (requête nommée)
    public List<Femme> getFemmesMarieesDeuxFois() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = s.createNamedQuery("Femme.marieeDeuxFois", Femme.class).list();
        s.close();
        return list;
    }

    // ✅ 3. Hommes mariés à 4 femmes entre deux dates (Criteria API)
    public long getHommesMarieesAQuatre(Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Homme> root = cq.from(Homme.class);

        // ⚠️ Simplifié : on ne filtre pas encore par dates
        cq.select(cb.count(root))
                .where(cb.greaterThanOrEqualTo(cb.size(root.get("mariages")), 4));

        Long result = s.createQuery(cq).getSingleResult();
        s.close();
        return result != null ? result : 0;
    }

    // ✅ 4. Afficher les mariages d’un homme avec détails
    public void afficherMariagesHomme(int idHomme) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Homme homme = s.get(Homme.class, idHomme);

        if (homme == null) {
            System.out.println("Aucun homme trouvé avec cet ID !");
            s.close();
            return;
        }

        System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

        System.out.println("Mariages en cours :");
        for (Mariage m : homme.getMariages()) {
            if (m.getDateFin() == null) {
                System.out.println("Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                        "   Date Début : " + m.getDateDebut() +
                        "   Nbr Enfants : " + m.getNbrEnfant());
            }
        }

        System.out.println("\nMariages échoués :");
        for (Mariage m : homme.getMariages()) {
            if (m.getDateFin() != null) {
                System.out.println("Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                        "   Date Début : " + m.getDateDebut() +
                        "   Date Fin : " + m.getDateFin() +
                        "   Nbr Enfants : " + m.getNbrEnfant());
            }
        }

        s.close();
    }
}
