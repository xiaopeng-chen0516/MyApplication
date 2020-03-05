package com.example.myapplication.threadUtils;

import android.os.Message;
import android.util.Log;

import com.example.myapplication.MainActivity;
import com.example.myapplication.utils.NetUilts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestThread {

    private MainActivity mainActivity;


    public  TestThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void test1() {
        /**
         * get请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginOfGet("111","111");

                Message msg = new Message();
                if (s!=null){
                    Log.i("线程中的值",s);

                    msg.obj = 2;
                    mainActivity.handler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    mainActivity.handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void test2() {
        /**
         * POST请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginofPost("111","111");
                Message msg = new Message();
                if (s!=null){
                    Log.i("线程中的值",s);
                    msg.obj = s;
                    mainActivity.handler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    mainActivity.handler.sendMessage(msg);
                }

            }
        }).start();
    }

    public void test3() {
        /**
         * POST请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        Message msg = new Message();
                        msg.what = 1;
                        mainActivity.handler1.sendMessage(msg);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            }).start();
    }



        public Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5000);
                        Message msg=new Message();
                        msg.what=1;
                        mainActivity.handler1.sendMessage(msg);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });







}


