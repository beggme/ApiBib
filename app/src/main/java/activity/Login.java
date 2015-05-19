package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mehdibeggas.apibib_apresreunion.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import objets.Resolution;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    final String RESOLUTION = "resolution";
    private Intent intent;
    private Resolution resolution;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        intent = getIntent();

        if (intent != null){
            resolution = intent.getExtras().getParcelable(RESOLUTION);
        }

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            // TODO gérer le callback et les hashset
            @Override
            public void onSuccess(LoginResult loginResult) {
                // TODO récupérer l'adresse mail
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

        intent = new Intent(this, ParametresInitiaux.class);
        intent.putExtra(RESOLUTION, resolution);
        startActivity(intent);
        return false;

    }

}