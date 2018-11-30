package com.example.byras.eggtimer;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int duration;

    public void startTimer (View view){
        final TextView timeCount = (TextView) findViewById(R.id.textView2);
        final SeekBar mySeekBar =  (SeekBar) findViewById(R.id.timerSeekBar);
        Button startButton = (Button) findViewById(R.id.startButton);

        CountDownTimer count;
        String textOnTheButton = startButton.getText().toString();

        count = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeCount.setText(String.valueOf(millisUntilFinished/1000));
                // NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timeCount.setText(String.valueOf(min)+":"+String.valueOf(sec));
                timeCount.invalidate();
            }

            @Override
            public void onFinish() {
                mySeekBar.setEnabled(true);
            }
        };

        if (textOnTheButton.equals("STOP")){
            startButton.setText("START");
            count.cancel();
            mySeekBar.setEnabled(true);
        }else {
            startButton.setText("STOP");
            count.start();
            mySeekBar.setEnabled(false);
        }
        startButton.invalidate();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar mySeekBar =  (SeekBar) findViewById(R.id.timerSeekBar);

        mySeekBar.setMax(600);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seek = ", String.valueOf(progress));
                if (progress==0){
                    seekBar.setProgress(1);
                }
                duration = progress *1000;
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
