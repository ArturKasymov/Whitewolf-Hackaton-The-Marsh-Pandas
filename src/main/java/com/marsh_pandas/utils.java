package com.marsh_pandas;

import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.data_provider.UtilScriptDataProvider;

import java.math.BigDecimal;

public class utils {

    public static void insertProducts(){

        try {
            UtilScriptDataProvider dataProvider = new DatabaseProvider();

            dataProvider.insertProduct("Banana",
                                        new BigDecimal("89.3"),
                                        new BigDecimal("1.1"),
                                        new BigDecimal("0.3"),
                                        new BigDecimal("22.8"));
            dataProvider.insertProduct("Butter",
                                        new BigDecimal("111.1"),
                                        new BigDecimal("0.7"),
                                        new BigDecimal("111"),
                                        new BigDecimal("2.3"));
            dataProvider.insertProduct("Cheese",
                                        new BigDecimal("149.9"),
                                        new BigDecimal("7.1"),
                                        new BigDecimal("7.8"),
                                        new BigDecimal("1.3"));
            dataProvider.insertProduct("Chicken wings",
                                        new BigDecimal("156.7"),
                                        new BigDecimal("6.9"),
                                        new BigDecimal("8.7"),
                                        new BigDecimal("0.9"));
            dataProvider.insertProduct("Cornflakes",
                                        new BigDecimal("86.1"),
                                        new BigDecimal("0.8"),
                                        new BigDecimal("5.7"),
                                        new BigDecimal("11.0"));
            dataProvider.insertProduct("Eggs",
                                        new BigDecimal("123"),
                                        new BigDecimal("7.2"),
                                        new BigDecimal("9.6"),
                                        new BigDecimal("1.5"));
            dataProvider.insertProduct("Cucumber",
                                        new BigDecimal("21.6"),
                                        new BigDecimal("1.1"),
                                        new BigDecimal("0.6"),
                                        new BigDecimal("1.4"));
            dataProvider.insertProduct("Tomatoes",
                                        new BigDecimal("24.0"),
                                        new BigDecimal("1.5"),
                                        new BigDecimal("0.5"),
                                        new BigDecimal("1.2"));
            dataProvider.insertProduct("Ham",
                                        new BigDecimal("62.3"),
                                        new BigDecimal("12.1"),
                                        new BigDecimal("4.6"),
                                        new BigDecimal("1.3"));
            dataProvider.insertProduct("Fish",
                                        new BigDecimal("183.8"),
                                        new BigDecimal("8.6"),
                                        new BigDecimal("11.2"),
                                        new BigDecimal("2.1"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
