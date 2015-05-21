package activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mehdibeggas.apibib_apresreunion.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Date;

import SqlLite.ApiBibDataSource;
import SqlLite.Utilisateur;
import main.Singleton;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    private Intent intent;
    CallbackManager callbackManager;

    private EditText login, mdp;

    private ApiBibDataSource dataSource;
    private Singleton singleton = Singleton.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        dataSource = new ApiBibDataSource(this);
        dataSource.open();

        intent = getIntent();

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            // TODO g�rer le callback et les hashset
            @Override
            public void onSuccess(LoginResult loginResult) {
                // TODO r�cup�rer l'adresse mail
            }

            @Override
            public void onCancel() {
                // TODO Afficher message annuler
            }

            @Override
            public void onError(FacebookException exc) {
                // TODO Afficher message erreur
            }
        });
    }

    public boolean connexion(View v){
        boolean premiereConnexion = false;

        if(premiereConnexion) {
            intent = new Intent(this, ParametresInitiaux.class);
        }
        else{
            login = (EditText) findViewById(R.id.login);
            mdp = (EditText) findViewById(R.id.mdp);

            Utilisateur utilisateur = null;

            if(dataSource.getUtilisateur(login.getText().toString()) != null
                    && !dataSource.getUtilisateur(login.getText().toString()).isEmpty()) {
                utilisateur = dataSource.getUtilisateur(login.getText().toString()).get(0);
            }

            if(utilisateur != null) {
                if (mdp.getText().toString().equals(utilisateur.getMdp())) {

                    singleton.setUtilisateur(utilisateur);
                    singleton.setBebe(dataSource.getBebe(utilisateur).get(0));

                    intent = new Intent(this, Indicateurs.class);
                    dataSource.close();
                    startActivity(intent);
                }
                else{
                    final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                    alertDialog.setTitle("Erreur d'authentification");
                    alertDialog.setMessage("Mot de passe incorrect");
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Modifier", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.hide();
                        }
                    });
                    alertDialog.show();
                }
            }
            else{
                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                alertDialog.setTitle("Erreur d'identification");
                alertDialog.setMessage("Utilisateur inconnu");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.hide();
                    }
                });
                alertDialog.show();
            }
        }
        return false;
    }

    public void initialiserBDD(View v){
        dataSource.deleteAllUtilisateur();
        dataSource.deleteAllBebe();
        dataSource.deleteAllRepas();

        Utilisateur imane = dataSource.createUtilisateur("imane", "yousim");
        Utilisateur mehdi = dataSource.createUtilisateur("mehdi", "beggme");
        Utilisateur kevin = dataSource.createUtilisateur("kevin", "bettke");
        Utilisateur tim = dataSource.createUtilisateur("timothée", "delati");
        Utilisateur matt = dataSource.createUtilisateur("matthieu", "fravma");

        dataSource.createBebe(imane.getId(),"Youssouf","Imane",1,4);
        dataSource.createBebe(mehdi.getId(),"Beggas","Panda",1,4);
        dataSource.createBebe(kevin.getId(),"Bettencourt","Kevin",1,4);
        dataSource.createBebe(tim.getId(),"Delauriere","Timotchuky",1,4);
        dataSource.createBebe(matt.getId(),"Fravallo","Silvio",1,4);

        dataSource.createRepas(150,2,new Date(),kevin.getId());

        System.out.println("BDD créé");

    }

}