package com.example.msp.numbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import util.mCustomProgressDialog;

public class OneActivity extends Activity {

    public mCustomProgressDialog mdialog;
    private ImageView iv_frame;
    int i = 1;
    float x1,y1,x2,y2,x3,y3;
    int igvx;
    int igvy;
    int type = 0;
    int widthPixels,heightPixels;
    float scaleWidth,scaleHeight;
    Timer touchTimer = null;
    Bitmap arrdown;
    boolean typedialog = true;
    private LinearLayout linearLayout = null;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        if(MainActivity.isPlay) {
            PlayMusice();
        }

        initView();
    }

    private void PlayMusice() {
        mediaPlayer = MediaPlayer.create(this,R.raw.music1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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

    public void OnYS(View view) {
        if(mdialog == null) {
            mdialog = new mCustomProgressDialog(this,"演示中点击边缘取消演示动画",R.anim.frame1);
        }

        mdialog.show();
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
        lodimagep(1);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        igvx = iv_frame.getLeft();
                        igvy = iv_frame.getTop();
                        if( x1 >= igvx && x1 <= igvx + (int)(arrdown.getWidth() * scaleWidth)&& y1
                            >= igvy && y1 <= igvy + (int)(arrdown.getWidth()*scaleWidth)){
                            type = 1;
                        } else {
                            type = 0;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: 		// 手势移动中判断
                        igvx = iv_frame.getLeft(); 		// 获取图片的X坐标
                        igvy = iv_frame.getTop(); 			// 获取图片的Y坐标
                        x2 = motionEvent.getX(); 				// 获取移动中手指在屏幕X坐标的位置
                        y2 = motionEvent.getY(); 				// 获取移动中手指在屏幕Y坐标的位置
                        // 下边 是根据比划 以及 手势 做图片的处理 滑动到不同位置 加载不同图片
                        if (type == 1) { 					// 如果书写开启
                            // 如果手指按下的X坐标大于等于图片的X坐标，或者小于等于缩放图片的X坐标时
                            if (x2 >= igvx && x2 <= igvx + (int) (arrdown.getWidth() * scaleWidth)) {
                                // 如果当前手指按下的Y坐标小于等于缩放图片的Y坐标，或者大于等于图片的Y坐标时
                                if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 && y2 >= igvy) {
                                    lodimagep(1);      		// 调用lodimagep()方法，加载第一张显示图片
                                }
                                // 如果当前手指按下的Y坐标小于等于缩放图片的Y坐标
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 2) {
                                    lodimagep(2);            // 调用lodimagep()方法，加载第二张显示图片
                                }
                                // 如果当前手指按下的Y坐标小于等于缩放图片的Y坐标
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 3) {
                                    lodimagep(3);            // 调用lodimagep()方法，加载第三张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 4) {
                                    lodimagep(4);            // 调用lodimagep()方法，加载第四张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 5) {
                                    lodimagep(5);            // 调用lodimagep()方法，加载第五张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 6) {
                                    lodimagep(6);            // 调用lodimagep()方法，加载第六张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 7) {
                                    lodimagep(7);            // 调用lodimagep()方法，加载第七张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 8) {
                                    lodimagep(8);            // 调用lodimagep()方法，加载第八张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 9) {
                                    lodimagep(9);            // 调用lodimagep()方法，加载第九张显示图片
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 10) {
                                    lodimagep(10);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 11) {
                                    lodimagep(11);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 12) {
                                    lodimagep(12);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 13) {
                                    lodimagep(13);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 14) {
                                    lodimagep(14);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 15) {
                                    lodimagep(15);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 16) {
                                    lodimagep(16);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 17) {
                                    lodimagep(17);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 18) {
                                    lodimagep(18);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 19) {
                                    lodimagep(19);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 20) {
                                    lodimagep(20);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 21) {
                                    lodimagep(21);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 22) {
                                    lodimagep(22);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 23) {
                                    lodimagep(23);
                                }
                                else if (y2 <= igvy + (int) (arrdown.getHeight() * scaleHeight) / 24 * 24) {
                                    lodimagep(24);   //加载最后一张图片时，将在lodimagep()方法中调用书写完成对话框
                                }
                                else {
                                    type = 0;         // 手指离开 设置书写关闭
                                }

                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:					// 手势抬起判断
                        type = 0; 								// 手势关闭
                        // 当手指离开的时候
                        if (touchTimer != null) { 				// 判断计时器是否为空
                            touchTimer.cancel(); 				// 中断计时器
                            touchTimer = null; 					// 设置计时器为空
                        }
                        touchTimer = new Timer(); 				// 初始化计时器
                        touchTimer.schedule(new TimerTask() { 	// 开启时间计时器
                            @Override
                            public void run() {
                                Thread thread = new Thread(new Runnable() { //创建子线程
                                    @Override
                                    public void run() {
                                        // 创建Message用于发送消息
                                        Message message = new Message();
                                        message.what = 2; 			// message消息为2
                                        // 发送消息给handler实现倒退显示图片
                                        mHandler.sendMessage(message);
                                    }
                                });
                                thread.start();					// 开启线程
                            }
                        }, 300, 200);                          // 设置0.3秒后执行定时器，定时器每0.2秒发送一次


                }     // 获取行动方式尾部




                return true;
            }
        });
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    jlodimage();
                    break;
                    default:
                        break;
            }
        }
    };

    private void jlodimage() {  	//当手势抬起时数字资源图片倒退显示jlodimage()方法头部
        if (i == 25) { 			// 如果当前图片位置等于25
        } else if (i < 25) { 		// 否则如果当前图片小于25
            if (i > 1) { 			// 如果当前图片大于1
                i--;
            } else if (i == 1) { 	// 否则如果当前图片等于1
                i = 1;
                if (touchTimer != null) { 	// 判断计时器是否为空
                    touchTimer.cancel(); 	// 中断计时器
                    touchTimer = null; 		// 设置计时器为空
                }
            }
            String name = "on1_" + i; 		// 图片的名称
            // 获取图片资源
            int imgid = getResources().getIdentifier(name, "drawable",
                    "com.example.msp.numbergame");
            // 给imageview设置图片
            iv_frame.setBackgroundResource(imgid);
        }
    }  //当手势抬起时数字资源图片倒退显示jlodimage()方法尾部

    private synchronized void lodimagep(int j) {
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
                lodimagep(i);
            }
        });

        builder.create().show();
    }
}
