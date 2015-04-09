package SqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mehdibeggas on 07/04/2015.
 */
public class BddCreation extends SQLiteOpenHelper {

    private static final String TABLE_UTILISATEUR = "Utilisateur";
    private static final String TABLE_ALERTE = "Alerte";
    private static final String TABLE_REPAS = "Repas";
    private static final String TABLE_BEBE = "Bebe";

    private static final String COL_ID = "ID";
    private static final String COL_LOGIN = "Login";
    private static final String COL_MDP = "Mdp";

    private static final String COL_NOM = "Nom";
    private static final String COL_PRENOM = "Prenom";
    private static final String COL_AGE = "Age";
    private static final String COL_POIDS = "Poids";
    private static final String COL_REF_UTILISATEUR = "Ref_utilisateur";

    private static final String COL_DATE_HEURE = "Date_heure";
    private static final String COL_REF_BEBE = "Ref_bebe";
    private static final String COL_QUANTITE = "Quantite";

    private static final String CREATE_UTILISATEUR = "CREATE TABLE " + TABLE_UTILISATEUR + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_LOGIN + " TEXT NOT NULL, " +
            COL_MDP + " TEXT NOT NULL, " +
                "PRIMARY KEY ( " + COL_ID + " ) " +" )";

    private static final String CREATE_BEBE = "CREATE TABLE " + TABLE_BEBE + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOM + " TEXT NOT NULL, " +
            COL_PRENOM + " TEXT NOT NULL, " +
            COL_AGE + " REAL NOT NULL, " +
            COL_POIDS + " REAL NOT NULL, " +
            COL_REF_UTILISATEUR + " INTEGER NOT NULL, " +
                "PRIMARY KEY ( " + COL_ID + " ), " +
                "FOREIGN KEY(" + COL_REF_UTILISATEUR + " ) REFERENCES " +
                TABLE_UTILISATEUR + "(" + COL_ID + ") )";

    private static final String CREATE_ALERTE = "CREATE TABLE " + TABLE_ALERTE + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE_HEURE + " TEXT NOT NULL, " +
            COL_REF_BEBE + " INTEGER NOT NULL, " +
            "PRIMARY KEY ( " + COL_ID + " ), " +
                "FOREIGN KEY(" + COL_REF_BEBE + " ) REFERENCES " +
                TABLE_BEBE + "(" + COL_ID + ") )";

    private static final String CREATE_REPAS = "CREATE TABLE " + TABLE_REPAS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE_HEURE + " TEXT NOT NULL, " +
            COL_QUANTITE + " REAL NOT NULL, " +
            COL_REF_BEBE + " INTEGER NOT NULL, " +
            "PRIMARY KEY ( " + COL_ID + " ), " +
                "FOREIGN KEY(" + COL_REF_BEBE + " ) REFERENCES " +
                TABLE_BEBE + "(" + COL_ID + ") )";


    public BddCreation(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_UTILISATEUR);
        db.execSQL(CREATE_BEBE);
        db.execSQL(CREATE_ALERTE);
        db.execSQL(CREATE_REPAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}