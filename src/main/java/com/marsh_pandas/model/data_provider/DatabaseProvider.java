package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.entities.Product;

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


    public String getUserPassword(String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_HASLO);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.isClosed()) return null;
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(String username, String encryptPassword) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(DODAJ_UZYTKOWNIKA);
        pstmt.setString(1,username);
        pstmt.setString(2,encryptPassword);
        pstmt.execute();
        ResultSet rs =  pstmt.getResultSet();
        rs.next();
        return rs.getInt(1);
    }

    public int getUserID(String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_USER_ID);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.isClosed()) return -1;
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

    public boolean addProductToUserFridge(int product_id, int user_token, int ilosc){
        try {
            PreparedStatement pstmt = connection.prepareStatement(DODAJ_PRODUKTY_UZYTKOWNIKA);
            pstmt.setInt(1,product_id);
            pstmt.setInt(2,user_token);
            pstmt.setInt(3,ilosc);
            pstmt.execute();

            return true;
        } catch(Exception e){

        }
        return false;
    }
}