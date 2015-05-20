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

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    private Intent intent;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

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

        intent = new Intent(this, ParametresInitiaux.class);
        startActivity(intent);
        return false;

    }

}