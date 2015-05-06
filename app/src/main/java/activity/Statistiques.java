package activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mehdibeggas.apibib_apresreunion.R;

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
                fragment_graphique = new Fragment_Graphique();

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                ft.replace(R.id.container_fragment, fragment_graphique);

                ft.commit();
            }
        });
    }
}