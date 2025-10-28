package ma.projet.test;

import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.service.CommandeService;

import java.text.SimpleDateFormat;

public class TestFonctionnalite {
    public static void main(String[] args) {
        CommandeService commandeService = new CommandeService();
        Commande commande = commandeService.getById(4); // on récupère la commande avec id=4

        if (commande != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

            System.out.println("Commande : " + commande.getId() +
                    "     Date : " + sdf.format(commande.getDate()));

            System.out.println("Liste des produits :");
            System.out.printf("%-10s %-10s %-10s%n", "Référence", "Prix", "Quantité");

            for (LigneCommandeProduit lcp : commande.getLignes()) {
                Produit p = lcp.getProduit();
                System.out.printf("%-10s %-10.2f %-10d%n",
                        p.getReference(),
                        p.getPrix(),
                        lcp.getQuantite());
            }
        } else {
            System.out.println("Commande introuvable !");
        }
    }
}
