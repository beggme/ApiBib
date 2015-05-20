package main;

import SqlLite.Bebe;
import SqlLite.Utilisateur;
import objets.Resolution;

/**
 * Created by mehdibeggas on 17/05/2015.
 */
public class VariablesGenerales {

    private static VariablesGenerales instance;
    private Resolution resolution;
    private Utilisateur utilisateur;
    private Bebe bebe;

    private VariablesGenerales(){
    }

    public static VariablesGenerales getInstance(){
        if (instance==null)
            instance=new VariablesGenerales();
        return instance;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Bebe getBebe() {
        return bebe;
    }

    public void setBebe(Bebe bebe) {
        this.bebe = bebe;
    }
}
