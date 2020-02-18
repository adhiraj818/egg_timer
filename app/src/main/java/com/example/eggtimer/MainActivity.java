package com.example.eggtimer;

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

public class MainActivity extends AppCompatActivity {

    SeekBar sk;
    TextView txt;
    String str;
    int sec,min;
    ImageView img;
    Button b1,b2;
    boolean active=false;
    CountDownTimer countdowntimer;

    public void reset(){
        txt.setText(Integer.toString(min)+":"+str);
        sk.setProgress(sk.getProgress());
        countdowntimer.cancel();
        sk.setEnabled(true);
        b1.setText("START");
        active=false;
    }

    public void update_timer(int secleft){
         min=(int) secleft/60;
         sec=secleft-min*60;
         str =Integer.toString(sec);

    if(sec<10)
            str="0"+str;

        txt.setText(Integer.toString(min)+":"+str);

    }

    public void onclickstart(View view) {
        if (active == false) {
            active=true;
            sk.setEnabled(false);
            b1.setText("STOP");
            b2.setAlpha(0);

         countdowntimer = new CountDownTimer(sk.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {

                    update_timer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    reset();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    img.setImageResource(R.drawable.crackegg);
                    txt.setAlpha(0);
                    b1.setAlpha(0);
                    b2.setAlpha(1);
                    sk.setAlpha(0);
                }
            }.start();
        }
        else{
            reset();
    }
    }

    public void onclickreset(View view){
        b1.setAlpha(1);
        txt.setAlpha(1);
        b2.setAlpha(0);
        img.setImageResource(R.drawable.egg);
        sk.setAlpha(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

              sk=(SeekBar)findViewById(R.id.seekBar);
              txt=(TextView)findViewById(R.id.textView) ;
              b1=(Button)findViewById(R.id.b1);
              b2=(Button)findViewById(R.id.b2);
              img=(ImageView)findViewById(R.id.imageView);

        sk.setMax(10*60);
        sk.setProgress(30);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               update_timer(i);
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
