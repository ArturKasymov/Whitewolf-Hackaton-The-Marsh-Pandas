package com.marsh_pandas.model.interactors;


import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.repositories.CryptoRepo;

import java.sql.SQLException;

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
    public boolean checkUserLoginData(String email, String password) {
        String userHashedPassword = provider.getUserPassword(email);
        if (userHashedPassword== null) {
            //TODO need registration
        }

        System.out.println(userHashedPassword + " "+ password);

        return CryptoRepo.comparePasswords(userHashedPassword, CryptoRepo.encryptPassword(password));
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
}
