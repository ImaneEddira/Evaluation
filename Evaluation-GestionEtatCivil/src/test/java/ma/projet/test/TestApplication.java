package ma.projet.test;

import ma.projet.beans.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class TestApplication {
    public static void main(String[] args) throws Exception {
        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Créer 5 hommes
        Homme h1 = new Homme(); h1.setNom("SAFI"); h1.setPrenom("SAID"); h1.setDateNaissance(sdf.parse("01/01/1960"));
        Homme h2 = new Homme(); h2.setNom("BEN"); h2.setPrenom("ALI"); h2.setDateNaissance(sdf.parse("15/03/1970"));
        Homme h3 = new Homme(); h3.setNom("RAJI"); h3.setPrenom("OMAR"); h3.setDateNaissance(sdf.parse("22/07/1980"));
        Homme h4 = new Homme(); h4.setNom("NASSER"); h4.setPrenom("KHALID"); h4.setDateNaissance(sdf.parse("10/12/1975"));
        Homme h5 = new Homme(); h5.setNom("AMINE"); h5.setPrenom("HICHAM"); h5.setDateNaissance(sdf.parse("05/05/1985"));

        hommeService.create(h1); hommeService.create(h2); hommeService.create(h3); hommeService.create(h4); hommeService.create(h5);

        // Créer 10 femmes
        Femme f1 = new Femme(); f1.setNom("RAMI"); f1.setPrenom("SALIMA"); f1.setDateNaissance(sdf.parse("20/02/1965"));
        Femme f2 = new Femme(); f2.setNom("ALI"); f2.setPrenom("AMAL"); f2.setDateNaissance(sdf.parse("05/09/1972"));
        Femme f3 = new Femme(); f3.setNom("ALAOUI"); f3.setPrenom("WAFA"); f3.setDateNaissance(sdf.parse("11/11/1978"));
        Femme f4 = new Femme(); f4.setNom("ALAMI"); f4.setPrenom("KARIMA"); f4.setDateNaissance(sdf.parse("03/03/1960"));
        Femme f5 = new Femme(); f5.setNom("FARAH"); f5.setPrenom("LINA"); f5.setDateNaissance(sdf.parse("25/07/1982"));
        Femme f6 = new Femme(); f6.setNom("ZARA"); f6.setPrenom("HANANE"); f6.setDateNaissance(sdf.parse("14/10/1980"));
        Femme f7 = new Femme(); f7.setNom("NOUR"); f7.setPrenom("MARIAM"); f7.setDateNaissance(sdf.parse("08/08/1988"));
        Femme f8 = new Femme(); f8.setNom("KADI"); f8.setPrenom("IMANE"); f8.setDateNaissance(sdf.parse("01/01/1975"));
        Femme f9 = new Femme(); f9.setNom("HAMDI"); f9.setPrenom("SARA"); f9.setDateNaissance(sdf.parse("15/04/1990"));
        Femme f10 = new Femme(); f10.setNom("YASSIN"); f10.setPrenom("RANIA"); f10.setDateNaissance(sdf.parse("09/09/1992"));

        femmeService.create(f1); femmeService.create(f2); femmeService.create(f3); femmeService.create(f4); femmeService.create(f5);
        femmeService.create(f6); femmeService.create(f7); femmeService.create(f8); femmeService.create(f9); femmeService.create(f10);

        // Créer des mariages pour h1 (SAFI SAID)
        Mariage m1 = new Mariage(); m1.setHomme(h1); m1.setFemme(f1); m1.setDateDebut(sdf.parse("03/09/1990")); m1.setNbrEnfant(4);
        Mariage m2 = new Mariage(); m2.setHomme(h1); m2.setFemme(f2); m2.setDateDebut(sdf.parse("03/09/1995")); m2.setNbrEnfant(2);
        Mariage m3 = new Mariage(); m3.setHomme(h1); m3.setFemme(f3); m3.setDateDebut(sdf.parse("04/11/2000")); m3.setNbrEnfant(3);
        Mariage m4 = new Mariage(); m4.setHomme(h1); m4.setFemme(f4); m4.setDateDebut(sdf.parse("03/09/1989")); m4.setDateFin(sdf.parse("03/09/1990")); m4.setNbrEnfant(0);

        mariageService.create(m1);
        mariageService.create(m2);
        mariageService.create(m3);
        mariageService.create(m4);

        // ============================================================
        // AFFICHAGES DEMANDÉS
        // ============================================================

        // 1. Liste des femmes
        System.out.println("Liste des femmes :");
        for (Femme f : femmeService.getAll()) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        // 2. Femme la plus âgée
        Femme plusAgee = femmeService.getAll().stream()
                .min(Comparator.comparing(Femme::getDateNaissance))
                .orElse(null);
        if (plusAgee != null) {
            System.out.println("\nFemme la plus âgée : " + plusAgee.getNom() + " " + plusAgee.getPrenom());
        }

        // 3. Afficher les épouses d’un homme donné (h1)
        System.out.println("\nEpouses de " + h1.getNom() + " :");
        for (Mariage m : h1.getMariages()) {
            System.out.println("- " + m.getFemme().getNom() + " " + m.getFemme().getPrenom());
        }

        // 4. Nombre d’enfants d’une femme (exemple f1)
        int nbEnfants = femmeService.getNombreEnfants(f1.getId(), sdf.parse("01/01/1980"), sdf.parse("01/01/2025"));
        System.out.println("\nNombre d’enfants de " + f1.getNom() + " " + f1.getPrenom() + " : " + nbEnfants);

        // 5. Femmes mariées deux fois ou plus
        System.out.println("\nFemmes mariées au moins 2 fois :");
        for (Femme f : femmeService.getFemmesMarieesDeuxFois()) {
            System.out.println("- " + f.getNom() + " " + f.getPrenom());
        }

        // 6. Hommes mariés à 4 femmes entre deux dates
        long nbHommes4Femmes = femmeService.getHommesMarieesAQuatre(sdf.parse("01/01/1980"), sdf.parse("01/01/2025"));
        System.out.println("\nHommes mariés à 4 femmes : " + nbHommes4Femmes);

        // 7. Mariages détaillés d’un homme (h1)
        System.out.println("\nDétails des mariages de " + h1.getNom() + " :");
        femmeService.afficherMariagesHomme(h1.getId());
    }
}
