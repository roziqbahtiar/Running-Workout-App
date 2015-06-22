package com.example.stropheum.speedcalculatortest;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.os.Vibrator;
import java.util.Timer;
import java.util.TimerTask;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import com.example.stropheum.speedcalculatortest.SpeedCalculationService.SpeedCalculationBinder;

public class IntervalWorkoutActivity extends ActionBarActivity {

    // Allow 15 seconds of error for time calculations
    final double MILE_TIME_ERROR = 0.25;

    //final TextView pace = (TextView) findViewById(R.id.paceView);

    // TextViews for displaying goal paces and workout summaries
    TextView mainTitle;
    TextView secondaryTitle;
    TextView nextTitle;

    // Goal mile times for each part
    final double PART_ONE_GOAL_PACE   = 12.0;
    final double PART_TWO_GOAL_PACE   =  6.0;
    final double PART_THREE_GOAL_PACE = 12.0;
    final double PART_FOUR_GOAL_PACE  =  6.0;
    final double PART_FIVE_GOAL_PACE  = 12.0;
    final double PART_SIX_GOAL_PACE   =  6.0;
    final double PART_SEVEN_GOAL_PACE = 12.0;

    // Duration for each part in milliseconds
    final int PART_ONE_DURATION   =  60000;
    final int PART_TWO_DURATION   = 120000;
    final int PART_THREE_DURATION =  60000;
    final int PART_FOUR_DURATION  = 120000;
    final int PART_FIVE_DURATION  =  60000;
    final int PART_SIX_DURATION   = 120000;
    final int PART_SEVEN_DURATION =  60000;

    // Main titles for actionbar to set at each part
    final String PART_ONE_MAIN_TITLE   = "Part 1: Run 1 minute";
    final String PART_TWO_MAIN_TITLE   = "Part 2: Run 2 minutes";
    final String PART_THREE_MAIN_TITLE = "Part 3: Run 1 minute";
    final String PART_FOUR_MAIN_TITLE  = "Part 4: Run 2 minutes";
    final String PART_FIVE_MAIN_TITLE  = "Part 1: Run 1 minute";
    final String PART_SIX_MAIN_TITLE   = "Part 2: Run 2 minutes";
    final String PART_SEVEN_MAIN_TITLE = "Part 3: Run 1 minute";

    // Secondary titles for actionbar to set at each part
    final String PART_ONE_SECONDARY_TITLE   = "12 minute/mile pace";
    final String PART_TWO_SECONDARY_TITLE   = "6 minute/mile pace";
    final String PART_THREE_SECONDARY_TITLE = "12 minute/mile pace";
    final String PART_FOUR_SECONDARY_TITLE  = "6 minute/mile pace";
    final String PART_FIVE_SECONDARY_TITLE  = "12 minute/mile pace";
    final String PART_SIX_SECONDARY_TITLE   = "6 minute/mile pace";
    final String PART_SEVEN_SECONDARY_TITLE = "12 minute/mile pace";

    // Secondary abbreviated titles for "next" title
    final String PART_ONE_NEXT_TITLE   = "6:00 min/mile";
    final String PART_TWO_NEXT_TITLE   = "12:00 min/mile";
    final String PART_THREE_NEXT_TITLE = "6:00 min/mile";
    final String PART_FOUR_NEXT_TITLE  = "12:00 min/mile";
    final String PART_FIVE_NEXT_TITLE  = "6:00 min/mile";
    final String PART_SIX_NEXT_TITLE   = "12:00 min/mile";
    final String PART_SEVEN_NEXT_TITLE = "Finished";

    public SpeedCalculationService speedCalculator;
    boolean isBound = false;

    double currentPace, goalPace;
    double speed;
    double distance = 0;

    // Tracks the time a part starts and how long it has been running for
    double timeStart, timeElapsed;

    double workoutTimeStart;

    // Tracks the start time and elapsed time of individual parts
    double partOneTimeStart,   partOneTimeElapsed;
    double partTwoTimeStart,   partTwoTimeElapsed;
    double partThreeTimeStart, partThreeTimeElapsed;
    double partFourTimeStart,  partFourTimeElapsed;
    double partFiveTimeStart,  partFiveTimeElapsed;
    double partSixTimeStart,   partSixTimeElapsed;
    double partSevenTimeStart, partSevenTimeElapsed;

