package activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import SqlLite.ApiBibDataSource;
import SqlLite.Repas;
import main.Singleton;

/**
 * Created by mehdibeggas on 20/05/2015.
 */
public class Test extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DateFormat format_date_heure = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        Singleton singleton = Singleton.getInstance();
        Repas repas;
        Date date = null;
        try {
            date = format_date_heure.parse("10:30 14-05-2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ApiBibDataSource dataSource = new ApiBibDataSource(this);
        dataSource.open();

        dataSource.deleteAllUtilisateur();
        dataSource.deleteAllBebe();
        dataSource.deleteAllRepas();

        singleton.setUtilisateur(dataSource.createUtilisateur("mehdi", "mdp"));
        singleton.setBebe(dataSource.createBebe(singleton.getUtilisateur().getId(), "abc", "def",
                10, 11.3));
        repas = dataSource.createRepas(120, 20, date, singleton.getBebe().getId());

        List<Repas> repass = dataSource.getRepas(singleton.getBebe());

        dataSource.close();

        EditText login = (EditText) findViewById(R.id.login);

        login.setText(repass.get(0).getDuree() + "");

    }

}
