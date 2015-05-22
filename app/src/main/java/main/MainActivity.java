package main;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;

import activity.Login;
import objets.Resolution;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Singleton singleton = Singleton.getInstance();
        singleton.setResolution(getResolution());

        //Intent intent = new Intent(MainActivity.this, Login.class);
        Intent intent = new Intent(MainActivity.this, Login.class);

        startActivity(intent);
        finish();

    }

    private Resolution getResolution(){

        Display myDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        myDisplay.getSize(point);

        return new Resolution(point.x, point.y);

    }

}
