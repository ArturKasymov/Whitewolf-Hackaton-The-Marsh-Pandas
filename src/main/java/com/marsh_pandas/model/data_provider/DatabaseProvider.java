package com.marsh_pandas.model.data_provider;

import com.marsh_pandas.model.entities.Product;
import com.marsh_pandas.model.entities.ReceiptProduct;
import com.marsh_pandas.model.entities.Recipe;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.*;
import java.util.*;

import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.*;

public class DatabaseProvider implements UtilScriptDataProvider{

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

    public List<Product> getProductsShop(int id_shop){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRODUKTY_SKLEPU);
            pstmt.setInt(1,id_shop);
            ResultSet rs = pstmt.executeQuery();
            List<Product> list_products = new ArrayList<>();
            while(rs.next()){
                list_products.add(new Product(rs.getInt(1),rs.getString(2), rs.getBigDecimal(3),rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getBigDecimal(6)));
            }
            return list_products;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getUserFridgeProducts(String user_token){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRODUKTY_UZYTKOWNIKA);
            pstmt.setString(1,user_token);
            ResultSet rs = pstmt.executeQuery();
            List<Product> list_products = new ArrayList<>();
            while(rs.next()){
                list_products.add(new Product(rs.getInt(1),rs.getString(2), rs.getBigDecimal(3),rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getBigDecimal(6)));
            }
            return list_products;
        } catch(Exception e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getAllProducts(){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_WSZYSTKIE_PRODUKTY);
            ResultSet rs = pstmt.executeQuery();
            List<Product> list_products = new ArrayList<Product>();
            while(rs.next()){
                list_products.add(new Product(rs.getInt(1),rs.getString(2), rs.getBigDecimal(3),rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getBigDecimal(6)));
            }
            return list_products;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Recipe getRecipe(int id_recipe){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRZEPIS);
            pstmt.setInt(1,id_recipe);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.isClosed()) return null;
            return new Recipe(rs.getInt(1),rs.getString(3),rs.getString(4));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Recipe> getRecipesByAuthor(int id_author){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRZEPISY_PO_AUTORZE);
            pstmt.setInt(1,id_author);
            ResultSet rs = pstmt.executeQuery();
            List<Recipe> list_recipes = new ArrayList<Recipe>();
            while(rs.next()){
                int id=rs.getInt(1);
                String nazwa = rs.getString(2);
                String opis = rs.getString(3);
                list_recipes.add(new Recipe(id,nazwa,opis));
            }
            return list_recipes;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public List<Product> getProductsRecipe(int id_recipe){
        try {
            PreparedStatement pstmt = connection.prepareStatement(GET_PRODUKTY_PRZEPISU);
            pstmt.setInt(1,id_recipe);
            ResultSet rs = pstmt.executeQuery();
            List<Product> list_products = new ArrayList<>();
            while(rs.next()){
                list_products.add(new Product(rs.getInt(1),rs.getString(2), rs.getBigDecimal(3),rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getBigDecimal(6)));
            }
            return list_products;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void insertProduct(String productName, BigDecimal kcal, BigDecimal protein, BigDecimal fats, BigDecimal carbohydrates) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(DODAJ_PRODUKT);
            pstmt.setString(1, productName);
            pstmt.setBigDecimal(2, kcal);
            pstmt.setBigDecimal(3, protein);
            pstmt.setBigDecimal(4, fats);
            pstmt.setBigDecimal(5, carbohydrates);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropAllTables(){
        try {
            PreparedStatement pstmt = connection.prepareStatement(DROP_UZYTKOWNICY);
            pstmt.execute();
            PreparedStatement pstmt2 = connection.prepareStatement(DROP_PRODUKTY);
            pstmt2.execute();
            PreparedStatement pstmt3 = connection.prepareStatement(DROP_PRODUKTY_UZYTKOWNIKA);
            pstmt3.execute();
            PreparedStatement pstmt4 = connection.prepareStatement(DROP_PRZEPIS);
            pstmt4.execute();
            PreparedStatement pstmt5 = connection.prepareStatement(DROP_PRODUKTY_PRZEPIS);
            pstmt5.execute();
            PreparedStatement pstmt6 = connection.prepareStatement(DROP_SKLEP);
            pstmt6.execute();
            PreparedStatement pstmt7 = connection.prepareStatement(DROP_PRODUKTY_SKLEPU);
            pstmt7.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertAdminReceipt(String receiptName, String description, List<ReceiptProduct> list_products) {
        try {

            int receipt_id = insertReceipt(1, receiptName, description);

            //TODO rewrite
            PreparedStatement pstmtProducts = connection.prepareStatement(GET_WSZYSTKIE_PRODUKTY);
            ResultSet rsp = pstmtProducts.executeQuery();

            Map<String, Integer> name_to_id = new HashMap<>();

            while (rsp.next()){
                name_to_id.put(rsp.getString(2), rsp.getInt(1));
            }
            for(ReceiptProduct rp : list_products) rp.setID(name_to_id.get(rp.getName()));
            for(ReceiptProduct rp : list_products) insertReceiptProduct(receipt_id, rp.getID(), rp.getQuantity());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertReceipt(int userID, String receiptName, String description) throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement(DODAJ_PRZEPIS);
        pstmt.setInt(1, userID); //AdminID
        pstmt.setString(2, receiptName);
        pstmt.setString(3, description);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    private void insertReceiptProduct(int receipt_id, int product_id, BigDecimal quantity ) throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement(DODAJ_PRODUCT_PRZEPISU);
        pstmt.setInt(1, receipt_id);
        pstmt.setInt(2, product_id);
        pstmt.setBigDecimal(3, quantity);
        pstmt.execute();
    }

}