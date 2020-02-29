package com.marsh_pandas.model;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseProvider{

    private Connection connection;

    public DatabaseProvider() throws Exception{


        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        connection = DriverManager.getConnection(dbUrl, username, password);


        ResultSet rs = connection.prepareStatement("SELECT NOW();").executeQuery();
        System.out.println(rs.getDate(1).toString());
    }



}