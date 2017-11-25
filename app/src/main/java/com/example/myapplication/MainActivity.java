package com.example.myapplication;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RedPacketTest redRainView1;
    private TextView start, stop;
    private int totalmoney = 0;
    private int nu = 0;
    //倒计时
    private MyCountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (TextView) findViewById(R.id.start);
        stop = (TextView) findViewById(R.id.stop);
        redRainView1 = (RedPacketTest) findViewById(R.id.red_packets_view1);
        mCountDownTimer = new MyCountDownTimer(500000,1000);
        mCountDownTimer.start();
        startRedRain();
    }
    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *      表示以毫秒为单位 倒计时的总数
         *
         *      例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            start.setText("done");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            start.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
        }
    }

    /**
     * 开始下红包雨
     */
    private void startRedRain() {
        redRainView1.startRain();
        redRainView1.setOnRedPacketClickListener(new RedPacketTest.OnRedPacketClickListener() {
            @Override
            public void onRedPacketClickListener(RedPacket redPacket) {
                if (redPacket.isRealRed) {
                    nu++;
                    stop.setText("" + nu);
                }

            }
        });
    }

    /**
     * 停止下红包雨
     */
    private void stopRedRain() {
        totalmoney = 0;//金额清零
        redRainView1.stopRainNow();
    }
}