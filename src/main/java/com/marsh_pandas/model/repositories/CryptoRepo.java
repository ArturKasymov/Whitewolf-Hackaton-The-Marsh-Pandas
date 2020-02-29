package com.marsh_pandas.model.repositories;

import org.mindrot.jbcrypt.BCrypt;

public class CryptoRepo {

    public static boolean comparePasswords(String inputPassword, String userPassword) {
        return BCrypt.checkpw(inputPassword, userPassword);
    }

    public static String encryptPassword(String password){
        if(password==null) throw new NullPointerException();
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
