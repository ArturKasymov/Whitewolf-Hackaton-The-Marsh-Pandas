package com.marsh_pandas.model.entities;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Product extends BaseApplicationEntity{
    private int id;
    private String nazwa;
    private BigDecimal kcal;
    private BigDecimal protein;
    private BigDecimal fats;
    private BigDecimal carbohydrates;

    public Product(int id, String nazwa, BigDecimal kcal, BigDecimal protein, BigDecimal fats, BigDecimal carbohydrates) {
        this.id=id;
        this.nazwa=nazwa;
        this.kcal=kcal;
        this.protein=protein;
        this.fats=fats;
        this.carbohydrates=carbohydrates;
    }

    public int getId(){
        return id;
    }

    public String getNazwa(){
        return nazwa;
    }

    public JSONObject getJSON(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", nazwa);
        json.put("kcal", kcal);
        json.put("protein", protein);
        json.put("fats", fats);
        json.put("carbohydrates", carbohydrates);
        return json;
    }
}