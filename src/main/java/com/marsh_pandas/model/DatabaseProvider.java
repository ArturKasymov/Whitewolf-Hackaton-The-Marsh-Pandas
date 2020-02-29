package com.marsh_pandas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseProvider{

    private Connection connection;

    public DatabaseProvider() throws Exception{
        connection = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));

        ResultSet rs = connection.prepareStatement("SELECT NOW();").executeQuery();
        System.out.println(rs.getDate(1).toString());
    }



}