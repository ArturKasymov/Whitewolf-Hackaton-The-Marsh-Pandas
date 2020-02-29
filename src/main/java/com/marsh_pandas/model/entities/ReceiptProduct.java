package com.marsh_pandas.model.entities;

import java.math.BigDecimal;

public class ReceiptProduct {

    private int id = -1;
    private String name = null;
    private BigDecimal quantity;

    public ReceiptProduct(String name, BigDecimal quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public ReceiptProduct(int id, BigDecimal quantity){
        this.id = id;
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
}
