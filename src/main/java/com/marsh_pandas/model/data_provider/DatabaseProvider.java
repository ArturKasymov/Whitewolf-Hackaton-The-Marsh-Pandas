package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.Entites.Product;

import java.net.URI;
import java.sql.*;
import java.util.*;

import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.*;

public class DatabaseProvider{

    private Connection connection;

    public DatabaseProvider() throws Exception{
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        connection = DriverManager.getConnection(dbUrl, username, password);

        checkDatabase();
    }

    private void checkDatabase() throws SQLException {

        Statement stmt=connection.createStatement();

        stmt.execute(CHECK_UZYTKOWNICY);
        stmt.execute(CHECK_PRODUKTY);
        stmt.execute(CHECK_PRODUKTY_UZYTKOWNIKA);
        stmt.execute(CHECK_PRZEPIS);
        stmt.execute(CHECK_PRODUKTY_PRZEPIS);
        stmt.execute(CHECK_SKLEP);
        stmt.execute(CHECK_PRODUKTY_SKLEPU);
    }

    public List<Product> getUserFridgeProducts(String user_token){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRODUKTY_UZYTKOWNIKA);
            pstmt.setString(1,user_token);
            ResultSet rs = pstmt.executeQuery();
            List<Product> list_products = new ArrayList<Product>();
            while(rs.next()){
                int id=rs.getInt(0);
                String nazwa = rs.getString(1);
                String jednostka = rs.getString(2);
                list_products.add(new Product(id,nazwa,jednostka));
            }
            return list_products;
        } catch(Exception e){

        }
        return null;
    }
}