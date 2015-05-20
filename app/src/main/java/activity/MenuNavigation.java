package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by mehdibeggas on 01/04/2015.
 */
public class MenuNavigation extends Activity {

    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public boolean gotoParametrageAlertes(View v) {

        intent = new Intent(this, ParametrageAlertes.class);
        startActivity(intent);
        return false;

    }

    public boolean gotoStatistiques(View v) {

        intent = new Intent(this, Statistiques.class);
        startActivity(intent);
        return false;

    }

    public boolean gotoParametres(View v) {

        intent = new Intent(this, ParametresInitiaux.class);
        startActivity(intent);
        return false;

    }

    public boolean gotoIndicateurs(View v) {

        intent = new Intent(this, Indicateurs.class);
        startActivity(intent);
        return false;

    }

}
