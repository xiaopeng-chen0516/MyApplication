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
                msg.obj = s;
                mainActivity.handler.sendMessage(msg);
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
                Log.i("线程中的值",s);
                Message msg = new Message();
                msg.obj = s;
                mainActivity.handler.sendMessage(msg);
            }
        }).start();
    }






}

