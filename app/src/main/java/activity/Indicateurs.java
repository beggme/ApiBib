package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import objets.FichierTxtAsyncTask;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Indicateurs extends MenuNavigation{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicateurs);

    }

    public void synchronisation(View view){

        String urlFichierTxt = "http://projectapibib.pe.hu/upload/data.txt";

        FichierTxtAsyncTask monAsyncTask = new FichierTxtAsyncTask(Indicateurs.this);
        monAsyncTask.execute(urlFichierTxt);

        TextView textView = (TextView) findViewById(R.id.duree);
        textView.setText("Durée : 20 minutes");

        textView = (TextView) findViewById(R.id.vitesse);
        textView.setText("Vitesse : 3");

    }

}
