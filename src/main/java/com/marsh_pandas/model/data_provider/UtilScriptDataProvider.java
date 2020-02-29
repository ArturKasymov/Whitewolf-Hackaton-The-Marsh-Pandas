package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.entities.Product;

import java.util.List;

public interface UtilScriptDataProvider {

    void insertProduct(String productName, String value);
    void insertReceip(String receipName, String opis, List<Product> list_products);

}
