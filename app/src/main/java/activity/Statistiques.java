package activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import SqlLite.Repas;

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
        btFragmentGraphiqueSemaine=(Button) findViewById(R.id.bt_fragmentGraphiqueSemaine);
        btFragmentGraphiqueMois=(Button) findViewById(R.id.bt_fragmentGraphiqueMois);

        btFragmentGraphiqueJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ici la requête pour récupérer les quantités bues de la journée
                HashMap<Float,Integer>quantite = new HashMap<Float, Integer>();

                Date dateDuJour = new Date();
                List<Repas> listeRepasJour = getRepasJour(dateDuJour);

                if(listeRepasJour != null && !listeRepasJour.isEmpty()){
                    fragment_graphique = Fragment_Graphique.newInstance(listeRepasJour,1);
                }

                else {
                    quantite.put(4f, 0);
                    quantite.put(8f, 1);
                    quantite.put(6f, 2);
                    quantite.put(12f, 3);
                    quantite.put(18f, 4);
                    quantite.put(9f, 5);

                    fragment_graphique = Fragment_Graphique.newInstance(quantite, 1);
                }

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

                fragment_graphique = Fragment_Graphique.newInstance(quantite,2);

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

                fragment_graphique = Fragment_Graphique.newInstance(quantite,3);

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                ft.replace(R.id.container_fragment, fragment_graphique);

                ft.commit();
            }
        });
    }

    public List<Repas>getRepasJour(Date date){
        Repas repas = new Repas();
        repas.setDate_heure(new Date());
        repas.setDuree(10);
        repas.setQuantite(100);

        List<Repas>list=new ArrayList<Repas>();
        list.add(repas);

        repas = new Repas();
        repas.setDate_heure(new Date(new Date().getTime()+(3600*1000)));
        repas.setDuree(100);
        repas.setQuantite(1000);
        list.add(repas);

        repas = new Repas();
        repas.setDate_heure(new Date(new Date().getTime()+(2*3600*1000)));
        repas.setDuree(1);
        repas.setQuantite(10);
        list.add(repas);

        return list;
    }
}