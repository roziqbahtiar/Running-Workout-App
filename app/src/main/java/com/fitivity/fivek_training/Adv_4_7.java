package com.fitivity.fivek_training;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.stropheum.speedcalculatortest.ElevenPartActivity;
import com.example.stropheum.speedcalculatortest.R;


public class Adv_4_7 extends ElevenPartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION      = 525 * 10 * 60;
        PART_TWO_DURATION      = 6 * 1000 * 60;
        PART_THREE_DURATION    = 525 * 10 * 60;
        PART_FOUR_DURATION     = 6 * 1000 * 60;
        PART_FIVE_DURATION     = 525 * 10 * 60;
        PART_SIX_DURATION      = 6 * 1000 * 60;
        PART_SEVEN_DURATION    = 525 * 10 * 60;
        PART_EIGHT_DURATION    = 6 * 1000 * 60;
        PART_NINE_DURATION     = 10 * 1000 * 60;
        PART_TEN_DURATION      = 20 * 1000 * 60;
        PART_ELEVEN_DURATION   = 15 * 1000 * 60;

        PART_ONE_GOAL_PACE      = 5.25;
        PART_TWO_GOAL_PACE      = 15.0;
        PART_THREE_GOAL_PACE    = 5.25;
        PART_FOUR_GOAL_PACE     = 15.0;
        PART_FIVE_GOAL_PACE     = 5.25;
        PART_SIX_GOAL_PACE      = 15.0;
        PART_SEVEN_GOAL_PACE    = 5.25;
        PART_EIGHT_GOAL_PACE    = 15.0;
        PART_NINE_GOAL_PACE     = 15.0;
        PART_TEN_GOAL_PACE      = 5.5;
        PART_ELEVEN_GOAL_PACE   = 15.0;

        PART_ONE_SECONDARY_TITLE    = "5:15 min/mile";
        PART_TWO_SECONDARY_TITLE    = "15:00 min/mile";
        PART_THREE_SECONDARY_TITLE  = "5:15 min/mile";
        PART_FOUR_SECONDARY_TITLE   = "15:00 min/mile";
        PART_FIVE_SECONDARY_TITLE   = "5:15 min/mile";
        PART_SIX_SECONDARY_TITLE    = "15:00 min/mile";
        PART_SEVEN_SECONDARY_TITLE  = "5:15 min/mile";
        PART_EIGHT_SECONDARY_TITLE  = "15:00 min/mile";
        PART_NINE_SECONDARY_TITLE   = "15:00 min/mile";
        PART_TEN_SECONDARY_TITLE    = "5:30 min/mile";
        PART_ELEVEN_SECONDARY_TITLE = "15:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_4_7);
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
