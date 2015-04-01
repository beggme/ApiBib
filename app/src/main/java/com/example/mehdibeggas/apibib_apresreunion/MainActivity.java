package com.example.mehdibeggas.apibib_apresreunion;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;

import activity.Login;


public class MainActivity extends ActionBarActivity {

    final String EXTRA_WIDTH = "screen_width";
    final String EXTRA_HEIGHT = "screen_height";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MainActivity.this, Login.class);

        Display myDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        myDisplay.getSize(point);
        int width = point.x;
        int height = point.y;

        intent.putExtra(EXTRA_WIDTH, width);
        intent.putExtra(EXTRA_HEIGHT, width);

        startActivity(intent);

    }

}
