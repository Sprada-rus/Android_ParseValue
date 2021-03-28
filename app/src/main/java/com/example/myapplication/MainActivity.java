package com.example.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private Document doc;
    private String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    private ListView listView;
    private ArrayList<Currency> currencyObjects  = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private Runnable runnable;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNewThread();
    }

    public void getListView() {
            listView = (ListView) findViewById(R.id.list_item);
            adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, names);
            listView.setAdapter(adapter);
    }

    private void initNewThread() {
        getListView();
        runnable = new Runnable() {
            @Override
            public void run() {
                getWebValue();
            }
        };


        thread = new Thread(runnable);
        thread.start();
    }

    private void getWebValue(){
        try{
            doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsCode = doc.text();
            JSONObject value = new JSONObject(jsCode).getJSONObject("Valute");
            Iterator<String> listJs = value.keys();
            while(listJs.hasNext()){
                String name = listJs.next();
                String attr = value.get(name).toString();
                Currency currency = new Currency(attr, name);
                currencyObjects.add(currency);
            }

            for (Currency i : currencyObjects){
                names.add(i.getName());
            }
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }



    private void nameJsObjs(){

    }

}