package main;

import SqlLite.Bebe;
import SqlLite.Utilisateur;
import objets.Resolution;

/**
 * Created by mehdibeggas on 17/05/2015.
 */
public class Singleton {

    private static Singleton instance;
    private Resolution resolution;
    private Utilisateur utilisateur;
    private Bebe bebe;

    private Singleton(){
    }

    public static Singleton getInstance(){
        if (instance==null)
            instance=new Singleton();
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
