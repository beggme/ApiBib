package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehdibeggas.apibib_apresreunion.R;

import SqlLite.ApiBibDataSource;
import SqlLite.Bebe;
import main.Singleton;

/**
 * Created by mehdibeggas on 25/03/2015.
 */
public class ParametresInitiaux extends MenuNavigation {

    private TextView nom, prenom, age, poids;
    private Button bouton;
    Singleton singleton = Singleton.getInstance();
    ApiBibDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_initiaux);

        nom = (TextView) findViewById(R.id.nomBebe);
        prenom = (TextView) findViewById(R.id.prenomBebe);
        age = (TextView) findViewById(R.id.age);
        poids = (TextView) findViewById(R.id.poids);
        bouton = (Button) findViewById(R.id.ajoutModifBebe);

        if(singleton.getBebe() != null) {

            bouton.setText("Modifier");

            nom.setText(singleton.getBebe().getNom());
            prenom.setText(singleton.getBebe().getPrenom());
            age.setText(Long.toString(singleton.getBebe().getAge()));
            poids.setText(Double.toString(singleton.getBebe().getPoids()));

        }

    }

    public void ajoutModifBebe (View v) {

        dataSource = new ApiBibDataSource(this);
        dataSource.open();

        if (singleton.getBebe() != null) {

            Bebe bebe = dataSource.modifBebe(singleton.getBebe().getId(), nom.getText().toString(),
                    prenom.getText().toString(), Long.parseLong(age.getText().toString()),
                    Double.parseDouble(poids.getText().toString()));

            singleton.setBebe(bebe);

            Toast.makeText(ParametresInitiaux.this, "Informations modifiées",
                    Toast.LENGTH_SHORT).show();

        } else {

            if (nom.getText().toString().matches("") || prenom.getText().toString().matches("") ||
                    age.getText().toString().matches("") || poids.getText().toString().matches("")) {

                Toast.makeText(ParametresInitiaux.this, "Veuillez renseigner tout les champs",
                        Toast.LENGTH_SHORT).show();

            } else {

                Boolean testVariablesNumeriques = true;
                long ageNum= 0;
                double poidsNum = 0;

                try {
                    ageNum = Long.parseLong(age.getText().toString());
                    poidsNum = Double.parseDouble(poids.getText().toString());
                }
                catch (Exception e) {
                    testVariablesNumeriques = false;
                }

                if(testVariablesNumeriques){

                    singleton.setBebe(dataSource.createBebe(singleton.getUtilisateur().getId(),
                            nom.getText().toString(), prenom.getText().toString(), ageNum, poidsNum));

                    Toast.makeText(ParametresInitiaux.this, "Ajout réussi",
                            Toast.LENGTH_SHORT).show();

                    bouton.setText("Modifier");

                }else{

                    Toast.makeText(ParametresInitiaux.this, "Les champs Age et Poids ne sont pas numériques",
                            Toast.LENGTH_SHORT).show();

                }
            }

        }

        dataSource.close();
    }
}
