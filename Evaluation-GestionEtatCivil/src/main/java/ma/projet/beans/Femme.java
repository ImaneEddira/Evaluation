package ma.projet.beans;

import jakarta.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Femme.marieeDeuxFois",
                query = "SELECT f FROM Femme f WHERE SIZE(f.mariages) >= 2")
})
public class Femme extends Personne {
    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

    public Femme() {}

    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}
