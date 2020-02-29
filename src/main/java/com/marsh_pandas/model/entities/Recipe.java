package com.marsh_pandas.model.entities;

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

        public String getJSON(){
            return "{" +
                    "id:" + this.id +","+
                    "nazwa:" + this.nazwa +","+
                    "opis:" + this.opis +
                    "}";
        }
}