package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlankFragment extends ListFragment {
    private Runnable run;
    private Thread thread;
    private Document doc;
    private String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    private ListView listView;
    private ArrayList<Currency> currencyObjects  = new ArrayList<>();
    private List<String> names = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initNewThread();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void initNewThread(){
        run = new Runnable() {
            @Override
            public void run() {
                getWebValue();
            }

        };

        thread = new Thread(run);
        thread.start();

        if(!thread.isAlive()){

        }
    }

    private void getWebValue(){
        try{
            doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsCode = doc.text();
            JSONObject value = new JSONObject(jsCode).getJSONObject("Valute");
            Iterator<String> listJs = value.keys();
            Log.d("parselog",String.valueOf(value.length()));
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
    }

    private void nameJsObjs(){

    }
}