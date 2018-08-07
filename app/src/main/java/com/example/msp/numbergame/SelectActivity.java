package com.example.msp.numbergame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectActivity extends Activity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        if(MainActivity.isPlay) {
            PlayMusic();
        }
    }

    private void PlayMusic() {
        mediaPlayer = MediaPlayer.create(this,R.raw.number_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    protected void onStop(){
        super.onStop();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    protected void onRestart() {
        super.onRestart();
        if(MainActivity.isPlay) {
            PlayMusic();
        }
    }

}
