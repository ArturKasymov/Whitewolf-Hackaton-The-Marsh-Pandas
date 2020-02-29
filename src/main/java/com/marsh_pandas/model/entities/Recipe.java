package com.marsh_pandas.model.entities;

import org.json.JSONObject;

public class Recipe {

        private int id;
        private String nazwa;
        private String opis;

        public Recipe(int id, String nazwa, String opis) {
            this.id=id;
            this.nazwa=nazwa;
            this.opis=opis;
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

            return json;
        }
}