package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Statistiques extends MenuNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

    }

    public void synchronisation(View view){
        TextView textView = (TextView) findViewById(R.id.duree);
        textView.setText("Durée : 20 minutes");

        textView = (TextView) findViewById(R.id.quInitiale);
        textView.setText("Quantité initiale : 200 ml");

        textView = (TextView) findViewById(R.id.quBue);
        textView.setText("Quantité bue : 150 ml");

        textView = (TextView) findViewById(R.id.vitesse);
        textView.setText("Vitesse : 3");

    }

}