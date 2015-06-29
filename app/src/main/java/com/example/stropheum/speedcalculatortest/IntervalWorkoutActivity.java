package com.example.stropheum.speedcalculatortest;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
    final double PART_TWO_GOAL_PACE   = 6.0;
    final double PART_THREE_GOAL_PACE = 12.0;
    final double PART_FOUR_GOAL_PACE  = 6.0;
    final double PART_FIVE_GOAL_PACE  = 12.0;
    final double PART_SIX_GOAL_PACE   = 6.0;
    final double PART_SEVEN_GOAL_PACE = 12.0;

    // Duration for each part in milliseconds
    final int PART_ONE_DURATION   = 60000;
    final int PART_TWO_DURATION   = 120000;
    final int PART_THREE_DURATION = 60000;
    final int PART_FOUR_DURATION  = 120000;
    final int PART_FIVE_DURATION  = 60000;
    final int PART_SIX_DURATION   = 120000;
    final int PART_SEVEN_DURATION = 60000;

    // Main titles for actionbar to set at each part
    final String PART_ONE_MAIN_TITLE   = "Part 1: Run 1 minute";
    final String PART_TWO_MAIN_TITLE   = "Part 2: Run 2 minutes";
    final String PART_THREE_MAIN_TITLE = "Part 3: Run 1 minute";
    final String PART_FOUR_MAIN_TITLE  = "Part 4: Run 2 minutes";
    final String PART_FIVE_MAIN_TITLE  = "Part 5: Run 1 minute";
    final String PART_SIX_MAIN_TITLE   = "Part 6: Run 2 minutes";
    final String PART_SEVEN_MAIN_TITLE = "Part 7: Run 1 minute";

    // Secondary titles for actionbar to set at each part
    final String PART_ONE_SECONDARY_TITLE   = "12:00 minute/mile pace";
    final String PART_TWO_SECONDARY_TITLE   = "6:00 minute/mile pace";
    final String PART_THREE_SECONDARY_TITLE = "12:00 minute/mile pace";
    final String PART_FOUR_SECONDARY_TITLE  = "6:00 minute/mile pace";
    final String PART_FIVE_SECONDARY_TITLE  = "12:00 minute/mile pace";
    final String PART_SIX_SECONDARY_TITLE   = "6:00 minute/mile pace";
    final String PART_SEVEN_SECONDARY_TITLE = "12:00 minute/mile pace";

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

    CountDownTimer partTimer;
    long timeRemaining;
    boolean isPaused;

    // Tracks the start time and elapsed time of individual parts
    double partTimeStart, partTimeElapsed;

    int currentPart;

    int tickCounter; // Counts the number of ticks on current part

    // Value to determine if the part has run for the first time
    boolean partOneFirstRun, partTwoFirstRun, partThreeFirstRun,
            partFourFirstRun, partFiveFirstRun, partSixFirstRun,
            partSevenFirstRun;

    // Value to track the first tick of each timer
    boolean partOneFirstTick, partTwoFirstTick, partThreeFirstTick,
            partFourFirstTick, partFiveFirstTick, partSixFirstTick,
            partSevenFirstTick;

    String paceText;
    Intent i;

    Vibrator vibrator;

    ImageButton pauseButton;
    ImageButton backButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_interval_workout);

        i = new Intent(this, SpeedCalculationService.class);

        // Enable values to track the first call to each part to initialize CountDownTimer values
        partOneFirstRun   = true;
        partTwoFirstRun   = true;
        partThreeFirstRun = true;
        partFourFirstRun  = true;
        partFiveFirstRun  = true;
        partSixFirstRun   = true;
        partSevenFirstRun = true;

        isPaused = true;
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        pauseButton.setEnabled(false);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePauseClick();
            }
        });

        backButton = (ImageButton) findViewById(R.id.partLeftButton);
        backButton.setEnabled(false);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBackButtonClick();
            }
        });

        nextButton = (ImageButton) findViewById(R.id.partRightButton);
        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButtonClick();
            }
        });

        mainTitle = (TextView) findViewById(R.id.mainTitle);
        mainTitle.setText(PART_ONE_MAIN_TITLE);
        secondaryTitle = (TextView) findViewById(R.id.secondaryTitle);
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);
        nextTitle = (TextView) findViewById(R.id.nextTitle);
        nextTitle.setText(PART_ONE_NEXT_TITLE);

        // Starts the service for calculating user's speed
        bindService(i, speedConnection, Context.BIND_AUTO_CREATE);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        String paceText = "Waiting for GPS";
        updatePaceText(paceText);
        currentPart = 1; // Tracks currently active part
        timeRemaining = PART_ONE_DURATION;
        tickCounter = 0;
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
        emtVal.setText(String.format("%02d:%02d", minutes, seconds));
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
     *
     * @param color color to change the text to
     */
    private void updatePaceColor(String color) {
        TextView pace = (TextView) findViewById(R.id.paceView);
        pace.setTextColor(Color.parseColor(color));
    }

    /**
     * Updates curent distance traveled
     *
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
        double time = timeRemaining / 1000; // Store remaining time in seconds

        int minutes = (int) time / 60;
        int seconds = (int) time % 60;

        final TextView timeView = (TextView) findViewById(R.id.timeLabel);
        timeView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    ServiceConnection speedConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SpeedCalculationBinder binder = (SpeedCalculationBinder) service;
            isBound = true;
            speedCalculator = binder.getService();

            waitForService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    /**
     * Enables start button when GPS signal is found
     */
    public void waitForService() {
        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pauseButton.setEnabled(true);
                            pauseButton.setBackgroundResource(R.drawable.play);
                            final TextView paceText = (TextView) findViewById(R.id.paceView);
                            updatePaceColor("#3498db");
                            paceText.setText("GPS Found");
                        }
                    });
                    cancel();
                }
            }
        }, 0, 100);
    }

    /**
     * Method called when pause/play button is pressed
     */
    private void handlePauseClick() {
        if (isPaused) {
            pauseButton.setBackgroundResource(R.drawable.pause);
            switch (currentPart) {
                case 1:
                    partOneBegin();
                    break;
                case 2:
                    partTwoBegin();
                    break;
                case 3:
                    partThreeBegin();
                    break;
                case 4:
                    partFourBegin();
                    break;
                case 5:
                    partFiveBegin();
                    break;
                case 6:
                    partSixBegin();
                    break;
                case 7:
                    partSevenBegin();
                    break;
            }
            isPaused = false;
        } else {
            pauseButton.setBackgroundResource(R.drawable.play);
            backButton.setEnabled(false); // Disable part skipping when app is paused
            nextButton.setEnabled(false); // Disaple part skipping when app is paused
            partTimer.cancel();
            isPaused = true;
        }
    }

    /**
     * Method called when "back" button is clicked
     */
    private void handleBackButtonClick() {

        partTimer.cancel();

        switch (currentPart) {
            case 1:
                // Do nothing
                break;
            case 2:
                partOneFirstRun = true;
                timeRemaining = PART_ONE_DURATION;
                partOneBegin();
                break;
            case 3:
                partTwoFirstRun = true;
                timeRemaining = PART_TWO_DURATION;
                partTwoBegin();
                break;
            case 4:
                partThreeFirstRun = true;
                timeRemaining = PART_THREE_DURATION;
                partThreeBegin();
                break;
            case 5:
                partFourFirstRun = true;
                timeRemaining = PART_FOUR_DURATION;
                partFourBegin();
                break;
            case 6:
                partFiveFirstRun = true;
                timeRemaining = PART_FIVE_DURATION;
                partFiveBegin();
                break;
            case 7:
                partSixFirstRun = true;
                timeRemaining = PART_SIX_DURATION;
                partSixBegin();
                break;
        }
    }

    /**
     * Method called when "next" button is clicked
     */
    private void handleNextButtonClick() {

        partTimer.cancel();

        switch (currentPart) {
            case 1:
                partTwoFirstRun = true;
                timeRemaining = PART_TWO_DURATION;
                partTwoBegin();
                break;
            case 2:
                partThreeFirstRun = true;
                timeRemaining = PART_THREE_DURATION;
                partThreeBegin();
                break;
            case 3:
                partFourFirstRun = true;
                timeRemaining = PART_FOUR_DURATION;
                partFourBegin();
                break;
            case 4:
                partFiveFirstRun = true;
                timeRemaining = PART_FIVE_DURATION;
                partFiveBegin();
                break;
            case 5:
                partSixFirstRun = true;
                timeRemaining = PART_SIX_DURATION;
                partSixBegin();
                break;
            case 6:
                partSevenFirstRun = true;
                timeRemaining = PART_SEVEN_DURATION;
                partSevenBegin();
                break;
            case 7:
                // Do nothing
                break;
        }
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partOneBegin() {

        backButton.setEnabled(false); // Disable back button when first part begins
        nextButton.setEnabled(true); // Enable next button when first part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_1);

        if (partOneFirstRun) {
            timeRemaining = PART_ONE_DURATION;
            tickCounter = 0;
            partOneFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 1;

        // Update titles
        mainTitle.setText(PART_ONE_MAIN_TITLE);
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);
        nextTitle.setText(PART_ONE_NEXT_TITLE);

        final RadioButton partButton1 = (RadioButton) findViewById(R.id.radioButton1);
        partButton1.setChecked(true);

        partOneFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partOneFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_ONE_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partOneFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partTwoBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partTwoBegin() {

        backButton.setEnabled(true); // Enable back button when second part begins
        nextButton.setEnabled(true); // Enable next button when second part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_2);

        if (partTwoFirstRun) {
            timeRemaining = PART_TWO_DURATION;
            tickCounter = 0;
            partTwoFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 2;

        // Update titles
        mainTitle.setText(PART_TWO_MAIN_TITLE);
        secondaryTitle.setText(PART_TWO_SECONDARY_TITLE);
        nextTitle.setText(PART_TWO_NEXT_TITLE);

        final RadioButton partButton2 = (RadioButton) findViewById(R.id.radioButton2);
        partButton2.setChecked(true);

        partTwoFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partTwoFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_TWO_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partTwoFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partThreeBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partThreeBegin() {

        backButton.setEnabled(true); // Enable back button when third part begins
        nextButton.setEnabled(true); // Enable next button when third part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_3);

        if (partThreeFirstRun) {
            timeRemaining = PART_THREE_DURATION;
            tickCounter = 0;
            partThreeFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 3;

        // Update titles
        mainTitle.setText(PART_THREE_MAIN_TITLE);
        secondaryTitle.setText(PART_THREE_SECONDARY_TITLE);
        nextTitle.setText(PART_THREE_NEXT_TITLE);

        final RadioButton partButton3 = (RadioButton) findViewById(R.id.radioButton3);
        partButton3.setChecked(true);

        partThreeFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partThreeFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_THREE_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partThreeFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partFourBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partFourBegin() {

        backButton.setEnabled(true); // Enable back button when fourth part begins
        nextButton.setEnabled(true); // Enable next button when fourth part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_4);

        if (partFourFirstRun) {
            timeRemaining = PART_FOUR_DURATION;
            tickCounter = 0;
            partFourFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 4;

        // Update titles
        mainTitle.setText(PART_FOUR_MAIN_TITLE);
        secondaryTitle.setText(PART_FOUR_SECONDARY_TITLE);
        nextTitle.setText(PART_FOUR_NEXT_TITLE);

        final RadioButton partButton4 = (RadioButton) findViewById(R.id.radioButton4);
        partButton4.setChecked(true);

        partFourFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partFourFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_FOUR_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partFourFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partFiveBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partFiveBegin() {

        backButton.setEnabled(true); // Enable back button when fifth part begins
        nextButton.setEnabled(true); // Enable next button when fifth part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_1);

        if (partFiveFirstRun) {
            timeRemaining = PART_FIVE_DURATION;
            tickCounter = 0;
            partFiveFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 5;

        // Update titles
        mainTitle.setText(PART_FIVE_MAIN_TITLE);
        secondaryTitle.setText(PART_FIVE_SECONDARY_TITLE);
        nextTitle.setText(PART_FIVE_NEXT_TITLE);

        final RadioButton partButton5 = (RadioButton) findViewById(R.id.radioButton5);
        partButton5.setChecked(true);

        partFiveFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partFiveFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_FIVE_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partFiveFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partSixBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partSixBegin() {

        backButton.setEnabled(true); // Enable back button when sixth part begins
        nextButton.setEnabled(true); // Enable next button when sixth part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_2);

        if (partSixFirstRun) {
            timeRemaining = PART_SIX_DURATION;
            tickCounter = 0;
            partSixFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 6;

        // Update titles
        mainTitle.setText(PART_SIX_MAIN_TITLE);
        secondaryTitle.setText(PART_SIX_SECONDARY_TITLE);
        nextTitle.setText(PART_SIX_NEXT_TITLE);

        final RadioButton partButton6 = (RadioButton) findViewById(R.id.radioButton6);
        partButton6.setChecked(true);

        partSixFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partSixFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_SIX_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partSixFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partSevenBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partSevenBegin() {

        backButton.setEnabled(true); // Enable back button when last part begins
        nextButton.setEnabled(false); // Disable next button when last part begins

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        layout.setBackgroundResource(R.drawable.background_3);

        if (partSevenFirstRun) {
            timeRemaining = PART_SEVEN_DURATION;
            tickCounter = 0;
            partSevenFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 7;

        // Update titles
        mainTitle.setText(PART_SEVEN_MAIN_TITLE);
        secondaryTitle.setText(PART_SEVEN_SECONDARY_TITLE);
        nextTitle.setText(PART_SEVEN_NEXT_TITLE);

        final RadioButton partButton7 = (RadioButton) findViewById(R.id.radioButton7);
        partButton7.setChecked(true);

        partSevenFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                if (partSevenFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_SEVEN_GOAL_PACE;
                    updateGoalPace(goalPace);

                    distance = 0.0;
                    updateDistance(distance);

                    partSevenFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                currentPace = 60 / speed;
                updateCurrentPace(currentPace);

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 20 == 0) {// calls pace alert every 10 seconds (20 loop ticks)
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                pauseButton.setEnabled(false);
                updatePaceColor("#3498db");
                updatePaceText("Workout Finished!");
            }

        }.start();
    }
}