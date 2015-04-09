package activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import com.example.mehdibeggas.apibib_apresreunion.R;

/**
 * Created by mehdibeggas on 31/03/2015.
 */
public class ParametrageAlertes extends MenuNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrage_alerte);

    }

    public void openTimePicker(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timepicker");
    }

}
