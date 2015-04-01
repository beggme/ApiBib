package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import objets.Resolution;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    final String RESOLUTION = "resolution";
    Resolution resolution;

    private Intent intent;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = getIntent();

        if (intent != null){

            resolution = intent.getExtras().getParcelable(RESOLUTION);

        }

        TextView textView = (TextView)findViewById(R.id.texte);
        textView.setText(Integer.toString(width));

    }

    public boolean connexion(){

        intent = new Intent(this, ParametresInitiaux.class);
        startActivity(intent);
        return false;

    }

}