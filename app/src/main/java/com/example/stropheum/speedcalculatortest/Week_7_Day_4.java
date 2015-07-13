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


public class Week_7_Day_4 extends ActionBarActivity {

    // Allow 15 seconds of error for time calculations
    final double MILE_TIME_ERROR = 0.5;

    final int PACE_UPDATE_INTERVAL = 1;

    //final TextView pace = (TextView) findViewById(R.id.paceView);

    // TextViews for displaying goal paces and workout summaries
    TextView mainTitle;
    TextView secondaryTitle;
    TextView nextTitle;

    private final int MINUTE           = 61;
    private final int MINUTES          = 62;
    private final int SECOND           = 63;
    private final int SECONDS          = 64;
    private final int MINUTE_PER_MILE  = 65;
    private final int PACE             = 66;

    // Goal mile times for each part
    final double PART_ONE_GOAL_PACE   = 10.0;
    final double PART_TWO_GOAL_PACE   = 11.0;
    final double PART_THREE_GOAL_PACE = 18.0;
    final double PART_FOUR_GOAL_PACE  = 6.0;
    final double PART_FIVE_GOAL_PACE  = 12.0;
    final double PART_SIX_GOAL_PACE   = 6.0;
    final double PART_SEVEN_GOAL_PACE = 12.0;

    // Duration for each part in milliseconds
    final int PART_ONE_DURATION   = 900 * 1000;
    final int PART_TWO_DURATION   = 1200 * 1000;
    final int PART_THREE_DURATION = 600 * 1000;
    final int PART_FOUR_DURATION  = 120000;
    final int PART_FIVE_DURATION  = 60000;
    final int PART_SIX_DURATION   = 120000;
    final int PART_SEVEN_DURATION = 60000;

    // Main titles for actionbar to set at each part
//    final String PART_ONE_MAIN_TITLE   = "Part 1: Run 1 minute";
//    final String PART_TWO_MAIN_TITLE   = "Part 2: Run 2 minutes";
//    final String PART_THREE_MAIN_TITLE = "Part 3: Run 1 minute";
//    final String PART_FOUR_MAIN_TITLE  = "Part 4: Run 2 minutes";
//    final String PART_FIVE_MAIN_TITLE  = "Part 5: Run 1 minute";
//    final String PART_SIX_MAIN_TITLE   = "Part 6: Run 2 minutes";
//    final String PART_SEVEN_MAIN_TITLE = "Part 7: Run 1 minute";

    // Secondary titles for actionbar to set at each part
    final String PART_ONE_SECONDARY_TITLE   = "10:00 min/mile";
    final String PART_TWO_SECONDARY_TITLE   = "11:00 min/mile";
    final String PART_THREE_SECONDARY_TITLE = "18:00 min/mile";
    final String PART_FOUR_SECONDARY_TITLE  = "6:00 min/mile";
    final String PART_FIVE_SECONDARY_TITLE  = "12:00 min/mile";
    final String PART_SIX_SECONDARY_TITLE   = "6:00 min/mile";
    final String PART_SEVEN_SECONDARY_TITLE = "12:00 min/mile";

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
    double paceSum, paceAverage;
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

    // Tracks if "perfect pace" was said once so it doesn't repeat
    boolean saidPerfectOnce;

    String paceText;
    Intent i;

    Vibrator vibrator;

    ImageButton pauseButton;
    ImageButton backButton, nextButton;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_7_day_4);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_7_4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Disable screen timeout while workout is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        i = new Intent(this, SpeedCalculationService.class);

        // Enable values to track the first call to each part to initialize CountDownTimer values
        partOneFirstRun   = true;
        partTwoFirstRun   = true;
        partThreeFirstRun = true;
        partFourFirstRun  = true;
        partFiveFirstRun  = true;
        partSixFirstRun   = true;
        partSevenFirstRun = true;

        saidPerfectOnce = false;

        paceSum = 0.0;

        // Initialize loading image
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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
//        mainTitle.setText(PART_ONE_MAIN_TITLE);
        secondaryTitle = (TextView) findViewById(R.id.secondaryTitle);
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);
//        nextTitle = (TextView) findViewById(R.id.nextTitle);
//        nextTitle.setText(PART_ONE_NEXT_TITLE);

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
        getMenuInflater().inflate(R.menu.menu_week_7_day_4, menu);
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
            stopService(new Intent(Week_7_Day_4.this, SpeedCalculationService.class));
