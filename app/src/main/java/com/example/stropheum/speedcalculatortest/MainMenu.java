package com.example.stropheum.speedcalculatortest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainMenu extends ActionBarActivity {
    LinearLayout[][] layout;
    CheckBox[] checkBoxArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        // Disable screen timeout while workout is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        layout = new LinearLayout[9][7];
        checkBoxArray = new CheckBox[63];
        getCheckBoxStates();

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

        configureButton_4_1();
        configureButton_4_2();
        configureButton_4_3();
        configureButton_4_4();
        configureButton_4_5();
        configureButton_4_6();
        configureButton_4_7();

        configureButton_5_1();
        configureButton_5_2();
        configureButton_5_3();
        configureButton_5_4();
        configureButton_5_5();
        configureButton_5_6();
        configureButton_5_7();

        configureButton_6_1();
        configureButton_6_2();
        configureButton_6_3();
        configureButton_6_4();
        configureButton_6_5();
        configureButton_6_6();
        configureButton_6_7();

        configureButton_7_1();
        configureButton_7_2();
        configureButton_7_3();
        configureButton_7_4();
        configureButton_7_5();
        configureButton_7_6();
        configureButton_7_7();

        configureButton_8_1();
        configureButton_8_2();
        configureButton_8_3();
        configureButton_8_4();
        configureButton_8_5();
        configureButton_8_6();
        configureButton_8_7();

        configureButton_9_1();
        configureButton_9_2();
        configureButton_9_3();
        configureButton_9_4();
        configureButton_9_5();
        configureButton_9_6();
        configureButton_9_7();

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

    @Override
    public void onPause() {
        super.onPause();
        for (int i = 0; i < checkBoxArray.length; i++) {
            save(i + "", checkBoxArray[i].isChecked());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < checkBoxArray.length; i++) {
            checkBoxArray[i].setChecked(load(i + ""));
        }
    }

    private void save(final String key, final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.commit();
    }

    private boolean load(final String key) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Retrieve the states of all check boxes and stores them in an array
     */
    private void getCheckBoxStates() {
        checkBoxArray[0]  = (CheckBox) findViewById(R.id.box_1_1);
        checkBoxArray[1]  = (CheckBox) findViewById(R.id.box_1_2);
        checkBoxArray[2]  = (CheckBox) findViewById(R.id.box_1_3);
        checkBoxArray[3]  = (CheckBox) findViewById(R.id.box_1_4);
        checkBoxArray[4]  = (CheckBox) findViewById(R.id.box_1_5);
        checkBoxArray[5]  = (CheckBox) findViewById(R.id.box_1_6);
        checkBoxArray[6]  = (CheckBox) findViewById(R.id.box_1_7);
        checkBoxArray[7]  = (CheckBox) findViewById(R.id.box_2_1);
        checkBoxArray[8]  = (CheckBox) findViewById(R.id.box_2_2);
        checkBoxArray[9]  = (CheckBox) findViewById(R.id.box_2_3);
        checkBoxArray[10] = (CheckBox) findViewById(R.id.box_2_4);
        checkBoxArray[11] = (CheckBox) findViewById(R.id.box_2_5);
        checkBoxArray[12] = (CheckBox) findViewById(R.id.box_2_6);
        checkBoxArray[13] = (CheckBox) findViewById(R.id.box_2_7);
        checkBoxArray[14] = (CheckBox) findViewById(R.id.box_3_1);
        checkBoxArray[15] = (CheckBox) findViewById(R.id.box_3_2);
        checkBoxArray[16] = (CheckBox) findViewById(R.id.box_3_3);
        checkBoxArray[17] = (CheckBox) findViewById(R.id.box_3_4);
        checkBoxArray[18] = (CheckBox) findViewById(R.id.box_3_5);
        checkBoxArray[19] = (CheckBox) findViewById(R.id.box_3_6);
        checkBoxArray[20] = (CheckBox) findViewById(R.id.box_3_7);
        checkBoxArray[21] = (CheckBox) findViewById(R.id.box_4_1);
        checkBoxArray[22] = (CheckBox) findViewById(R.id.box_4_2);
        checkBoxArray[23] = (CheckBox) findViewById(R.id.box_4_3);
        checkBoxArray[24] = (CheckBox) findViewById(R.id.box_4_4);
        checkBoxArray[25] = (CheckBox) findViewById(R.id.box_4_5);
        checkBoxArray[26] = (CheckBox) findViewById(R.id.box_4_6);
        checkBoxArray[27] = (CheckBox) findViewById(R.id.box_4_7);
        checkBoxArray[28] = (CheckBox) findViewById(R.id.box_5_1);
        checkBoxArray[29] = (CheckBox) findViewById(R.id.box_5_2);
        checkBoxArray[30] = (CheckBox) findViewById(R.id.box_5_3);
        checkBoxArray[31] = (CheckBox) findViewById(R.id.box_5_4);
        checkBoxArray[32] = (CheckBox) findViewById(R.id.box_5_5);
        checkBoxArray[33] = (CheckBox) findViewById(R.id.box_5_6);
        checkBoxArray[34] = (CheckBox) findViewById(R.id.box_5_7);
        checkBoxArray[35] = (CheckBox) findViewById(R.id.box_6_1);
        checkBoxArray[36] = (CheckBox) findViewById(R.id.box_6_2);
        checkBoxArray[37] = (CheckBox) findViewById(R.id.box_6_3);
        checkBoxArray[38] = (CheckBox) findViewById(R.id.box_6_4);
        checkBoxArray[39] = (CheckBox) findViewById(R.id.box_6_5);
        checkBoxArray[40] = (CheckBox) findViewById(R.id.box_6_6);
        checkBoxArray[41] = (CheckBox) findViewById(R.id.box_6_7);
        checkBoxArray[42] = (CheckBox) findViewById(R.id.box_7_1);
        checkBoxArray[43] = (CheckBox) findViewById(R.id.box_7_2);
        checkBoxArray[44] = (CheckBox) findViewById(R.id.box_7_3);
        checkBoxArray[45] = (CheckBox) findViewById(R.id.box_7_4);
        checkBoxArray[46] = (CheckBox) findViewById(R.id.box_7_5);
        checkBoxArray[47] = (CheckBox) findViewById(R.id.box_7_6);
        checkBoxArray[48] = (CheckBox) findViewById(R.id.box_7_7);
        checkBoxArray[49] = (CheckBox) findViewById(R.id.box_8_1);
        checkBoxArray[50] = (CheckBox) findViewById(R.id.box_8_2);
        checkBoxArray[51] = (CheckBox) findViewById(R.id.box_8_3);
        checkBoxArray[52] = (CheckBox) findViewById(R.id.box_8_4);
        checkBoxArray[53] = (CheckBox) findViewById(R.id.box_8_5);
        checkBoxArray[54] = (CheckBox) findViewById(R.id.box_8_6);
        checkBoxArray[55] = (CheckBox) findViewById(R.id.box_8_7);
        checkBoxArray[56] = (CheckBox) findViewById(R.id.box_9_1);
        checkBoxArray[57] = (CheckBox) findViewById(R.id.box_9_2);
        checkBoxArray[58] = (CheckBox) findViewById(R.id.box_9_3);
        checkBoxArray[59] = (CheckBox) findViewById(R.id.box_9_4);
        checkBoxArray[60] = (CheckBox) findViewById(R.id.box_9_5);
        checkBoxArray[61] = (CheckBox) findViewById(R.id.box_9_6);
        checkBoxArray[62] = (CheckBox) findViewById(R.id.box_9_7);
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
//                        startActivity(new Intent(getApplicationContext(), SevenPartTest.class));
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
     * Configures the click listener for workout button 3_5
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
     * Configures the click listener for workout button 3_6
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
     * Configures the click listener for workout button 3_7
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

    /**
     * Configures the click listener for workout button 4_1
     */
    private void configureButton_4_1() {
        layout[3][0] = (LinearLayout) findViewById(R.id.row_4_1);

        layout[3][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_2
     */
    private void configureButton_4_2() {
        layout[3][1] = (LinearLayout) findViewById(R.id.row_4_2);

        layout[3][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_4_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_3
     */
    private void configureButton_4_3() {
        layout[3][2] = (LinearLayout) findViewById(R.id.row_4_3);

        layout[3][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_4_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_4
     */
    private void configureButton_4_4() {
        layout[3][3] = (LinearLayout) findViewById(R.id.row_4_4);

        layout[3][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_5
     */
    private void configureButton_4_5() {
        layout[3][4] = (LinearLayout) findViewById(R.id.row_4_5);

        layout[3][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_4_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_6
     */
    private void configureButton_4_6() {
        layout[3][5] = (LinearLayout) findViewById(R.id.row_4_6);

        layout[3][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_4_Day_6.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_7
     */
    private void configureButton_4_7() {
        layout[3][6] = (LinearLayout) findViewById(R.id.row_4_7);

        layout[3][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_4_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_1
     */
    private void configureButton_5_1() {
        layout[4][0] = (LinearLayout) findViewById(R.id.row_5_1);

        layout[4][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_2
     */
    private void configureButton_5_2() {
        layout[4][1] = (LinearLayout) findViewById(R.id.row_5_2);

        layout[4][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_3
     */
    private void configureButton_5_3() {
        layout[4][2] = (LinearLayout) findViewById(R.id.row_5_3);

        layout[4][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_4
     */
    private void configureButton_5_4() {
        layout[4][3] = (LinearLayout) findViewById(R.id.row_5_4);

        layout[4][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_5
     */
    private void configureButton_5_5() {
        layout[4][4] = (LinearLayout) findViewById(R.id.row_5_5);

        layout[4][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_6
     */
    private void configureButton_5_6() {
        layout[4][5] = (LinearLayout) findViewById(R.id.row_5_6);

        layout[4][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_6.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_7
     */
    private void configureButton_5_7() {
        layout[4][6] = (LinearLayout) findViewById(R.id.row_5_7);

        layout[4][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_5_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_1
     */
    private void configureButton_6_1() {
        layout[5][0] = (LinearLayout) findViewById(R.id.row_6_1);

        layout[5][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_2
     */
    private void configureButton_6_2() {
        layout[5][1] = (LinearLayout) findViewById(R.id.row_6_2);

        layout[5][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_6_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_3
     */
    private void configureButton_6_3() {
        layout[5][2] = (LinearLayout) findViewById(R.id.row_6_3);

        layout[5][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_6_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_4
     */
    private void configureButton_6_4() {
        layout[5][3] = (LinearLayout) findViewById(R.id.row_6_4);

        layout[5][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_6_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_5
     */
    private void configureButton_6_5() {
        layout[5][4] = (LinearLayout) findViewById(R.id.row_6_5);

        layout[5][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_6_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_6
     */
    private void configureButton_6_6() {
        layout[5][5] = (LinearLayout) findViewById(R.id.row_6_6);

        layout[5][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_7
     */
    private void configureButton_6_7() {
        layout[5][6] = (LinearLayout) findViewById(R.id.row_6_7);

        layout[5][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_6_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_1
     */
    private void configureButton_7_1() {
        layout[6][0] = (LinearLayout) findViewById(R.id.row_7_1);

        layout[6][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_2
     */
    private void configureButton_7_2() {
        layout[6][1] = (LinearLayout) findViewById(R.id.row_7_2);

        layout[6][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_7_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_3
     */
    private void configureButton_7_3() {
        layout[6][2] = (LinearLayout) findViewById(R.id.row_7_3);

        layout[6][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_7_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_4
     */
    private void configureButton_7_4() {
        layout[6][3] = (LinearLayout) findViewById(R.id.row_7_4);

        layout[6][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_7_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_5
     */
    private void configureButton_7_5() {
        layout[6][4] = (LinearLayout) findViewById(R.id.row_7_5);

        layout[6][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_7_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_6
     */
    private void configureButton_7_6() {
        layout[6][5] = (LinearLayout) findViewById(R.id.row_7_6);

        layout[6][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_7
     */
    private void configureButton_7_7() {
        layout[6][6] = (LinearLayout) findViewById(R.id.row_7_7);

        layout[6][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_7_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_1
     */
    private void configureButton_8_1() {
        layout[7][0] = (LinearLayout) findViewById(R.id.row_8_1);

        layout[7][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_2
     */
    private void configureButton_8_2() {
        layout[7][1] = (LinearLayout) findViewById(R.id.row_8_2);

        layout[7][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_8_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_3
     */
    private void configureButton_8_3() {
        layout[7][2] = (LinearLayout) findViewById(R.id.row_8_3);

        layout[7][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_4
     */
    private void configureButton_8_4() {
        layout[7][3] = (LinearLayout) findViewById(R.id.row_8_4);

        layout[7][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_8_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_5
     */
    private void configureButton_8_5() {
        layout[7][4] = (LinearLayout) findViewById(R.id.row_8_5);

        layout[7][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_8_Day_5.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_6
     */
    private void configureButton_8_6() {
        layout[7][5] = (LinearLayout) findViewById(R.id.row_8_6);

        layout[7][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_7
     */
    private void configureButton_8_7() {
        layout[7][6] = (LinearLayout) findViewById(R.id.row_8_7);

        layout[7][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_8_Day_7.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_1
     */
    private void configureButton_9_1() {
        layout[8][0] = (LinearLayout) findViewById(R.id.row_9_1);

        layout[8][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][0].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_2
     */
    private void configureButton_9_2() {
        layout[8][1] = (LinearLayout) findViewById(R.id.row_9_2);

        layout[8][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][1].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_9_Day_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_3
     */
    private void configureButton_9_3() {
        layout[8][2] = (LinearLayout) findViewById(R.id.row_9_3);

        layout[8][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][2].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_9_Day_3.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_4
     */
    private void configureButton_9_4() {
        layout[8][3] = (LinearLayout) findViewById(R.id.row_9_4);

        layout[8][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][3].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), Week_9_Day_4.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_5
     */
    private void configureButton_9_5() {
        layout[8][4] = (LinearLayout) findViewById(R.id.row_9_5);

        layout[8][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][4].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_6
     */
    private void configureButton_9_6() {
        layout[8][5] = (LinearLayout) findViewById(R.id.row_9_6);

        layout[8][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][5].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_7
     */
    private void configureButton_9_7() {
        layout[8][6] = (LinearLayout) findViewById(R.id.row_9_7);

        layout[8][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][6].setBackgroundColor(Color.WHITE);
                        startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

}