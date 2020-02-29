package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface UtilScriptDataProvider {

    void insertProduct(String productName, BigDecimal kcal, BigDecimal protein, BigDecimal fats, BigDecimal carbohydrates);
   // void insertReceipt(String receiptName, String description, List<Product> list_products);

}
