package SqlLite;

/**
 * Created by mehdibeggas on 11/05/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ApiBibDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] whereArgs;

    private String[] allColumnsUtilisateur = { MySQLiteHelper.COLUMN_ID_UTILISATEUR,
            MySQLiteHelper.COLUMN_LOGIN, MySQLiteHelper.COLUMN_MDP};

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

    public List<Utilisateur> getAllUtilisateur() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        whereArgs = new String[]{"Mehdi"};

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

    public List<Utilisateur> getAllUtilisateur(String login) {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        whereArgs = new String[]{login};

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

    private Utilisateur cursorToUtilisateur(Cursor cursor) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(cursor.getLong(0));
        utilisateur.setLogin(cursor.getString(1));
        utilisateur.setMdp(cursor.getString(2));
        return utilisateur;
    }
}
