package activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import main.Singleton;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class ParametresInitiaux extends MenuNavigation {

    private TextView nom, prenom, dateNaissance, poids;
    Singleton singleton = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_initiaux);

        nom = (TextView) findViewById(R.id.nomBebe);
        nom.setText(singleton.getBebe().getNom());

        prenom = (TextView) findViewById(R.id.prenomBebe);
        prenom.setText(singleton.getBebe().getPrenom());

        dateNaissance = (TextView) findViewById(R.id.dateNaissance);
        dateNaissance.setText(Long.toString(singleton.getBebe().getAge()));

        poids = (TextView) findViewById(R.id.poids);
        poids.setText(Double.toString(singleton.getBebe().getPoids()));

    }

}
