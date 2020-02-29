package com.marsh_pandas.model.interactors;


import com.marsh_pandas.model.entities.Product;
import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.entities.Recipe;
import com.marsh_pandas.model.repositories.CryptoRepo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.GET_PRODUKTY_PRZEPISU;
import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.GET_PRZEPIS;


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

    public List<Product> getUserFridgeProducts(String user_token) {

        List<Product> list_product=provider.getUserFridgeProducts(user_token);
        return list_product;
    }

    public List<Product> getAllProducts() {

        List<Product> list_product=provider.getAllProducts();
        return list_product;
    }

    public void addProductToUserFridge(int product_id,int user_token, int ilosc){
        provider.addProductToUserFridge(product_id, user_token, ilosc);
    }



    public Recipe getRecipe(int id_recipe){
        try {
            Recipe recipe=provider.getRecipe(id_recipe);
            return recipe;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Recipe> getRecipesByAuthor(int id_author){
        try {
            List<Recipe> list_recipe=provider.getRecipesByAuthor(id_author);
            return list_recipe;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProductsRecipe(int id_recipe){
        try {
            List<Product> list_products=provider.getProductsRecipe(id_recipe);
            return list_products;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public List<Product> getProductsShop(int id_shop){
        try {
            List<Product> list_products=provider.getProductsShop(id_shop);
            return list_products;
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
