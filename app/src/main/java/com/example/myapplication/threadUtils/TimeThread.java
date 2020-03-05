package com.example.myapplication.threadUtils;

import android.os.Message;
import android.util.Log;

import com.example.myapplication.MainActivity;

public class TimeThread extends Thread {
    private MainActivity mainActivity;


    public TimeThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public volatile boolean running=true;

    @Override
    public void run() {

        while (running){

            try {
                Message msg = new Message();
                msg.what = 1;
                mainActivity.handler1.sendMessage(msg);
                Log.i("执行", "线程");
//                if (this.isInterrupted()) break;
                Log.i("TimeThread中", String.valueOf(this.isInterrupted()));
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


