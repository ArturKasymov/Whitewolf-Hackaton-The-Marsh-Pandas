package com.marsh_pandas.model.interactors;

<<<<<<< HEAD

=======
import com.marsh_pandas.model.Entites.Product;
>>>>>>> dodana encja Produktu, proste wyciąganie ich z bazy
import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.repositories.CryptoRepo;

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

    public void addProductToUserFridge(int product_id,int user_token, int ilosc){
        provider.addProductToUserFridge(product_id, user_token, ilosc);
    }
}
