package com.example.jsonvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

// add permission in manifest
//<uses-permission android:name="android.permission.INTERNET"/>
// to create createJson Object ion URL
//go to http://myjson.com/api then there createJson Object

//Volley is a networking library for Android that manages network requests.
// The RequestQueue manages worker threads for running the network operations,
// reading from and writing to the cache, and parsing responses

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    RequestQueue rq;
    ArrayList al;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv);
        button=findViewById(R.id.but1);
        al=new ArrayList<>();
        rq= Volley.newRequestQueue(this);
    }

    public void dothis(View view) {
        String url = "https://api.myjson.com/bins/uga5i";

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jarr = null;
                try {
                    jarr = response.getJSONArray("student");
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject stu = jarr.getJSONObject(i);
                        String name = stu.getString("name");
                        long mob = stu.getLong("mobno");
                        String s = name + " " + mob;

                        al.add(s);
                        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, al);
                        listView.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rq.add(jreq);

    }


}



