package com.example.stropheum.speedcalculatortest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.stropheum.speedcalculatortest.R;

public class SevenPartTest extends OnePartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Goal mile times for each part
        PART_ONE_GOAL_PACE = 10.0;

        // Duration for each part in milliseconds
        PART_ONE_DURATION   = 600 * 1000;

        // Secondary titles for actionbar to set at each part
        // Format: "00:00 min/mile"
        PART_ONE_SECONDARY_TITLE   = "10:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_1_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seven_part_test, menu);
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