    double currentPartTimeStart;
    int currentPartDuration;

    // Value to determine if the part has run for the first time
    boolean partOneFirstRun,  partTwoFirstRun,  partThreeFirstRun,
            partFourFirstRun, partFiveFirstRun, partSixFirstRun,
            partSevenFirstRun;

    String paceText;
    Intent i;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_workout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        i = new Intent(this, SpeedCalculationService.class);

        mainTitle      = (TextView) findViewById(R.id.mainTitle);
        secondaryTitle = (TextView) findViewById(R.id.secondaryTitle);
        nextTitle      = (TextView) findViewById(R.id.nextTitle);

        // Starts the service for calculating user's speed
        bindService(i, speedConnection, Context.BIND_AUTO_CREATE);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        String paceText = "Waiting for GPS";
        updatePaceText(paceText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interval_workout, menu);
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

        if (id == 16908332) {
            stopService(new Intent(IntervalWorkoutActivity.this, SpeedCalculationService.class));
            unbindService(speedConnection);
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Terminate the speed calculation service
        stopService(new Intent(IntervalWorkoutActivity.this, SpeedCalculationService.class));
        unbindService(speedConnection);
        finish();
        return;
    }

    /**
     * Updates the display to show the current speed
     *
     * @param speed The current speed of the user
     */
    private void updateSpeed(double speed) {
        final TextView speedVal = (TextView) findViewById(R.id.SpeedVal);
        speedVal.setText(String.format("%.2f", speed));
    }

    /**
     * Updates the current estimated mile time
     *
     * @param currentPace User's current mile time
     */
    private void updateCurrentPace(double currentPace) {
        int minutes = (int) currentPace;
        int seconds = (int) (((currentPace * 100) % 100) * 0.6);
        if (minutes > 25) {
            minutes = 0;
            seconds = 0;
        }
        final TextView emtVal = (TextView) findViewById(R.id.emtVal);
        emtVal.setText(String.format("%d:%02d", minutes, seconds));
    }

    /**
     * Updates the current goal mile time
     *
     * @param goalPace New goal mile time
     */
    private void updateGoalPace(double goalPace) {
        int minutes = (int) goalPace;
        int seconds = (int) (((goalPace * 100) % 100) * 0.6);
        final TextView gmtVal = (TextView) findViewById(R.id.gmtVal);
        gmtVal.setText(String.format("%d:%02d", minutes, seconds));
    }

    /**
     * Updates the current pace text
     *
     * @param paceText indicator for user;s current speed in relation to goal time
     */
    private void updatePaceText(String paceText) {
        TextView pace = (TextView) findViewById(R.id.paceView);
        pace.setText(paceText);
    }

    /**
     * Updates pace color according to current pace
     * @param color color to change the text to
     */
    private void updatePaceColor(String color) {
        TextView pace = (TextView) findViewById(R.id.paceView);
        pace.setTextColor(Color.parseColor(color));
    }

    /**
     * Updates curent distance traveled
     * @param distance The current overall distance traveled
     */
    private void updateDistance(double distance) {
        final TextView distanceText = (TextView) findViewById(R.id.distanceVal);
        distanceText.setText(String.format("%.3f", distance));
    }

    /**
     * Checks current pace and assigns appropriate text and color
     */
    private void paceAlert() {
        timeStart = System.currentTimeMillis(); // Reset alert interval
        String paceColor;

        if (currentPace > goalPace + MILE_TIME_ERROR) {
            paceText = "Speed up";
            paceColor = "#52be7f";//Green
            long[] pattern = {0, 200, 200, 200, 200, 200};
            vibrator.vibrate(pattern, -1);

        } else if (currentPace < goalPace - MILE_TIME_ERROR) {
            paceText = "Slow Down";
            paceColor = "#e74c3c";//Red
            vibrator.vibrate(1000);
        } else {
            paceText = "Perfect Pace!";
            paceColor = "#3498db";//Blue
        }
        updatePaceText(paceText);
        updatePaceColor(paceColor);
    }

