package SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_UTILISATEURS = "Utilisateurs";
    public static final String COLUMN_ID_UTILISATEUR = "_id_utilisateur";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_MDP = "mdp";

    public static final String TABLE_BEBE = "Bebe";
    public static final String COLUMN_ID_BEBE = "_id_bebe";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_PRENOM = "Prenom";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_POIDS = "Poids";
    public static final String COLUMN_REF_UTILISATEUR = "Ref_utilisateur";

    public static final String TABLE_ALERTE = "Alerte";
    public static final String COLUMN_ID_ALERTE = "_id_alerte";

    public static final String TABLE_REPAS = "Repas";
    public static final String COLUMN_ID_REPAS = "_id_repas";
    private static final String COLUMN_REF_BEBE = "Ref_bebe";
    private static final String COLUMN_QUANTITE = "Quantite";
    private static final String COLUMN_DATE_HEURE = "Date_heure";
    private static final String COLUMN_DUREE = "Duree";

    private static final String DATABASE_NAME = "apibib.db";
    private static final int DATABASE_VERSION = 10;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_UTILISATEURS + " ( " + COLUMN_ID_UTILISATEUR
            + " integer primary key autoincrement, " +
            COLUMN_LOGIN + " text not null, " +
            COLUMN_MDP + " text not null);" +

            "create table "
            + TABLE_BEBE + " ( " + COLUMN_ID_BEBE
            + " integer primary key autoincrement, " +
            COLUMN_NOM + " text not null, " +
            COLUMN_PRENOM + " text not null, " +
            COLUMN_AGE + " integer not null, " +
            COLUMN_POIDS + " real not null, " +
            COLUMN_REF_UTILISATEUR + " integer not null, " +
            "FOREIGN KEY " + COLUMN_REF_UTILISATEUR +
            " REFERENCES " + TABLE_UTILISATEURS + " ( " + COLUMN_ID_UTILISATEUR + " );" +

            " create table "
            + TABLE_ALERTE + " ( " + COLUMN_ID_ALERTE
            + " integer primary key autoincrement, " +
            COLUMN_DATE_HEURE + " text not null); " +

            " create table "
            + TABLE_REPAS + "(" + COLUMN_ID_REPAS
            + " integer primary key autoincrement, " +
            COLUMN_QUANTITE + " integer not null, " +
            COLUMN_DUREE + " integer not null, " +
            COLUMN_DATE_HEURE + " text not null, " +
            COLUMN_REF_BEBE + " integer not null, " +
            " FOREIGN KEY " + COLUMN_REF_BEBE +
            " REFERENCES " + TABLE_BEBE + " ( " + COLUMN_ID_BEBE + " ); ";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEURS);
        onCreate(db);
    }

}