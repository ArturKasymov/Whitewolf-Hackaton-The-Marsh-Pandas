package com.marsh_pandas.model.interactors;

import com.marsh_pandas.model.Entites.Product;
import com.marsh_pandas.model.data_provider.DatabaseProvider;

import java.util.List;

public class Interactor implements LoginInteractor {

    private static Interactor instance;

    private DatabaseProvider provider;

    public synchronized static Interactor getInstance() throws Exception {
        if(instance == null)
            instance  = new Interactor();
        return instance;
    }

    private Interactor() throws Exception {
        this.provider = new DatabaseProvider();
    }

    public boolean checkUserLoginData(String email, String password) {
        return false;
    }

    public List<Product> getUserFridgeProducts(String user_token) {

        List<Product> list_product=provider.getUserFridgeProducts(user_token);
        return list_product;
    }
}
