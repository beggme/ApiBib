package activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.HashMap;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class Statistiques extends MenuNavigation {

    FragmentManager fm;
    FragmentTransaction ft;

    Fragment_Graphique fragment_graphique;
    Button btFragmentGraphiqueJour,btFragmentGraphiqueSemaine,btFragmentGraphiqueMois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

        btFragmentGraphiqueJour=(Button) findViewById(R.id.bt_fragmentGraphiqueJour);
        btFragmentGraphiqueSemaine=(Button) findViewById(R.id.bt_fragmentGraphiqueMois);
        btFragmentGraphiqueMois=(Button) findViewById(R.id.bt_fragmentGraphiqueSemaine);

        btFragmentGraphiqueJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ici la requête pour récupérer les quantités bues de la journée
                HashMap<Float,Integer>quantite = new HashMap<Float, Integer>();
                quantite.put(4f, 0);
                quantite.put(8f, 1);
                quantite.put(6f, 2);
                quantite.put(12f, 3);
                quantite.put(18f, 4);
                quantite.put(9f, 5);

                fragment_graphique = Fragment_Graphique.newInstance(quantite);

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                ft.replace(R.id.container_fragment, fragment_graphique);

                ft.commit();
            }
        });

        btFragmentGraphiqueSemaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ici la requête pour récupérer les quantités bues de la semaine
                HashMap<Float,Integer>quantite = new HashMap<Float, Integer>();
                quantite.put(4f, 0);
                quantite.put(8f, 1);
                quantite.put(6f, 2);
                quantite.put(12f, 3);
                quantite.put(18f, 4);
                quantite.put(9f, 5);

                fragment_graphique = Fragment_Graphique.newInstance(quantite);

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                ft.replace(R.id.container_fragment, fragment_graphique);

                ft.commit();
            }
        });

        btFragmentGraphiqueMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ici la requête pour récupérer les quantités bues du mois
                HashMap<Float,Integer>quantite = new HashMap<Float, Integer>();
                quantite.put(4f, 0);
                quantite.put(8f, 1);
                quantite.put(6f, 2);
                quantite.put(12f, 3);
                quantite.put(18f, 4);
                quantite.put(9f, 5);

                fragment_graphique = Fragment_Graphique.newInstance(quantite);

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                ft.replace(R.id.container_fragment, fragment_graphique);

                ft.commit();
            }
        });
    }
}