package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Login extends Activity{

    final String EXTRA_WIDTH = "screen_width";
    final String EXTRA_HEIGHT = "screen_height";

    private Intent intent;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = getIntent();

        if (intent != null){

            width = intent.getIntExtra(EXTRA_WIDTH, 0);
            height = intent.getIntExtra(EXTRA_HEIGHT, 0);

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