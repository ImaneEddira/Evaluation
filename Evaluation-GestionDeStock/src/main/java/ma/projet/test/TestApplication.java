package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

public class TestApplication {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();

        // Ajouter une catégorie
        Categorie c1 = new Categorie();
        c1.setCode("CAT1");
        c1.setLibelle("Ordinateurs");
        cs.create(c1);

        // Ajouter un produit
        Produit p1 = new Produit();
        p1.setReference("ES12");
        p1.setPrix(120);
        p1.setCategorie(c1);
        ps.create(p1);

        System.out.println("Produit et catégorie insérés !");
    }
}
