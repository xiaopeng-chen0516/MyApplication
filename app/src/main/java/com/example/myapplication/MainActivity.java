package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.util.Log;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.threadUtils.TestThread;
import com.example.myapplication.threadUtils.TimeThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TestThread testThread = new TestThread(this);
    TimeThread timeThread = new TimeThread(this);
    public TextView TV1;
    public Switch switch1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.btn);
        Button btn1 = findViewById(R.id.btn1);
        Button thread_btn = findViewById(R.id.thread_btn);
        TV1 = findViewById(R.id.TV1);
        switch1 = findViewById(R.id.switch1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testThread.test1();
                Log.i("执行了", "testThread");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testThread.test2();
                Log.i("执行了", "testThread");

            }
        });




        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            HelloThread h=new HelloThread();
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    if (h.isAlive()){
                        h.running = false; // 标志位置为false
                        try {
                            Thread.sleep(3000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
//                        h.start();
                        Log.i("1", String.valueOf(h.isAlive()));
                    }
                    Log.i("1.1", String.valueOf(h.isAlive()));
                } else {
                    if (h.isAlive()){
                        h.running = false; // 标志位置为false
                        try {
                            Thread.sleep(3000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
//                        h.start();
                        Log.i("2", String.valueOf(h.isAlive()));
                    }
                    Log.i("2.2", String.valueOf(h.isAlive()));
                }
            }
        });





        thread_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloThread h=new HelloThread();

                if (h.isAlive()){
                    h.running = false; // 标志位置为false
                    try {
                        Thread.sleep(1000);
                        h.stop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    h.start();
                }
                Log.i("", String.valueOf(h.isAlive()));
            }
        });

    }


    /**
     * 解析数据
     */
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            Log.i("result", msg.obj.toString());


            switch (msg.obj.toString()) {
                case "1":
                    Toast.makeText(MainActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    break;
                case "2":
                    Toast.makeText(MainActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    try {
                        JSONArray jsonArray = new JSONArray(msg.obj.toString());
                        for (int i = jsonArray.length() - 1; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                            map.put(1, jsonObject.getString("id"));
                            map.put(2, jsonObject.getString("O2"));
                            map.put(3, jsonObject.getString("CO2"));
                            map.put(4, jsonObject.getString("WD"));
                        }
                        Log.i("map", map.toString());
                        Log.i("id-->", map.get(1).toString());
                        Log.i("O2-->", map.get(2).toString());
                        Log.i("CO2-->", map.get(3).toString());
                        Log.i("温度-->", map.get(4).toString());
                        Toast.makeText(MainActivity.this, map.get(3).toString(), Toast.LENGTH_LONG).show();

                        TV1.setText("温度为" + map.get(4).toString() + "℃");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }

        }
    };


    /**
     * 测试用队列
     */
    public Handler handler1 = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, "cs", Toast.LENGTH_SHORT).show();
            }
        }
    };


    class HelloThread extends Thread {
        public volatile boolean running = true;
        public void run() {
            int n = 0;
            while (running) {
                n++;
                System.out.println(n + " hello!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("end!");
        }
    }


}