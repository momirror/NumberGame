package com.example.msp.numbergame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    static boolean isPlay = true;
    MediaPlayer mediaPlayer;
    Button music_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music_btn = findViewById(R.id.btn_music);
        PlayMusic();
    }

    private void PlayMusic() {
        mediaPlayer = MediaPlayer.create(this,R.raw.main_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void OnMusic(View v) {

        if(isPlay) {
            if(mediaPlayer != null) {
                mediaPlayer.stop();
                music_btn.setBackgroundResource(R.drawable.btn_music2);
                isPlay = false;
            }
        } else {
            PlayMusic();
            music_btn.setBackgroundResource(R.drawable.btn_music1);
            isPlay = true;
        }
    }

    public void OnPlay(View v) {
        startActivity(new Intent(MainActivity.this,SelectActivity.class));
    }

    public void OnAbout(View v) {
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }

    protected void onStop() {
        super.onStop();

        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }

    }

    protected void onDestroy() {

        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    protected void onRestart() {
        super.onRestart();
        if(isPlay) {
            PlayMusic();
        }
    }


}
