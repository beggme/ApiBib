package objets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;

import com.github.mikephil.charting.listener.OnChartGestureListener;

import SqlLite.Repas;

/**
 * Created by kevinbettencourt on 15/05/2015.
 */
public class OnChartRepasGestureListener implements OnChartGestureListener {
    private Repas repas;
    private Activity activity;

    public OnChartRepasGestureListener(Activity activity, Repas repas){
        this.repas = repas;
        this.activity = activity;
    }

    @Override
    public void onChartLongPressed(MotionEvent motionEvent) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent motionEvent) {
        final AlertDialog recapRepas = new AlertDialog.Builder(activity).create();
        recapRepas.setTitle("Récapitulatif du repas");
        recapRepas.setMessage("Bébé a bu " + repas.getQuantite() + "ml en" + repas.getDuree()
                + " min");

        //View repasAModifier = new EditText(activity);

        //recapRepas.setView(repasAModifier);

        recapRepas.setButton(DialogInterface.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recapRepas.hide();
            }
        });
        recapRepas.setButton(DialogInterface.BUTTON_POSITIVE,"Modifier",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                modifierRepas(repas);
            }
        });

        recapRepas.show();
    }

    private void modifierRepas(Repas repas) {
        //intent = new Intent(this.activity, Modifier_Repas.class);
    }

    @Override
    public void onChartSingleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {

    }

    @Override
    public void onChartScale(MotionEvent motionEvent, float v, float v2) {

    }

    @Override
    public void onChartTranslate(MotionEvent motionEvent, float v, float v2) {

    }
}
