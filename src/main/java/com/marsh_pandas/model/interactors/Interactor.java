package com.marsh_pandas.model.interactors;

import com.marsh_pandas.model.data_provider.DatabaseProvider;
import com.marsh_pandas.model.repositories.CryptoRepo;

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
        String userHashedPassword = provider.getUserPassword(email);
        return CryptoRepo.comparePasswords(userHashedPassword, CryptoRepo.encryptPassword(password));
    }
    
}
