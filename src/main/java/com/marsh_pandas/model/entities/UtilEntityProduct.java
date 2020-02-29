package com.marsh_pandas.model.entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;

public class UtilEntityProduct {

    private int id = -1;
    private String name = null;
    private BigDecimal quantity;

    public UtilEntityProduct(String name, BigDecimal quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public UtilEntityProduct(int id, BigDecimal quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public UtilEntityProduct(int id, String name, BigDecimal quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public void setID(int id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.id;
    }

    public BigDecimal getQuantity(){
        return this.quantity;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("name", name);
        json.put("quantity", quantity);

        return json;
    }
}
