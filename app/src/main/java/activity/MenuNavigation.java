package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import objets.Resolution;

/**
 * Created by mehdibeggas on 01/04/2015.
 */
public class MenuNavigation extends Activity {

    final String RESOLUTION = "resolution";
    private Intent intent;
    private Resolution resolution;

    protected void onCreate(Bundle savedInstanceState) {

        intent = getIntent();

        if (intent != null){
            resolution = intent.getExtras().getParcelable(RESOLUTION);
        }

    }

    public boolean gotoParametrageAlertes(View v) {

        intent = new Intent(this, ParametrageAlertes.class);
        intent.putExtra(RESOLUTION, resolution);
        startActivity(intent);
        return false;

    }

    public boolean gotoStatistiques(View v) {

        intent = new Intent(this, Statistiques.class);
        intent.putExtra(RESOLUTION, resolution);
        startActivity(intent);
        return false;

    }

    public boolean gotoParametres(View v) {

        intent = new Intent(this, Parametres.class);
        intent.putExtra(RESOLUTION, resolution);
        startActivity(intent);
        return false;

    }

}
