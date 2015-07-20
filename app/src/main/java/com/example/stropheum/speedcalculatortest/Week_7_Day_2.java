package com.example.stropheum.speedcalculatortest;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Vibrator;
import java.util.Timer;
import java.util.TimerTask;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import com.example.stropheum.speedcalculatortest.SpeedCalculationService.SpeedCalculationBinder;

public class Week_7_Day_2 extends SevenPartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Goal mile times for each part
         PART_ONE_GOAL_PACE   = 10.0;
         PART_TWO_GOAL_PACE   = 11.0;
         PART_THREE_GOAL_PACE = 10.0;
         PART_FOUR_GOAL_PACE  = 11.0;
         PART_FIVE_GOAL_PACE  = 10.0;
         PART_SIX_GOAL_PACE   = 11.0;
         PART_SEVEN_GOAL_PACE = 18.0;

        // Duration for each part in milliseconds
         PART_ONE_DURATION   = 600 * 1000;
         PART_TWO_DURATION   = 600 * 1000;
         PART_THREE_DURATION = 600 * 1000;
         PART_FOUR_DURATION  = 600 * 1000;
         PART_FIVE_DURATION  = 600 * 1000;
         PART_SIX_DURATION   = 600 * 1000;
         PART_SEVEN_DURATION = 600 * 1000;

        // Secondary titles for actionbar to set at each part
         PART_ONE_SECONDARY_TITLE   = "10:00 min/mile";
         PART_TWO_SECONDARY_TITLE   = "11:00 min/mile";
         PART_THREE_SECONDARY_TITLE = "10:00 min/mile";
         PART_FOUR_SECONDARY_TITLE  = "11:00 min/mile";
         PART_FIVE_SECONDARY_TITLE  = "10:00 min/mile";
         PART_SIX_SECONDARY_TITLE   = "11:00 min/mile";
         PART_SEVEN_SECONDARY_TITLE = "18:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_7_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_week_7_day_2, menu);
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