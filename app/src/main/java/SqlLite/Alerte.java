package SqlLite;

import java.util.Date;

/**
 * Created by mehdibeggas on 21/05/2015.
 */
public class Alerte {

    private long id;
    private Date date_heure;
    private long ref_bebe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }

    public long getRef_bebe() {
        return ref_bebe;
    }

    public void setRef_bebe(long ref_bebe) {
        this.ref_bebe = ref_bebe;
    }
}
