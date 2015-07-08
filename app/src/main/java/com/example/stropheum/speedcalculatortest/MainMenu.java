package com.example.stropheum.speedcalculatortest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainMenu extends ActionBarActivity {
    LinearLayout[][] layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        layout = new LinearLayout[9][7];

        // Initialize click listeners for workout buttons
        configureButton_1_1();
        configureButton_1_2();
        configureButton_1_3();
        configureButton_1_4();
        configureButton_1_5();
        configureButton_1_6();
        configureButton_1_7();

        configureButton_2_1();
        configureButton_2_2();
        configureButton_2_3();
        configureButton_2_4();
        configureButton_2_5();
        configureButton_2_6();
        configureButton_2_7();

        configureButton_3_1();
        configureButton_3_2();
        configureButton_3_3();
        configureButton_3_4();
        configureButton_3_5();
        configureButton_3_6();
        configureButton_3_7();
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
    private void configureButton_1_1() {
        layout[0][0] = (LinearLayout) findViewById(R.id.row_1_1);

        layout[0][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_1.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button two
     */
    private void configureButton_1_2() {
        layout[0][1] = (LinearLayout) findViewById(R.id.row_1_2);

        layout[0][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button three
     */
    private void configureButton_1_3() {
        layout[0][2] = (LinearLayout) findViewById(R.id.row_1_3);

        layout[0][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button four
     */
    private void configureButton_1_4() {
        layout[0][3] = (LinearLayout) findViewById(R.id.row_1_4);

        layout[0][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][3].setBackgroundColor(0xFFe1e1e1);
                        break;
                    case MotionEvent.ACTION_UP:
                        layout[0][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button five
     */
    private void configureButton_1_5() {
        layout[0][4] = (LinearLayout) findViewById(R.id.row_1_5);

        layout[0][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 1_6
     */
    private void configureButton_1_6() {
        layout[0][5] = (LinearLayout) findViewById(R.id.row_1_6);

        layout[0][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 1_7
     */
    private void configureButton_1_7() {
        layout[0][6] = (LinearLayout) findViewById(R.id.row_1_7);

        layout[0][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_1_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_1
     */
    private void configureButton_2_1() {
        layout[1][0] = (LinearLayout) findViewById(R.id.row_2_1);

        layout[1][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_2
     */
    private void configureButton_2_2() {
        layout[1][1] = (LinearLayout) findViewById(R.id.row_2_2);

        layout[1][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_2_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_3
     */
    private void configureButton_2_3() {
        layout[1][2] = (LinearLayout) findViewById(R.id.row_2_3);

        layout[1][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_2_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_4
     */
    private void configureButton_2_4() {
        layout[1][3] = (LinearLayout) findViewById(R.id.row_2_4);

        layout[1][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_2_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_5
     */
    private void configureButton_2_5() {
        layout[1][4] = (LinearLayout) findViewById(R.id.row_2_5);

        layout[1][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_2_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_6
     */
    private void configureButton_2_6() {
        layout[1][5] = (LinearLayout) findViewById(R.id.row_2_6);

        layout[1][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_7
     */
    private void configureButton_2_7() {
        layout[1][6] = (LinearLayout) findViewById(R.id.row_2_7);

        layout[1][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_2_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_1
     */
    private void configureButton_3_1() {
        layout[2][0] = (LinearLayout) findViewById(R.id.row_3_1);

        layout[2][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_2
     */
    private void configureButton_3_2() {
        layout[2][1] = (LinearLayout) findViewById(R.id.row_3_2);

        layout[2][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_3
     */
    private void configureButton_3_3() {
        layout[2][2] = (LinearLayout) findViewById(R.id.row_3_3);

        layout[2][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_4
     */
    private void configureButton_3_4() {
        layout[2][3] = (LinearLayout) findViewById(R.id.row_3_4);

        layout[2][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_5
     */
    private void configureButton_3_5() {
        layout[2][4] = (LinearLayout) findViewById(R.id.row_3_5);

        layout[2][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_6
     */
    private void configureButton_3_6() {
        layout[2][5] = (LinearLayout) findViewById(R.id.row_3_6);

        layout[2][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_6.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_7
     */
    private void configureButton_3_7() {
        layout[2][6] = (LinearLayout) findViewById(R.id.row_3_7);

        layout[2][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_3_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

}