package activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.util.concurrent.TimeUnit;

import main.ApiBibCountDownTimer;
import main.Singleton;

/**
 * Created by mehdibeggas on 31/03/2015.
 */
public class ParametrageAlertes extends MenuNavigation implements View.OnClickListener
{
    private static final String tag = "Main";
    private ApiBibCountDownTimer countDownTimer;
    private Button startB;
    private Singleton s;
    private long startTime =10800000;
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
        }
        else
        {
            startB = (Button) this.findViewById(R.id.startTimer);
            startB.setOnClickListener(this);
            countDownTimer = new ApiBibCountDownTimer(startTime, interval,this);
            s.setTimerCount(countDownTimer);
            s.setTimerHasStarted(false);
        }



        //timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);


        //text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View v) {
        if (!s.IsTimerHasStarted()) {
            s.getTimerCount().start();
            s.setTimerHasStarted(true);
            //startB.setText("Start");
        } else {

            s.getTimerCount().cancel();
            s.setTimerHasStarted(true);
            //startB.setText("RESET");
        }
    }
}
