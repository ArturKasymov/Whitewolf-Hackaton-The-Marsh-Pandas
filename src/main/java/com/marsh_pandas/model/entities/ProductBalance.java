package com.marsh_pandas.model.entities;

import org.json.JSONObject;

import java.math.BigDecimal;

public class ProductBalance extends BaseApplicationEntity{

    private int id;
    private String name;
    private BigDecimal balance;

    public ProductBalance(int id, String name, BigDecimal balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public JSONObject getJSON(){

        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("balance", balance);

        return json;
    }

}
