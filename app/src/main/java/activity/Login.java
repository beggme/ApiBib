package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mehdibeggas.apibib_apresreunion.R;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public boolean connexion(View v){

        intent = new Intent(this, ParametresInitiaux.class);
        startActivity(intent);
        return false;

    }

}