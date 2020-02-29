package com.marsh_pandas;

import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.data_provider.UtilScriptDataProvider;

import java.math.BigDecimal;

public class utils {

    public static void insertProducts(){

        try {
            UtilScriptDataProvider dataProvider = new DatabaseProvider();

            dataProvider.insertProduct("Banana",
                                        new BigDecimal("89"),
                                        new BigDecimal("1.1"),
                                        new BigDecimal("0.3"),
                                        new BigDecimal("22.8"));
            

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
