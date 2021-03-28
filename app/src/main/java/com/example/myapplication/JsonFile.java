package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonFile {
    @SerializedName("Date")
    private String date;
    @SerializedName("PreviousDate")
    private String previousDate;
    @SerializedName("PreviousURL")
    private String previousURL;
    @SerializedName("Timestamp")
    private String timestamp;
    @SerializedName("Valute")
    private Currency values;

    private List<CurrencyDetail> currencies;

    public JsonFile(String date, String previousDate, String previousURL, String timestamp, Currency values){
        this.date = date;
        this.previousDate = previousDate;
        this.previousURL = previousURL;
        this.timestamp = timestamp;
        Gson gson = new Gson();
        this.values = values;
    //        currencies.add(values);
    }


//    public Currency getValues() {
//        return values;
//    }

    public List<CurrencyDetail> getCurrencies() {
        return currencies;
    }
}
