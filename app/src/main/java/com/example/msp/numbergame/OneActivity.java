package com.example.msp.numbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

public class OneActivity extends Activity {

    private ImageView iv_frame;
    int i = 1;
    float x1,y1,x2,y2,x3,y3;
    int igvx;
    int igvy;
    int widthPixels,heightPixels;
    float scaleWidth,scaleHeight;
    Timer touchTimer = null;
    Bitmap arrdown;
    boolean typedialog = true;
    private LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        initView();
    }

    private  void initView() {
        iv_frame = findViewById(R.id.iv_frame);
        linearLayout = (LinearLayout)findViewById(R.id.LinearLayout1);
        LinearLayout write_layout = (LinearLayout)findViewById(R.id.LinearLayout_number);
        write_layout.setBackgroundResource(R.drawable.bg1);
        widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        heightPixels = this.getResources().getDisplayMetrics().heightPixels;
        scaleWidth = (float)widthPixels/720;
        scaleHeight = (float)heightPixels/1280;

        try{
            InputStream is = getResources().getAssets().open("on1_1.png");
            arrdown = BitmapFactory.decodeStream(is);
        }catch (IOException e){
            e.printStackTrace();
        }

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)iv_frame.getLayoutParams();
        layoutParams.width = (int) (arrdown.getWidth()*scaleHeight);
        layoutParams.height = (int)(arrdown.getHeight()*scaleHeight);

        iv_frame.setLayoutParams(layoutParams);
        loadimagep(1);
    }

    private synchronized void loadimagep(int j) {
        i = j;

        if(i < 25) {
            String name = "on1_"+i;
            int imgid = getResources().getIdentifier(name,"drawable","com.example.msp.numbergame");
            iv_frame.setBackgroundResource(imgid);
            i++;
        }

        if(j == 24) {
            if(typedialog) {
                dialog();
            }
        }
    }

    protected  void dialog() {
        typedialog = false;

        AlertDialog.Builder builder = new AlertDialog.Builder(OneActivity.this);
        builder.setMessage("太棒了，书写完成");
        builder.setTitle("提示");

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                typedialog = true;
                finish();
            }
        });

        builder.setNegativeButton("再来一次", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                typedialog = true;
                i = 1;
                loadimagep(i);
            }
        });

        builder.create().show();
    }
}
