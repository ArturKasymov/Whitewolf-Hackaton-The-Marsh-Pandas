package com.marsh_pandas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseProvider{

    private Connection connection;

    public DatabaseProvider() throws Exception{
        connection = DriverManager.getConnection(System.getenv("DATABASE_URL"));

        //connection = DriverManager.getConnection(String.format("jdbc:postgresql:%s:%d/%s",HOST,port,dbname),username,password);


        ResultSet rs = connection.prepareStatement("SELECT NOW();").executeQuery();
        System.out.println(rs.getDate(1).toString());
    }



}