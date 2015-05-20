package SqlLite;

import java.util.Date;

/**
 * Created by mehdibeggas on 15/05/2015.
 */
public class Repas {

    private long id;
    private long quantite;
    private Date date_heure;
    private int duree;
    private long ref_bebe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public long getRef_bebe() {
        return ref_bebe;
    }

    public void setRef_bebe(long ref_bebe) {
        this.ref_bebe = ref_bebe;
    }
}
