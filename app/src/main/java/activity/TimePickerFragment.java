package activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.Calendar;

import main.ApiBibCountDownTimer;
import main.Singleton;

/**
 * Created by mehdibeggas on 09/04/2015.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = 2;
        int minute = 0;

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Singleton s = Singleton.getInstance();

        int startTime = hourOfDay*3600*1000 + minute*60*1000;
        TextView text = (TextView) getActivity().findViewById(R.id.timeRemain);
        text.setText(hourOfDay  + " h " + minute + " m 00 s ");
        ApiBibCountDownTimer countDownTimer = new ApiBibCountDownTimer(startTime,1000,getActivity());
        s.setTimerCount(countDownTimer);
    }
}