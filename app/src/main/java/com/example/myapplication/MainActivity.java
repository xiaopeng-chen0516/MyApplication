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
import android.widget.Toast;

import com.example.myapplication.threadUtils.TestThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TestThread testThread = new TestThread(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.btn);
        Button btn1 = findViewById(R.id.btn1);

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
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            Log.i("result", msg.obj.toString());
            Map<Integer,Object> map=new HashMap<Integer,Object>();

            switch (msg.obj.toString()) {
                case "1":
                    Toast.makeText(MainActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    break;
                case "2":
                    Toast.makeText(MainActivity.this, "获取成功", Toast.LENGTH_SHORT).show();

                default:
                    try {
                        JSONArray jsonArray = new JSONArray(msg.obj.toString());
                        for (int i = jsonArray.length() - 1; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                            map.put(1, jsonObject.getString("id"));
                            map.put(2, jsonObject.getString("O2"));
                            map.put(3, jsonObject.getString("CO2"));
                        }
                        Log.i("id-->", map.get(1).toString());
                        Log.i("O2-->", map.get(2).toString());
                        Log.i("CO2-->", map.get(3).toString());
                        Toast.makeText(MainActivity.this, map.get(3).toString(), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }


        }
    };


}