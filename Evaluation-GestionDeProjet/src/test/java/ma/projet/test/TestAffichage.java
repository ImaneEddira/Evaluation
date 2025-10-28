package ma.projet.test;

import java.text.SimpleDateFormat;
import ma.projet.classes.Projet;
import ma.projet.classes.EmployeTache;
import ma.projet.service.ProjetService;

public class TestAffichage {
    public static void main(String[] args) {
        ProjetService projetService = new ProjetService();
        Projet projet = projetService.getByIdWithTaches(1); // id du projet

        if (projet != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("=====================================================");
            System.out.println("Projet : " + projet.getId() +
                    "\tNom : " + projet.getNom() +
                    "\tDate début : " + (projet.getDateDebut() != null ? sdf.format(projet.getDateDebut()) : "N/A"));
            System.out.println("-----------------------------------------------------");
            System.out.println("Liste des tâches:");
            System.out.printf("%-5s %-20s %-20s %-20s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");
            System.out.println("-----------------------------------------------------");

            for (EmployeTache et : projet.getEmployeTaches()) {
                System.out.printf("%-5d %-20s %-20s %-20s%n",
                        et.getTache().getId(),
                        et.getTache().getNom(),
                        et.getDateDebutReelle() != null ? sdf.format(et.getDateDebutReelle()) : "N/A",
                        et.getDateFinReelle() != null ? sdf.format(et.getDateFinReelle()) : "N/A");
            }
        } else {
            System.out.println("Projet introuvable !");
        }
    }
}
