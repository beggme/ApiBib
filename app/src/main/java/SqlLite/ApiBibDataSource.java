package SqlLite;

/**
 * Created by mehdibeggas on 11/05/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiBibDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] whereArgs;

    DateFormat format_date_heure = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    DateFormat format_date = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat format_heure = new SimpleDateFormat("HH:mm");

    private String[] allColumnsUtilisateur = { MySQLiteHelper.COLUMN_ID_UTILISATEUR,
            MySQLiteHelper.COLUMN_LOGIN, MySQLiteHelper.COLUMN_MDP};

    private String[] allColumnsBebe = { MySQLiteHelper.COLUMN_ID_BEBE,
            MySQLiteHelper.COLUMN_NOM, MySQLiteHelper.COLUMN_PRENOM, MySQLiteHelper.COLUMN_AGE,
            MySQLiteHelper.COLUMN_POIDS, MySQLiteHelper.COLUMN_REF_UTILISATEUR};

    private String[] allColumnsAlerte = { MySQLiteHelper.COLUMN_ID_ALERTE,
            MySQLiteHelper.COLUMN_REF_BEBE, MySQLiteHelper.COLUMN_DATE_ALERTE, MySQLiteHelper.COLUMN_HEURE_ALERTE};

    private String[] allColumnsRepas = { MySQLiteHelper.COLUMN_ID_REPAS,
            MySQLiteHelper.COLUMN_QUANTITE, MySQLiteHelper.COLUMN_DUREE,
            MySQLiteHelper.COLUMN_DATE_REPAS, MySQLiteHelper.COLUMN_HEURE_REPAS, MySQLiteHelper.COLUMN_REF_BEBE};

    public ApiBibDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Utilisateur createUtilisateur(String login, String mdp) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_LOGIN, login);
        values.put(MySQLiteHelper.COLUMN_MDP, mdp);
        long insertId = database.insert(MySQLiteHelper.TABLE_UTILISATEURS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_UTILISATEURS,
                allColumnsUtilisateur, MySQLiteHelper.COLUMN_ID_UTILISATEUR + " = " + insertId, null,
                null, null, null, null);
        cursor.moveToFirst();
        Utilisateur newUtilisateur = cursorToUtilisateur(cursor);
        cursor.close();
        return newUtilisateur;
    }

    public void deleteUtilisateur(Utilisateur utilisateur) {
        long id = utilisateur.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_UTILISATEURS, MySQLiteHelper.COLUMN_ID_UTILISATEUR
                + " = " + id, null);
    }

    public void deleteAllUtilisateur() {
        database.delete(MySQLiteHelper.TABLE_UTILISATEURS, null, null);
    }

    public List<Utilisateur> getUtilisateur() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_UTILISATEURS,
                allColumnsUtilisateur, MySQLiteHelper.COLUMN_LOGIN + "=?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Utilisateur utilisateur = cursorToUtilisateur(cursor);
            utilisateurs.add(utilisateur);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return utilisateurs;
    }

    public List<Utilisateur> getUtilisateur(String login) {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Utilisateur utilisateur;

        whereArgs = new String[]{login};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_UTILISATEURS,
                allColumnsUtilisateur, MySQLiteHelper.COLUMN_LOGIN + "=?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            utilisateur = cursorToUtilisateur(cursor);
            utilisateurs.add(utilisateur);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return utilisateurs;
    }

    private Utilisateur cursorToUtilisateur(Cursor cursor) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(cursor.getLong(0));
        utilisateur.setLogin(cursor.getString(1));
        utilisateur.setMdp(cursor.getString(2));
        return utilisateur;
    }

    public Bebe createBebe(long ref_utilisateur, String nom, String prenom, long age, double poids) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_REF_UTILISATEUR, ref_utilisateur);
        values.put(MySQLiteHelper.COLUMN_NOM, nom);
        values.put(MySQLiteHelper.COLUMN_PRENOM, prenom);
        values.put(MySQLiteHelper.COLUMN_AGE, age);
        values.put(MySQLiteHelper.COLUMN_POIDS, poids);
        long insertId = database.insert(MySQLiteHelper.TABLE_BEBE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BEBE,
                allColumnsBebe, MySQLiteHelper.COLUMN_REF_UTILISATEUR + " = " + insertId, null,
                null, null, null, null);
        cursor.moveToFirst();
        Bebe newBebe = cursorToBebe(cursor);
        cursor.close();
        return newBebe;
    }

    public Bebe modifBebe(Bebe bebe) {

        long idBebe = bebe.getId();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM, bebe.getNom());
        values.put(MySQLiteHelper.COLUMN_PRENOM, bebe.getPrenom());
        values.put(MySQLiteHelper.COLUMN_AGE, bebe.getAge());
        values.put(MySQLiteHelper.COLUMN_POIDS, bebe.getPoids());

        database.update(MySQLiteHelper.TABLE_BEBE, values, MySQLiteHelper.COLUMN_ID_BEBE + "=?", new String[]{"idBebe"});

        return bebe;
    }

    public void deleteBebe(Bebe bebe) {
        long id = bebe.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_BEBE, MySQLiteHelper.COLUMN_ID_BEBE
                + " = " + id, null);
    }

    public void deleteAllBebe() {
        database.delete(MySQLiteHelper.TABLE_BEBE, null, null);
    }

    public List<Bebe> getBebe() {
        List<Bebe> bebes = new ArrayList<Bebe>();
        Bebe bebe;

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BEBE,
                allColumnsBebe, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            bebe = cursorToBebe(cursor);
            bebes.add(bebe);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return bebes;
    }

    public List<Bebe> getBebe(Utilisateur utilisateur) {
        List<Bebe> bebes = new ArrayList<Bebe>();
        Bebe bebe;

        whereArgs = new String[]{"" + utilisateur.getId()};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BEBE,
                allColumnsBebe, MySQLiteHelper.COLUMN_REF_UTILISATEUR + "=?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            bebe = cursorToBebe(cursor);
            bebes.add(bebe);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return bebes;
    }

    private Bebe cursorToBebe(Cursor cursor) {
        Bebe bebe = new Bebe();
        bebe.setId(cursor.getLong(0));
        bebe.setNom(cursor.getString(1));
        bebe.setPrenom(cursor.getString(2));
        bebe.setAge(cursor.getInt(3));
        bebe.setPoids(cursor.getDouble(4));
        bebe.setRef_utilisateur(cursor.getInt(5));
        return bebe;
    }

    public Repas createRepas(long quantite, long duree, Date date_heure, long ref_bebe) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_REF_BEBE, ref_bebe);
        values.put(MySQLiteHelper.COLUMN_QUANTITE, quantite);
        values.put(MySQLiteHelper.COLUMN_DUREE, duree);
        values.put(MySQLiteHelper.COLUMN_DATE_REPAS, format_date.format(date_heure));
        values.put(MySQLiteHelper.COLUMN_HEURE_REPAS, format_heure.format(date_heure));
        long insertId = database.insert(MySQLiteHelper.TABLE_REPAS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_REPAS,
                allColumnsRepas, MySQLiteHelper.COLUMN_ID_REPAS + " = " + insertId, null,
                null, null, null, null);
        cursor.moveToFirst();
        Repas newRepas = cursorToRepas(cursor);
        cursor.close();
        return newRepas;
    }

    public void deleteRepas(Repas repas) {
        long id = repas.getId();
        System.out.println("Repas deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_REPAS, MySQLiteHelper.COLUMN_ID_REPAS
                + " = " + id, null);
    }

    public void deleteAllRepas() {
        database.delete(MySQLiteHelper.TABLE_REPAS, null, null);
    }

    public List<Repas> getRepas() {
        List<Repas> repass = new ArrayList<Repas>();
        Repas repas;

        Cursor cursor = database.query(MySQLiteHelper.TABLE_REPAS,
                allColumnsBebe, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            repas = cursorToRepas(cursor);
            repass.add(repas);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return repass;
    }

    public List<Repas> getRepas(Bebe bebe) {
        List<Repas> repass = new ArrayList<Repas>();
        Repas repas;

        whereArgs = new String[]{"" + bebe.getId()};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_REPAS,
                allColumnsRepas, MySQLiteHelper.COLUMN_REF_BEBE + "=?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            repas = cursorToRepas(cursor);
            repass.add(repas);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return repass;
    }

    public List<Repas> getRepas(Bebe bebe, Date date) {
        List<Repas> repass = new ArrayList<Repas>();
        Repas repas;

        whereArgs = new String[]{"" + bebe.getId(), format_date.format(date)};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_REPAS,
                allColumnsRepas, MySQLiteHelper.COLUMN_REF_BEBE + "=? and " +
                        MySQLiteHelper.COLUMN_DATE_REPAS + " =?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            repas = cursorToRepas(cursor);
            repass.add(repas);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return repass;
    }

    private Repas cursorToRepas(Cursor cursor){
        Repas repas = new Repas();
        repas.setId(cursor.getLong(0));
        repas.setQuantite(cursor.getInt(1));
        repas.setDuree(cursor.getInt(2));

        try {
            repas.setDate_heure(format_date_heure.parse(cursor.getString(3) + " " + cursor.getString(4)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        repas.setRef_bebe(cursor.getLong(4));
        return repas;
    }

    public Alerte createAlerte(long ref_bebe, Date date_heure) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_REF_BEBE, ref_bebe);
        values.put(MySQLiteHelper.COLUMN_DATE_ALERTE, format_date.format(date_heure));
        values.put(MySQLiteHelper.COLUMN_HEURE_ALERTE, format_heure.format(date_heure));
        long insertId = database.insert(MySQLiteHelper.TABLE_ALERTE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ALERTE,
                allColumnsAlerte, MySQLiteHelper.COLUMN_ID_ALERTE + " = " + insertId, null,
                null, null, null, null);
        cursor.moveToFirst();
        Alerte newAlerte = cursorToAlerte(cursor);
        cursor.close();
        return newAlerte;
    }

    public void deleteAlerte(Alerte alerte) {
        long id = alerte.getId();
        System.out.println("Alerte deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_ALERTE, MySQLiteHelper.COLUMN_ID_ALERTE
                + " = " + id, null);
    }

    public void deleteAllAlerte() {
        database.delete(MySQLiteHelper.TABLE_ALERTE, null, null);
    }

    public List<Alerte> getAlerte() {
        List<Alerte> alertes = new ArrayList<Alerte>();
        Alerte alerte;

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ALERTE,
                allColumnsAlerte, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alerte = cursorToAlerte(cursor);
            alertes.add(alerte);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alertes;
    }

    public List<Alerte> getAlerte(Bebe bebe) {
        List<Alerte> alertes = new ArrayList<Alerte>();
        Alerte alerte;

        whereArgs = new String[]{"" + bebe.getId()};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ALERTE,
                allColumnsAlerte, MySQLiteHelper.COLUMN_REF_BEBE + "=?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alerte = cursorToAlerte(cursor);
            alertes.add(alerte);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alertes;
    }

    public List<Alerte> getAlerte(Bebe bebe, Date date) {
        List<Alerte> alertes = new ArrayList<Alerte>();
        Alerte alerte;

        whereArgs = new String[]{"" + bebe.getId(), format_date.format(date)};

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ALERTE,
                allColumnsAlerte, MySQLiteHelper.COLUMN_REF_BEBE + "=? and " +
                        MySQLiteHelper.COLUMN_DATE_ALERTE + " =?", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alerte = cursorToAlerte(cursor);
            alertes.add(alerte);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alertes;
    }

    private Alerte cursorToAlerte(Cursor cursor){
        Alerte alerte = new Alerte();
        alerte.setId(cursor.getLong(0));
        alerte.setRef_bebe(cursor.getLong(1));

        try {
            alerte.setDate_heure(format_date_heure.parse(cursor.getString(2) + " " + cursor.getString(3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return alerte;
    }
}
