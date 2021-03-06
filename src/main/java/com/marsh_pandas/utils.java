package com.marsh_pandas;

import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.data_provider.UtilScriptDataProvider;
import com.marsh_pandas.model.entities.UtilEntityProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
            dataProvider.insertProduct("Yogurt",
                                        new BigDecimal("41.8"),
                                        new BigDecimal("2.6"),
                                        new BigDecimal("1.4"),
                                        new BigDecimal("0.7"));
            dataProvider.insertProduct("Rice",
                                        new BigDecimal("70.4"),
                                        new BigDecimal("5.6"),
                                        new BigDecimal("1.0"),
                                        new BigDecimal("11.7"));
            dataProvider.insertProduct("Potatoes",
                                        new BigDecimal("75.4"),
                                        new BigDecimal("5.6"),
                                        new BigDecimal("1.0"),
                                        new BigDecimal("11.7"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void insertReceipts(){

        try {
            UtilScriptDataProvider dataProvider = new DatabaseProvider();
            
            List<UtilEntityProduct> lp1 = new ArrayList<>();
            UtilEntityProduct p1 = new UtilEntityProduct("Banana", new BigDecimal("100"));
            UtilEntityProduct p2 = new UtilEntityProduct("Yogurt", new BigDecimal("250"));
            UtilEntityProduct p3 = new UtilEntityProduct("Cornflakes", new BigDecimal("150"));
            
            lp1.add(p1);
            lp1.add(p2);
            lp1.add(p3);

            List<UtilEntityProduct> lp2 = new ArrayList<>();
            UtilEntityProduct p4 = new UtilEntityProduct("Fish", new BigDecimal("150"));
            UtilEntityProduct p5 = new UtilEntityProduct("Tomatoes", new BigDecimal("100"));
            UtilEntityProduct p6 = new UtilEntityProduct("Rice", new BigDecimal("150"));

            lp2.add(p4);
            lp2.add(p5);
            lp2.add(p6);

            List<UtilEntityProduct> lp3 = new ArrayList<>();
            UtilEntityProduct p7 = new UtilEntityProduct("Chicken wings", new BigDecimal("200"));
            UtilEntityProduct p8 = new UtilEntityProduct("Cucumbers", new BigDecimal("150"));
            UtilEntityProduct p9 = new UtilEntityProduct("Tomatoes", new BigDecimal("150"));

            lp3.add(p7);
            lp3.add(p8);
            lp3.add(p9);

            dataProvider.insertAdminReceipt("Yummy Banana", "slice the banana, open yogurt and mix with banana and add cornflakes to create the best meal!", lp1);
            dataProvider.insertAdminReceipt("Jewy fish", "Prepare fish by removing fishbones if necessary, slice tomatoes, boil rice and that's all! ", lp2);
            dataProvider.insertAdminReceipt("Happy Meat", "Fry chicken wings, slice cucumbers, boil tomatoes, you could also add any spices if you wish!", lp3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
