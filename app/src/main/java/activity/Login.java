package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehdibeggas.apibib_apresreunion.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Date;
import java.util.List;

import SqlLite.ApiBibDataSource;
import SqlLite.Bebe;
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

    private List<Utilisateur> utilisateurs;
    private List<Bebe> bebes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        dataSource = new ApiBibDataSource(this);
        dataSource.open();

        //initialiserBDD();

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

    public void enregistrementUtilisateur (View v){

        login = (EditText) findViewById(R.id.login);
        mdp = (EditText) findViewById(R.id.mdp);

        if (login.getText().toString().matches("") || mdp.getText().toString().matches("")){

            Toast.makeText(Login.this, "Veuillez renseigner un login et un mot de passe.", Toast.LENGTH_SHORT).show();

        }else {
            singleton.setUtilisateur(dataSource.createUtilisateur(login.getText().toString(),
                    mdp.getText().toString()));

            Toast.makeText(Login.this, "Enregistrement réussi", Toast.LENGTH_SHORT).show();

            singleton.setBebe(null);

            intent = new Intent(this, ParametresInitiaux.class);
            dataSource.close();
            startActivity(intent);
        }

    }

    public void connexion (View v){

        login = (EditText) findViewById(R.id.login);
        mdp = (EditText) findViewById(R.id.mdp);

        if (login.getText().toString().matches("") || mdp.getText().toString().matches("")){

            Toast.makeText(Login.this, "Veuillez renseigner votre login et votre mot de passe.",
                    Toast.LENGTH_SHORT).show();

        }else {
            utilisateurs = dataSource.getUtilisateur(login.getText().toString());

            if (utilisateurs.size() > 0 && utilisateurs.get(0).getMdp().equals(mdp.getText().toString())){

                Toast.makeText(Login.this, "Connection réussie",
                        Toast.LENGTH_SHORT).show();

                singleton.setUtilisateur(utilisateurs.get(0));

                bebes = dataSource.getBebe(singleton.getUtilisateur());
                if (bebes.size() > 0){
                    singleton.setBebe(bebes.get(0));
                }

                intent = new Intent(this, ParametresInitiaux.class);
                dataSource.close();
                startActivity(intent);
            }else{
                Toast.makeText(Login.this, "Identifiant ou mot de passe incorrect.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }


    /*public boolean connexion(View v){
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
    }*/

    public void initialiserBDD(){
        dataSource.deleteAllUtilisateur();
        dataSource.deleteAllBebe();
        dataSource.deleteAllRepas();

        Utilisateur a = dataSource.createUtilisateur("a", "a");
        Utilisateur imane = dataSource.createUtilisateur("imane", "yousim");
        Utilisateur mehdi = dataSource.createUtilisateur("mehdi", "beggme");
        Utilisateur kevin = dataSource.createUtilisateur("kevin", "bettke");
        Utilisateur tim = dataSource.createUtilisateur("timothée", "delati");
        Utilisateur matt = dataSource.createUtilisateur("matthieu", "fravma");

        dataSource.createBebe(a.getId(),"Youssouf","Imane",1,4);
        dataSource.createBebe(imane.getId(),"Youssouf","Imane",1,4);
        dataSource.createBebe(mehdi.getId(),"Beggas","Panda",1,4);
        dataSource.createBebe(kevin.getId(),"Bettencourt","Kevin",1,4);
        dataSource.createBebe(tim.getId(),"Delauriere","Timotchuky",1,4);
        dataSource.createBebe(matt.getId(),"Fravallo","Silvio",1,4);

        dataSource.createRepas(150,2,new Date(),kevin.getId());

        System.out.println("BDD créé");

    }

}