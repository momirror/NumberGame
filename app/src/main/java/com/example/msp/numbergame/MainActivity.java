package com.example.msp.numbergame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnPlay(View v) {
        startActivity(new Intent(MainActivity.this,SelectActivity.class));
    }

    public void OnAbout(View v) {
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }


}
