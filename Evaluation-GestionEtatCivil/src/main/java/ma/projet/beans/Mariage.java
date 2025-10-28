package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nbrEnfant;

    @ManyToOne
    @JoinColumn(name = "homme_id")   // clé étrangère vers Homme
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme_id")   // clé étrangère vers Femme
    private Femme femme;

    public Mariage() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }
}
