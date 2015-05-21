package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.Date;
import java.util.List;

import SqlLite.ApiBibDataSource;
import SqlLite.Repas;
import main.Singleton;
import objets.FichierTxtAsyncTask;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Indicateurs extends MenuNavigation{

    ApiBibDataSource apiBibDataSource;
    Singleton singleton = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicateurs);

        apiBibDataSource = new ApiBibDataSource(this);

    }

    public void synchronisation(View view){

        long heureDernierRepas,heureNouveauBiberon;

        apiBibDataSource.open();

        List<Repas> listeRepas = apiBibDataSource.getRepas(singleton.getBebe());
        Repas dernierRepas = listeRepas.get(listeRepas.size() - 1);

        heureDernierRepas = dernierRepas.getDate_heure().getTime();
        heureNouveauBiberon = new Date().getTime();

        //if(heureNouveauBiberon - heureDernierRepas )

        String urlFichierTxt = "http://projectapibib.pe.hu/upload/data.txt";

        FichierTxtAsyncTask monAsyncTask = new FichierTxtAsyncTask(Indicateurs.this,apiBibDataSource);
        monAsyncTask.execute(urlFichierTxt);

        TextView textView = (TextView) findViewById(R.id.vitesse);
        textView.setText("Vitesse : 3");

    }

}