    /**
     * Updates the timer display on current workout to reflect total elapsed time
     */
    private void updateTime() {
        double currentPartTimeElapsed = System.currentTimeMillis() - currentPartTimeStart;
        double time = (currentPartDuration - currentPartTimeElapsed) / 1000;

        int minutes = (int) time / 60;
        int seconds = (int) time % 60;

        final TextView timeView = (TextView) findViewById(R.id.timeLabel);
        timeView.setText(String.format("%d:%02d", minutes, seconds));
    }

    ServiceConnection speedConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SpeedCalculationBinder binder = (SpeedCalculationBinder) service;
            isBound = true;
            speedCalculator = binder.getService();

            partOneBegin();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partOneBegin() {
        partOneFirstRun = true;
        speedCalculator.resetValues();

        // Update titles
        mainTitle.setText(PART_ONE_MAIN_TITLE);
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);
        nextTitle.setText(PART_ONE_NEXT_TITLE);

        final RadioButton partButton1 = (RadioButton) findViewById(R.id.radioButton1);
        partButton1.setChecked(true);

        final Timer partOneTimer = new Timer();
        partOneTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {

                    // Forces GUI updates to happen on the Activity UI thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partOneFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partOneTimeStart = System.currentTimeMillis();
                                workoutTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partOneTimeStart;
                                currentPartDuration = PART_ONE_DURATION;

                                updateTime();

                                goalPace = PART_ONE_GOAL_PACE;
                                updateGoalPace(goalPace);

                                distance = 0.0;
                                updateDistance(distance);

                                partOneFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partOneTimeElapsed = System.currentTimeMillis() - partOneTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            distance = speedCalculator.getCurrentDistance();
                            updateDistance(distance);

                            if (partOneTimeElapsed >= PART_ONE_DURATION) {
                                // Terminate current part and start the next
                                partOneTimer.cancel();
                                partTwoBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });

                }
            }
        }, 0, 1000); // Updates every 5 seconds

    }

    /**
     * Method called when part one is completed
     */
    public void partTwoBegin() {
        partTwoFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_TWO_MAIN_TITLE);
        secondaryTitle.setText(PART_TWO_SECONDARY_TITLE);
        nextTitle.setText(PART_TWO_NEXT_TITLE);

        final RadioButton partButton2 = (RadioButton) findViewById(R.id.radioButton2);
        partButton2.setChecked(true);

        final Timer partTwoTimer = new Timer();
        partTwoTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partTwoFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partTwoTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partTwoTimeStart;
                                currentPartDuration = PART_TWO_DURATION;

                                goalPace = PART_TWO_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partTwoFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partTwoTimeElapsed = System.currentTimeMillis() - partTwoTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partTwoTimeElapsed >= PART_TWO_DURATION) {
                                // Terminate current part and start the next
                                partTwoTimer.cancel();
                                partThreeBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * Method called when part two is completed
     */
    public void partThreeBegin() {
        partThreeFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_THREE_MAIN_TITLE);
        secondaryTitle.setText(PART_THREE_SECONDARY_TITLE);
        nextTitle.setText(PART_THREE_NEXT_TITLE);

        final RadioButton partButton3 = (RadioButton) findViewById(R.id.radioButton3);
        partButton3.setChecked(true);

        final Timer partThreeTimer = new Timer();
        partThreeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partThreeFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partThreeTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partThreeTimeStart;
                                currentPartDuration = PART_THREE_DURATION;

                                goalPace = PART_THREE_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partThreeFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partThreeTimeElapsed = System.currentTimeMillis() - partThreeTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partThreeTimeElapsed >= PART_THREE_DURATION) {
                                // Terminate current part and start the next
                                partThreeTimer.cancel();
                                partFourBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * Method called when part three is completed
     */
    public void partFourBegin() {
        partFourFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_FOUR_MAIN_TITLE);
        secondaryTitle.setText(PART_FOUR_SECONDARY_TITLE);
        nextTitle.setText(PART_FOUR_NEXT_TITLE);

        final RadioButton partButton4 = (RadioButton) findViewById(R.id.radioButton4);
        partButton4.setChecked(true);

        final Timer partFourTimer = new Timer();
        partFourTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partFourFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partFourTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partFourTimeStart;
                                currentPartDuration = PART_FOUR_DURATION;

                                goalPace = PART_FOUR_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partFourFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partFourTimeElapsed = System.currentTimeMillis() - partFourTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partFourTimeElapsed >= PART_FOUR_DURATION) {
                                // Terminate current part and start the next
                                partFourTimer.cancel();
                                partFiveBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * Method called when part four is completed
     */
    public void partFiveBegin() {
        partFiveFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_FIVE_MAIN_TITLE);
        secondaryTitle.setText(PART_FIVE_SECONDARY_TITLE);
        nextTitle.setText(PART_FIVE_NEXT_TITLE);

        final RadioButton partButton5 = (RadioButton) findViewById(R.id.radioButton5);
        partButton5.setChecked(true);

        final Timer partFiveTimer = new Timer();
        partFiveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partFiveFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partFiveTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partFiveTimeStart;
                                currentPartDuration = PART_FIVE_DURATION;

                                goalPace = PART_FIVE_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partFiveFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partFiveTimeElapsed = System.currentTimeMillis() - partFiveTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partFiveTimeElapsed >= PART_FIVE_DURATION) {
                                // Terminate current part and start the next
                                partFiveTimer.cancel();
                                partSixBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * Method called when part five is completed
     */
    public void partSixBegin() {
        partSixFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_SIX_MAIN_TITLE);
        secondaryTitle.setText(PART_SIX_SECONDARY_TITLE);
        nextTitle.setText(PART_SIX_NEXT_TITLE);

        final RadioButton partButton6 = (RadioButton) findViewById(R.id.radioButton6);
        partButton6.setChecked(true);

        final Timer partSixTimer = new Timer();
        partSixTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partSixFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partSixTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partSixTimeStart;
                                currentPartDuration = PART_SIX_DURATION;

                                goalPace = PART_SIX_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partSixFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partSixTimeElapsed = System.currentTimeMillis() - partSixTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partSixTimeElapsed >= PART_SIX_DURATION) {
                                // Terminate current part and start the next
                                partSixTimer.cancel();
                                partSevenBegin();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * Method called when part six is completed
     */
    public void partSevenBegin() {
        partSevenFirstRun = true;
        speedCalculator.resetValues();

        mainTitle.setText(PART_SEVEN_MAIN_TITLE);
        secondaryTitle.setText(PART_SEVEN_SECONDARY_TITLE);
        nextTitle.setText(PART_SEVEN_NEXT_TITLE);

        final RadioButton partButton7 = (RadioButton) findViewById(R.id.radioButton7);
        partButton7.setChecked(true);

        final Timer partSevenTimer = new Timer();
        partSevenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partSevenFirstRun) {
                                timeStart = System.currentTimeMillis();
                                partSevenTimeStart = System.currentTimeMillis();
                                currentPartTimeStart = partSevenTimeStart;
                                currentPartDuration = PART_SEVEN_DURATION;

                                goalPace = PART_SEVEN_GOAL_PACE;
                                updateGoalPace(goalPace);

                                partSevenFirstRun = false;
                            }

                            // Tracks the elapsed time since last alert
                            timeElapsed = System.currentTimeMillis() - timeStart;
                            updateTime();

                            // Tracks the total elapsed time of the workout part
                            partSevenTimeElapsed = System.currentTimeMillis() - partSevenTimeStart;

                            speed = speedCalculator.getCurrentSpeed();
                            //updateSpeed(speed);

                            currentPace = 60 / speed;
                            updateCurrentPace(currentPace);

                            if (partSevenTimeElapsed >= PART_SEVEN_DURATION) {
                                paceText = "Workout Done!";
                                updatePaceText(paceText);
                                // Terminate current part and start the next
                                partSevenTimer.cancel();
                            }

                            if (timeElapsed >= 9750) {// slightly less than 10 second to account for loop intervals
                                paceAlert();
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }
}