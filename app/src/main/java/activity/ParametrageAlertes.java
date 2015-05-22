package activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.concurrent.TimeUnit;

import main.ApiBibCountDownTimer;
import main.Singleton;

/**
 * Created by timothee Delauriere on 31/03/2015.
 */
public class ParametrageAlertes extends MenuNavigation implements View.OnClickListener
{
    private static final String tag = "Main";
    private ApiBibCountDownTimer countDownTimer;
    private Button startB;
    private Button modifTimer;
    private Button sup;
    private Singleton s;
    private long startTime = 10800000;
    private final long interval = 1000;
    private TextView text;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        s = Singleton.getInstance();
        setContentView(R.layout.activity_parametrage_alerte);

        if(s.IsTimerHasStarted())
        {
            text = (TextView) this.findViewById(R.id.timeRemain);
            text.setText(String.valueOf(s.getTimeRemain()));
            countDownTimer = new ApiBibCountDownTimer(s.getTimeRemain(), interval,this);
            s.setTimerCount(countDownTimer);
            s.setTimerHasStarted(true);
            s.getTimerCount().start();
            //Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
        }
        else
        {
            startB = (Button) this.findViewById(R.id.startTimer);
            startB.setOnClickListener(this);
            modifTimer = (Button) this.findViewById(R.id.modifTimer);
            modifTimer.setOnClickListener(this);
            sup = (Button) this.findViewById(R.id.sup);
            sup.setOnClickListener(this);
            countDownTimer = new ApiBibCountDownTimer(startTime, interval,this);
            s.setTimerCount(countDownTimer);
            s.setTimerHasStarted(false);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.startTimer:
                if (!s.IsTimerHasStarted()) {

                    s.getTimerCount().start();
                    s.setTimerHasStarted(true);
                    //startB.setText("Start");
                } else {

                    s.getTimerCount().cancel();
                    s.setTimerHasStarted(false);
                    //startB.setText("RESET");
                }
            break;

            case R.id.modifTimer:
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timepicker");

            break;
            case R.id.sup:
                s.getTimerCount().cancel();
                text = (TextView) this.findViewById(R.id.timeRemain);
                text.setText("3 h 00 m 00 s ");
                countDownTimer = new ApiBibCountDownTimer(10800000, interval,this);
                s.setTimerCount(countDownTimer);
                s.setTimerHasStarted(false);
            break;

        }
    }
}
