package com.example.stropheum.speedcalculatortest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainMenu extends ActionBarActivity {
    LinearLayout[] layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        layout = new LinearLayout[5];

        // Initialize click listeners for workout buttons
        configureWorkoutButtonOne();
        configureWorkoutButtonTwo();
        configureWorkoutButtonThree();
        configureWorkoutButtonFour();
        configureWorkoutButtonFive();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Configures the click listener for workout button one
     */
    private void configureWorkoutButtonOne() {
        layout[0] = (LinearLayout) findViewById(R.id.row_1);

        layout[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), IntervalWorkoutActivity.class));
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button two
     */
    private void configureWorkoutButtonTwo() {
        layout[1] = (LinearLayout) findViewById(R.id.row_2);

        layout[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), IntervalWorkoutActivity.class));
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button three
     */
    private void configureWorkoutButtonThree() {
        layout[2] = (LinearLayout) findViewById(R.id.row_3);

        layout[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), IntervalWorkoutActivity.class));
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button four
     */
    private void configureWorkoutButtonFour() {
        layout[3] = (LinearLayout) findViewById(R.id.row_4);

        layout[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3].setBackgroundColor(0xFFe1e1e1);
                        break;
                    case MotionEvent.ACTION_UP:
                        layout[3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), IntervalWorkoutActivity.class));
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button five
     */
    private void configureWorkoutButtonFive() {
        layout[4] = (LinearLayout) findViewById(R.id.row_5);

        layout[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), IntervalWorkoutActivity.class));
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }
}