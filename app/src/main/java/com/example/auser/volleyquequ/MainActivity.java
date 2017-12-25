package com.example.auser.volleyquequ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView newListView;
    ArrayAdapter adapter;
    ArrayList<String> alr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listview內設定
        newListView =(ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(MainActivity.this
                , android.R.layout.simple_list_item_1
                , alr);
        newListView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        final StringRequest request=new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json"
        , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                try {
//                    JSONArray array=new JSONArray(response);
//                    for (int i=0;i<array.length();i++){
//                        JSONObject obj=array.getJSONObject(i);
//                        alr.add("區域:" + obj.getString("district")
//                                + "\n地址:" + obj.getString("address")
//                                + "\n電話:" + obj.getString("tel")
//                                + "\n聯絡時間:" + obj.getString("opening_hours")
//                        );
//                    }
//                    adapter.notifyDataSetChanged();//請adapter更新資料
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


                Gson gson=new Gson();
                AnimalHome[] ah=gson.fromJson(response,AnimalHome[].class);
                for (int i=0;i<ah.length;i++){
//                    alr.add(ah[i].district);
                    alr.add("區域:" + ah[i].district
                                + "\n地址:" + ah[i].address
                                + "\n電話:" + ah[i].tel
                                + "\n聯絡時間:" + ah[i].opening_hours
                        );
                }
                adapter.notifyDataSetChanged();//請adapter更新資料
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
        queue.start();
    }
}
