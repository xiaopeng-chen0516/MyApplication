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
            try {
                JSONArray jsonArray=new JSONArray(msg.obj.toString());
                for (int i=jsonArray.length()-1;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject (i);  //解析JSON数据
                    map.put(1,jsonObject.getString("id"));
                    map.put(2,jsonObject.getString("O2"));
                    map.put(3,jsonObject.getString("CO2"));
                }
                Log.i("id-->",map.get(1).toString());
                Log.i("O2-->",map.get(2).toString());
                Log.i("CO2-->",map.get(3).toString());
                Toast.makeText(MainActivity.this, map.get(2).toString(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    public static Map<String, Object> getStringToMap(String str) {
        String ss = str.replaceAll("[\\{\\}\\[\\]]", "");
        //根据逗号截取字符串数组
        String[] str1 = ss.split(",");
        //创建Map对象
        Map<String, Object> map = new HashMap<>();
        //循环加入map集合
        for (int i = 0; i < str1.length; i++) {
            //根据":"截取字符串数组
            String[] str2 = str1[i].split("=");
            //str2[0]为KEY,str2[1]为值
            map.put(str2[0], str2[1]);
        }
        return map;

    }

    public static Map<String, String> mapStringToMap(String str) {
//        str = str.substring(1, str.length() - 1);
        String ss = str.replaceAll("\\{\\}\\[\\]", "");
        String[] strs = ss.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;


    }


}