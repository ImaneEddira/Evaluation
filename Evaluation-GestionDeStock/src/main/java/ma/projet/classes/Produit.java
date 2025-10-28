package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "produits")
@NamedQueries({
        @NamedQuery(name = "Produit.findByPrixSup",
                query = "SELECT p FROM Produit p WHERE p.prix > 100")
})
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reference;
    private float prix;

    @ManyToOne
    private Categorie categorie;

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
}