//            stopService(i);
            unbindService(speedConnection);
            this.finish();
            overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
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
    public void onBackPressed() {
        // Terminate the speed calculation service
        stopService(new Intent(Week_7_Day_4.this, SpeedCalculationService.class));
        unbindService(speedConnection);
        this.finish();
        overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
        return;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (partTimer != null) {
            partTimer.cancel();
        }
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
        if (minutes > 99) {
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
        distanceText.setText(String.format("%.2f", distance));
    }

    /**
     * Checks current pace and assigns appropriate text and color
     */
    private void paceAlert() {
        timeStart = System.currentTimeMillis(); // Reset alert interval
        String paceColor;
        MediaPlayer player;

        if (paceAverage > goalPace + MILE_TIME_ERROR) {
            paceText = "Speed up";
            paceColor = "#52be7f";//Green
            long[] pattern = {0, 200, 200, 200, 200, 200};
            vibrator.vibrate(pattern, -1);
            player = MediaPlayer.create(this, R.raw.speed_up);
            player.start();
            saidPerfectOnce = false; // Reset perfect alert
        } else if (paceAverage < goalPace - MILE_TIME_ERROR) {
            paceText = "Slow Down";
            paceColor = "#e74c3c";//Red
            vibrator.vibrate(1000);
            player = MediaPlayer.create(this, R.raw.slow_down);
            player.start();
            saidPerfectOnce = false; // Reset perfect alert
        } else {
            paceText = "Perfect Pace!";
            paceColor = "#3498db";//Blue
            player = MediaPlayer.create(this, R.raw.perfect_pace);
            if (!saidPerfectOnce) {
                player.start();
            }
            saidPerfectOnce = true; // Don't repeat multiple consecutive perfect pace alerts
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

    /**
     * Updates the timer display specifically for initial countdown timer
     */
    private void updateCountdownTime() {
        double time = timeRemaining / 1000;

        int minutes = (int) (time / 60);
        int seconds;

        if (timeRemaining > 2000) {
            seconds = 3;
        } else if (timeRemaining > 1000) {
            seconds = 2;
        } else {
            seconds = 1;
        }


        final TextView timeView = (TextView) findViewById(R.id.timeLabel);
        timeView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    ServiceConnection speedConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SpeedCalculationService.SpeedCalculationBinder binder = (SpeedCalculationService.SpeedCalculationBinder) service;
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
                            progressBar.setVisibility(View.GONE); // Disable progress bar
                            pauseButton.setEnabled(true);
                            pauseButton.setBackgroundResource(R.drawable.play_button);
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
            pauseButton.setBackgroundResource(R.drawable.pause_button);
            switch (currentPart) {
                case 1:
                    if (partOneFirstRun) {
                        initialCountdownBegin();
                    } else {
                        partOneBegin();
                    }
                    break;
                case 2:
                    partTwoBegin();
                    break;
                case 3:
                    partThreeBegin();
                    break;
//                case 4:
//                    partFourBegin();
//                    break;
//                case 5:
//                    partFiveBegin();
//                    break;
//                case 6:
//                    partSixBegin();
//                    break;
//                case 7:
//                    partSevenBegin();
//                    break;
            }
            isPaused = false;
        } else {
            pauseButton.setBackgroundResource(R.drawable.play_button);
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
//            case 4:
//                partThreeFirstRun = true;
//                timeRemaining = PART_THREE_DURATION;
//                partThreeBegin();
//                break;
//            case 5:
//                partFourFirstRun = true;
//                timeRemaining = PART_FOUR_DURATION;
//                partFourBegin();
//                break;
//            case 6:
//                partFiveFirstRun = true;
//                timeRemaining = PART_FIVE_DURATION;
//                partFiveBegin();
//                break;
//            case 7:
//                partSixFirstRun = true;
//                timeRemaining = PART_SIX_DURATION;
//                partSixBegin();
//                break;
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
//                partFourFirstRun = true;
//                timeRemaining = PART_FOUR_DURATION;
//                partFourBegin();
                break;
            case 4:
//                partFiveFirstRun = true;
//                timeRemaining = PART_FIVE_DURATION;
//                partFiveBegin();
//                break;
//            case 5:
//                partSixFirstRun = true;
//                timeRemaining = PART_SIX_DURATION;
//                partSixBegin();
//                break;
//            case 6:
//                partSevenFirstRun = true;
//                timeRemaining = PART_SEVEN_DURATION;
//                partSevenBegin();
//                break;
//            case 7:
//                // Do nothing
//                break;
        }
    }

    /**
     * Plays a sound file associated with the number passed in
     * @param n the number associated with the sound file played
     */
    private MediaPlayer setSound(int n) {
        MediaPlayer sound;
        switch (n) {
            case 0:
                sound = MediaPlayer.create(this, R.raw.zero);
                break;
            case 1:
                sound = MediaPlayer.create(this, R.raw.one);
                break;
            case 2:
                sound = MediaPlayer.create(this, R.raw.two);
                break;
            case 3:
                sound = MediaPlayer.create(this, R.raw.three);
                break;
            case 4:
                sound = MediaPlayer.create(this, R.raw.four);
                break;
            case 5:
                sound = MediaPlayer.create(this, R.raw.five);
                break;
            case 6:
                sound = MediaPlayer.create(this, R.raw.six);
                break;
            case 7:
                sound = MediaPlayer.create(this, R.raw.seven);
                break;
            case 8:
                sound = MediaPlayer.create(this, R.raw.eight);
                break;
            case 9:
                sound = MediaPlayer.create(this, R.raw.nine);
                break;
            case 10:
                sound = MediaPlayer.create(this, R.raw.ten);
                break;
            case 11:
                sound = MediaPlayer.create(this, R.raw.eleven);
                break;
            case 12:
                sound = MediaPlayer.create(this, R.raw.twelve);
                break;
            case 13:
                sound = MediaPlayer.create(this, R.raw.thirteen);
                break;
            case 14:
                sound = MediaPlayer.create(this, R.raw.fourteen);
                break;
            case 15:
                sound = MediaPlayer.create(this, R.raw.fifteen);
                break;
            case 16:
                sound = MediaPlayer.create(this, R.raw.sixteen);
                break;
            case 17:
                sound = MediaPlayer.create(this, R.raw.seventeen);
                break;
            case 18:
                sound = MediaPlayer.create(this, R.raw.eighteen);
                break;
            case 19:
                sound = MediaPlayer.create(this, R.raw.nineteen);
                break;
            case 20:
                sound = MediaPlayer.create(this, R.raw.twenty);
                break;
            case 21:
                sound = MediaPlayer.create(this, R.raw.twenty_one);
                break;
            case 22:
                sound = MediaPlayer.create(this, R.raw.twenty_two);
                break;
            case 23:
                sound = MediaPlayer.create(this, R.raw.twenty_three);
                break;
            case 24:
                sound = MediaPlayer.create(this, R.raw.twenty_four);
                break;
            case 25:
                sound = MediaPlayer.create(this, R.raw.twenty_five);
                break;
            case 26:
                sound = MediaPlayer.create(this, R.raw.twenty_six);
                break;
            case 27:
                sound = MediaPlayer.create(this, R.raw.twenty_seven);
                break;
            case 28:
                sound = MediaPlayer.create(this, R.raw.twenty_eight);
                break;
            case 29:
                sound = MediaPlayer.create(this, R.raw.twenty_nine);
                break;
            case 30:
                sound = MediaPlayer.create(this, R.raw.thirty);
                break;
            case 31:
                sound = MediaPlayer.create(this, R.raw.thirty_one);
                break;
            case 32:
                sound = MediaPlayer.create(this, R.raw.thirty_two);
                break;
            case 33:
                sound = MediaPlayer.create(this, R.raw.thirty_three);
                break;
            case 34:
                sound = MediaPlayer.create(this, R.raw.thirty_four);
                break;
            case 35:
                sound = MediaPlayer.create(this, R.raw.thirty_five);
                break;
            case 36:
                sound = MediaPlayer.create(this, R.raw.thirty_six);
                break;
            case 37:
                sound = MediaPlayer.create(this, R.raw.thirty_seven);
                break;
            case 38:
                sound = MediaPlayer.create(this, R.raw.thirty_eight);
                break;
            case 39:
                sound = MediaPlayer.create(this, R.raw.thirty_nine);
                break;
            case 40:
                sound = MediaPlayer.create(this, R.raw.fourty);
                break;
            case 41:
                sound = MediaPlayer.create(this, R.raw.fourty_one);
                break;
            case 42:
                sound = MediaPlayer.create(this, R.raw.fourty_two);
                break;
            case 43:
                sound = MediaPlayer.create(this, R.raw.fourty_three);
                break;
            case 44:
                sound = MediaPlayer.create(this, R.raw.fourty_four);
                break;
            case 45:
                sound = MediaPlayer.create(this, R.raw.fourty_five);
                break;
            case 46:
                sound = MediaPlayer.create(this, R.raw.fourty_six);
                break;
            case 47:
                sound = MediaPlayer.create(this, R.raw.fourty_seven);
                break;
            case 48:
                sound = MediaPlayer.create(this, R.raw.fourty_eight);
                break;
            case 49:
                sound = MediaPlayer.create(this, R.raw.fourty_nine);
                break;
            case 50:
                sound = MediaPlayer.create(this, R.raw.fifty);
                break;
            case 51:
                sound = MediaPlayer.create(this, R.raw.fifty_one);
                break;
            case 52:
                sound = MediaPlayer.create(this, R.raw.fifty_two);
                break;
            case 53:
                sound = MediaPlayer.create(this, R.raw.fifty_three);
                break;
            case 54:
                sound = MediaPlayer.create(this, R.raw.fifty_four);
                break;
            case 55:
                sound = MediaPlayer.create(this, R.raw.fifty_five);
                break;
            case 56:
                sound = MediaPlayer.create(this, R.raw.fifty_six);
                break;
            case 57:
                sound = MediaPlayer.create(this, R.raw.fifty_seven);
                break;
            case 58:
                sound = MediaPlayer.create(this, R.raw.fifty_eight);
                break;
            case 59:
                sound = MediaPlayer.create(this, R.raw.fifty_nine);
                break;
            case 60:
                sound = MediaPlayer.create(this, R.raw.sixty);
                break;
            case MINUTE:
                sound = MediaPlayer.create(this, R.raw.minute);
                break;
            case MINUTES:
                sound = MediaPlayer.create(this, R.raw.minutes);
                break;
            case SECOND:
                sound = MediaPlayer.create(this, R.raw.second);
                break;
            case SECONDS:
                sound = MediaPlayer.create(this, R.raw.seconds);
                break;
            case MINUTE_PER_MILE:
                sound = MediaPlayer.create(this, R.raw.minute_per_mile);
                break;
            case PACE:
                sound = MediaPlayer.create(this, R.raw.pace);
                break;
            default:
                sound = MediaPlayer.create(this, R.raw.zero);
                break;
        }
        return sound;
    }

    /**
     * Tells user what pace to run
     */
    private void announcePace(double pace) {
        MediaPlayer phrase[] = new MediaPlayer[5];
        for (int i = 0; i < phrase.length; i++) {
            phrase[i] = null;
        }

        int minute = (int) pace;
        int second = (int) ((pace - minute) * 60);

        // Initialize minute portion of phrase
        phrase[0] = setSound(minute);
        phrase[1] = setSound(MINUTE);

        // Initialize second portion of phrase
        phrase[2] = setSound(second);
        phrase[3] = setSound(SECOND);

        // Initialize final portion of phrase
        phrase[4] = setSound(PACE);

        // Set sound files in phrase to play concurrently
        for (int i = 0; i < phrase.length - 1; i++) {
            phrase[i].setNextMediaPlayer(phrase[i + 1]);
        }

        phrase[0].start();

    }

    /**
     * Begins initial countdown for workout
     */
    public void initialCountdownBegin() {
        pauseButton.setEnabled(false);
        timeRemaining = 3000;
        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                timeRemaining = l;
                updateCountdownTime();
            }

            @Override
            public void onFinish() {
                pauseButton.setEnabled(true);
                updatePaceText("Begin!");
                partOneBegin();
            }
        }.start();

    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partOneBegin() {

        backButton.setEnabled(false); // Disable back button when first part begins
        nextButton.setEnabled(true); // Enable next button when first part begins

        speedCalculator.resetDistance();

        if (partOneFirstRun) {
            timeRemaining = PART_ONE_DURATION;
            tickCounter = 0;
            announcePace(PART_ONE_GOAL_PACE);
            partOneFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 1;

        // Update titles
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);

        final RadioButton partButton1 = (RadioButton) findViewById(R.id.radioButton1);
        partButton1.setChecked(true);

        partOneFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partOneFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_ONE_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

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

                currentPace = 60.0 / speed;
                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    if (Double.compare(paceSum, Double.NaN) == 0) {
                        paceSum = 0.0;
                    }
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(paceAverage);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
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

        speedCalculator.resetDistance();

        if (partTwoFirstRun) {
            timeRemaining = PART_TWO_DURATION;
            tickCounter = 0;
            announcePace(PART_TWO_GOAL_PACE);
            partTwoFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 2;

        // Update titles
        secondaryTitle.setText(PART_TWO_SECONDARY_TITLE);

        final RadioButton partButton2 = (RadioButton) findViewById(R.id.radioButton2);
        partButton2.setChecked(true);

        partTwoFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partTwoFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_TWO_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

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

                double lastPace = currentPace;
                currentPace = 60.0 / speed;
                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }
                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(paceAverage);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
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

        speedCalculator.resetDistance();

        if (partThreeFirstRun) {
            timeRemaining = PART_THREE_DURATION;
            tickCounter = 0;
            announcePace(PART_THREE_GOAL_PACE);
            partThreeFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 3;

        // Update titles
        secondaryTitle.setText(PART_THREE_SECONDARY_TITLE);

        final RadioButton partButton3 = (RadioButton) findViewById(R.id.radioButton3);
        partButton3.setChecked(true);

        partThreeFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partThreeFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_THREE_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

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

                double lastPace = currentPace;
                currentPace = 60.0 / speed;
                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }
                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(paceAverage);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
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
