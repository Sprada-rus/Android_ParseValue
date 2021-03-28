package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Currency {
    private CurrencyDetail attr;
    private String name;


    public Currency(String attr, String name){
        Gson gson = new Gson();
        this.attr = gson.fromJson(attr, CurrencyDetail.class);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
