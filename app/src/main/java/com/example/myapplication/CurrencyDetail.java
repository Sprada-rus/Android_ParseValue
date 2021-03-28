package com.example.myapplication;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrencyDetail {
    @SerializedName("ID")
    private String ID;
    @SerializedName("NumCode")
    private String NumCode;
    @SerializedName("CharCode")
    private String CharCode;
    @SerializedName("Nominal")
    private int Nominal;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Value")
    private float Value;
    @SerializedName("Previous")
    private float Previous;


    public CurrencyDetail(String ID, String NumCode, String CharCode, int Nominal, String Name, float Value, float Previous){
        this.ID = ID;
        this.NumCode = NumCode;
        this.CharCode = CharCode;
        this.Nominal = Nominal;
        this.Name = Name;
        this.Value = Value;
        this.Previous = Previous;
    }

    public String getName() {
        return Name;
    }

}
