package SqlLite;

/**
 * Created by mehdibeggas on 15/05/2015.
 */
public class Bebe {

    private long id;
    private long ref_utilisateur;
    private String prenom;
    private String nom;
    private long age;
    private double poids;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public long getRef_utilisateur() {
        return ref_utilisateur;
    }

    public void setRef_utilisateur(long ref_utilisateur) {
        this.ref_utilisateur = ref_utilisateur;
    }
}
