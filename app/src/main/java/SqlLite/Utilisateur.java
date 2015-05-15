package SqlLite;

/**
 * Created by mehdibeggas on 15/05/2015.
 */
public class Utilisateur {
    private long id;
    private String login;
    private String mdp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return login;
    }
}

