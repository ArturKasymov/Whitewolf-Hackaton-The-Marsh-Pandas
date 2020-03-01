package com.marsh_pandas.model.entities;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Recipe extends BaseApplicationEntity{

        private int id;
        private String nazwa;
        private String opis;
        private BigDecimal total_kcal;

        public Recipe(int id, String nazwa, String opis, BigDecimal total_kcal) {
            this.id=id;
            this.nazwa=nazwa;
            this.opis=opis;
            this.total_kcal = total_kcal;
        }

        public int getId(){
            return id;
        }

        public String getNazwa(){
            return nazwa;
        }

        public String getOpis(){
            return opis;
        }

        public JSONObject getJSON(){

            JSONObject json = new JSONObject();

            json.put("id", id);
            json.put("name", nazwa);
            json.put("description", opis);
            json.put("total_kcal",total_kcal);
            return json;
        }
}