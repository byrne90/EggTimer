package com.example.byras.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int duration;
    CountDownTimer passedTimerObject;
    MediaPlayer player;

    public void startTimer(View view) {
        Button startButton = (Button) findViewById(R.id.startButton);
        String textOnTheButton = startButton.getText().toString();
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.timerSeekBar);


        if (textOnTheButton.equals("STOP")) {
            startButton.setText("START");
            passedTimerObject.cancel();
            mySeekBar.setEnabled(true);
        } else if (textOnTheButton.equals("START")) {
            passedTimerObject = createAndStartTimer();
            startButton.setText("STOP");
            passedTimerObject.start();
            mySeekBar.setEnabled(false);
        }
        startButton.invalidate();

    }

    public CountDownTimer createAndStartTimer() {
        CountDownTimer count = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final TextView timeCount = (TextView) findViewById(R.id.textView2);
                Log.i("Tick value: ", String.valueOf(millisUntilFinished));
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                if (sec < 10) {
                    timeCount.setText(String.valueOf(min) + ":0" + String.valueOf(sec));
                } else {
                    timeCount.setText(String.valueOf(min) + ":" + String.valueOf(sec));
                }
                timeCount.invalidate();
            }

            @Override
            public void onFinish() {
                final SeekBar mySeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
                mySeekBar.setEnabled(true);
                player = MediaPlayer.create(MainActivity.this, R.raw.alarm);
                player.start();
            }
        };
        return count;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        mySeekBar.setMax(600);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seek = ", String.valueOf(progress));
                TextView textView = findViewById(R.id.textView2);
                int min = (progress / 60) % 60;
                int sec = (progress % 60);
                if (sec < 10) {
                    textView.setText(String.valueOf(min) + ":0" + String.valueOf(sec));

                } else {
                    textView.setText(String.valueOf(min) + ":" + String.valueOf(sec));
                }
                textView.invalidate();
                if (progress == 0) {
                    seekBar.setProgress(1);
                }
                duration = progress * 1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
