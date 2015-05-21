package main;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehdibeggas.apibib_apresreunion.R;

import java.text.SimpleDateFormat;

import activity.ParametrageAlertes;

/**
 * Created by Tim on 21/05/2015.
 */
// CountDownTimer class
public class ApiBibCountDownTimer extends CountDownTimer
{
    private Activity activity;
    private TextView text;
    private Singleton s;
    private Intent intent;

    public ApiBibCountDownTimer(long startTime, long interval,Activity activity)
    {
        super(startTime, interval);
        this.activity = activity;
        text = (TextView)activity.findViewById(R.id.timeRemain);
        s = Singleton.getInstance();
    }

    @Override
    public void onFinish()
    {
        text.setText("Time's up!");
        s.setTimerHasStarted(false);
        intent = new Intent(activity, ParametrageAlertes.class);
        //timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        convert(millisUntilFinished);
        s.setTimeRemain(millisUntilFinished);
        //timeElapsed = startTime - millisUntilFinished;
        //timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
    }

    void convert(long timems)
    {
        long heure;
        long min;
        long sec;
        long ms;
        long val = 1000*3600;
        long rest;
        heure = timems/val;
        rest = timems - heure*val;
        val /= 60;
        min = rest/val;
        rest = rest - min *val;
        val /= 60;
        sec = rest/val;
        ms = rest - sec *val;

        text.setText(heure  + " h " + min + " m " + sec + " s ");

    }
}