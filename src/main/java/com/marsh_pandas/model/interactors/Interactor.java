package com.marsh_pandas.model.interactors;


import com.marsh_pandas.model.entities.Product;
import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.entities.ProductBalance;
import com.marsh_pandas.model.entities.Recipe;
import com.marsh_pandas.model.entities.UtilEntityProduct;
import com.marsh_pandas.model.repositories.CryptoRepo;
import com.marsh_pandas.utils;

import java.math.BigDecimal;
import java.util.List;


public class Interactor implements LoginInteractor, RegistrationInteractor {

    private static Interactor instance;

    private DatabaseProvider provider;

    public synchronized static Interactor getInstance() throws Exception {
        if(instance == null)
            instance  = new Interactor();
        return instance;
    }

    private Interactor() throws Exception {
        this.provider = new DatabaseProvider();

        /*
        provider.insertUser("admin@gmail.com", CryptoRepo.encryptPassword("12345678"));
        utils.insertProducts();
        utils.insertReceipts();
        */
    }

    @Override
    public int checkUserLoginData(String email, String password) {
        String userHashedPassword = provider.getUserPassword(email);
        if (userHashedPassword== null) {
            return -1;
        }
        if (CryptoRepo.comparePasswords(password, userHashedPassword)){
            return provider.getUserID(email);
        };
        return -1;
    }

    @Override
    public int registerUser(String email, String password) {
        try {
            return provider.insertUser(email, CryptoRepo.encryptPassword(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<UtilEntityProduct> getUserFridgeProducts(String user_token) {
        return provider.getUserFridgeProducts(user_token);
    }

    public List<Product> getAllProducts() {
        return provider.getAllProducts();
    }

    public void addProductToUserFridge(int product_id,int user_token, BigDecimal ilosc){
        provider.addProductToUserFridge(product_id, user_token, ilosc);
    }

    public void deleteProductFromUserFridge(int product_id,int user_token, BigDecimal ilosc){
        provider.deleteProductFromUserFridge(product_id, user_token, ilosc);
    }

    public Recipe getRecipe(int id_recipe){
        try {
            return provider.getRecipe(id_recipe);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Recipe> getRecipesByAuthor(int id_author){
        try {
            return provider.getRecipesByAuthor(id_author);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Recipe> getRecipesForUser(int user_token){
        try {
            return provider.getRecipesForUser(user_token);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public List<Product> getProductsRecipe(int id_recipe){
        try {
            return provider.getProductsRecipe(id_recipe);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductBalance> getRecipeProductsBalance(int recipt_id, int user_token){
        try {
            return provider.getRecipeProductsBalance(recipt_id, user_token);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProductsShop(int id_shop){
        try {
            return provider.getShopProducts(id_shop);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void dropAllTables(){
        try {
            provider.dropAllTables();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
