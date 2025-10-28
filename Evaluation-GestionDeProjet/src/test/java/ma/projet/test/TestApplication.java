package ma.projet.test;

import ma.projet.service.*;
import ma.projet.classes.*;

public class TestApplication {
    public static void main(String[] args) {
        EmployeService empService = new EmployeService();
        ProjetService projService = new ProjetService();
        TacheService tacheService = new TacheService();

        System.out.println("== Employés existants ==");
        empService.getAll().forEach(e -> System.out.println(e.getNom()));

        System.out.println("== Projets existants ==");
        projService.getAll().forEach(p -> System.out.println(p.getNom()));

        System.out.println("== Tâches prix > 1000 ==");
        tacheService.getTachesPrixSup1000().forEach(t ->
                System.out.println(t.getNom() + " - " + t.getPrix())
        );
    }
}
