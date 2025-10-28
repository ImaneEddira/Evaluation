package ma.projet.test;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.service.ProjetService;
import ma.projet.service.EmployeService;

public class TestFonctionnalite {
    public static void main(String[] args) {
        ProjetService projService = new ProjetService();
        EmployeService empService = new EmployeService();

        // Exemple avec projet id = 1
        Projet p1 = projService.getById(1);

        if (p1 != null) {
            System.out.println("\n== Tâches planifiées du projet ==");
            projService.getTachesPlanifiees(p1.getId())
                    .forEach(t -> System.out.println("- " + t.getNom()));

            System.out.println("\n== Tâches réalisées du projet ==");
            projService.getTachesRealisees(p1.getId())
                    .forEach(et -> System.out.println("- " + et.getTache().getNom()
                            + " | Début réel : " + et.getDateDebutReelle()
                            + " | Fin réelle : " + et.getDateFinReelle()));
        }
    }
}
