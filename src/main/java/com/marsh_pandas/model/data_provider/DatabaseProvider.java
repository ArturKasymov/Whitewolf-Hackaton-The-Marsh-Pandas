package com.marsh_pandas.model.data_provider;

import java.net.URI;
import java.sql.*;

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

}