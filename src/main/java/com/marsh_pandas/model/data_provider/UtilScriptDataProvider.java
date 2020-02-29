package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.entities.UtilEntityProduct;

import java.math.BigDecimal;
import java.util.List;

public interface UtilScriptDataProvider {

    void insertProduct(String productName, BigDecimal kcal, BigDecimal protein, BigDecimal fats, BigDecimal carbohydrates);
    void insertAdminReceipt(String receiptName, String description, List<UtilEntityProduct> list_products);

}
