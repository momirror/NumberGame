package com.example.msp.numbergame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.Timer;

public class OneActivity extends Activity {

    int i = 1;
    float x1,y1,x2,y2,x3,y3;
    int igvx;
    int igvy;
    int widthPixels,heightPixels;
    float scaleWidth,getScaleHeight;
    Timer touchTimer = null;
    boolean typedialog = true;
    private LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
    }
}
