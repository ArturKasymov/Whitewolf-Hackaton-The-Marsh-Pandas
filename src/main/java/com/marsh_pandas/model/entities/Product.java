package com.marsh_pandas.model.entities;

public class Product {
    private int id;
    private String nazwa;
    private String jednostka;

    public Product(int id, String nazwa, String jednostka) {
        this.id=id;
        this.nazwa=nazwa;
        this.jednostka=jednostka;
    }

    public int getId(){
        return id;
    }

    public String getNazwa(){
        return nazwa;
    }

    public String getJednostka(){
        return jednostka;
    }

    public String getJSON(){
        return "{" +
                "id:" + this.id +","+
                "nazwa:" + this.nazwa +","+
                "jednostka:" + this.jednostka +
                "}";
    }
}
