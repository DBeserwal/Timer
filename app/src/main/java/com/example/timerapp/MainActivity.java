package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    ImageView bombImageView;
    ImageView blastImageView;
    TextView countDownTextView;
    Button startCountDownButton;

    CountDownTimer countDownTimer;

    Boolean IsCountDownActive = false;

    public void funcStartCountDown(View view){


        if(IsCountDownActive){


            countDownTimer.cancel();
            startCountDownButton.setText("START !");
            IsCountDownActive = false;
            timerSeekBar.setEnabled(true);


        }

        else {

            IsCountDownActive = true;

            startCountDownButton.setText("STOP !");
            timerSeekBar.setEnabled(false);

            bombImageView.setAlpha(1f);
            blastImageView.setAlpha(0f);

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000, 1000){


                @Override
                public void onTick(long millisUntilFinished) {


                    String str1;
                    String str2 = "00";



                    if((millisUntilFinished/1000) < 10)
                        str1 = "0"+Integer.toString((int)millisUntilFinished/1000);

                    else
                        str1 = Integer.toString((int)millisUntilFinished/1000);


                    if((millisUntilFinished/1000) > 60)
                        str2 = Integer.toString((int) (millisUntilFinished/60000));


                    countDownTextView.setText(str2+":"+str1);

                    timerSeekBar.setProgress((int)millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {



                    Log.i("CountDown Finished !!", " ");

                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bomb_blast_sound);
                    mplayer.start();

                    bombImageView.animate().alpha(0f).setDuration(8000);
                    blastImageView.animate().alpha(1f).setDuration(8000);


                    startCountDownButton.setText("START !");
                    IsCountDownActive = false;
                    timerSeekBar.setEnabled(true);




                }
            }.start();



        }
       }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        bombImageView = (ImageView) findViewById(R.id.bombImageView);
        blastImageView = (ImageView) findViewById(R.id.blastImageView);
        countDownTextView = (TextView) findViewById(R.id.countDownTextView);
        startCountDownButton = (Button) findViewById(R.id.startCountDownButton);

        timerSeekBar.setMax(120);
        timerSeekBar.setProgress(80);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                
                String str1;
                String str2 = "00";



                if(progress < 10)
                    str1 = "0"+Integer.toString(progress);

                else
                    str1 = Integer.toString(progress);


                if(progress > 60)
                    str2 = Integer.toString(progress);


                countDownTextView.setText(str2+":"+str1);

                timerSeekBar.setProgress(progress);


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