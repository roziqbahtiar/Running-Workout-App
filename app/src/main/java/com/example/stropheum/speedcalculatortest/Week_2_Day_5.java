package com.example.stropheum.speedcalculatortest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class Week_2_Day_5 extends OnePartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION = 2400 * 1000;

        PART_ONE_GOAL_PACE = 18.0;

        PART_ONE_SECONDARY_TITLE = "18:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_2_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}