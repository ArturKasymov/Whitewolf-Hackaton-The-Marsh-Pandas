package com.marsh_pandas.model.data_provider;

public class PostgreSQLQueries {

    public static final String CHECK_UZYTKOWNICY = "CREATE TABLE IF NOT EXISTS uzytkownicy (\n" +
            "id_uzytkownika serial primary key,\n" +
            "email varchar(48) not null unique,\n" +
            "haslo varchar(256) not null\n" +
            ");";
    public static final String CHECK_PRODUKTY = "CREATE TABLE IF NOT EXISTS produkty (\n" +
            "id_produktu serial primary key,\n" +
            "nazwa varchar(64) not null unique,\n" +
            "jednostka varchar(64) not null\n" +
            ");";
    public static final String CHECK_PRODUKTY_UZYTKOWNIKA = "CREATE TABLE IF NOT EXISTS produkty_uzytkownika(\n" +
            "id_produktu INTEGER REFERENCES produkty,\n" +
            "id_uzytkownika INTEGER REFERENCES uzytkownicy,\n" +
            "ilosc INTEGER not null\n" +
            ");";
    public static final String CHECK_PRZEPIS = "CREATE TABLE IF NOT EXISTS przepisy (\n" +
            "id_przepisu serial primary key,\n" +
            "nazwa varchar(64) not null,\n" +
            "opis varchar(4096) not null\n" +
            ");";
    public static final String CHECK_PRODUKTY_PRZEPIS = "CREATE TABLE IF NOT EXISTS produkty_przepis (\n" +
            "id_przepisu INTEGER REFERENCES przepisy,\n" +
            "id_produktu INTEGER REFERENCES produkty,\n" +
            "ilosc INTEGER not null\n" +
            ");";
    public static final String CHECK_SKLEP = "CREATE TABLE IF NOT EXISTS sklepy (\n" +
            "id_sklepu serial primary key,\n" +
            "nazwa varchar(64) not null\n" +
            ");";
    public static final String CHECK_PRODUKTY_SKLEPU = "CREATE TABLE IF NOT EXISTS produkty_sklepu (\n" +
            "id_sklepu INTEGER REFERENCES sklepy,\n" +
            "id_produktu INTEGER REFERENCES produkty,\n" +
            "ilosc INTEGER not null\n" +
            ");";

    //GET
    public static final String GET_HASLO = "SELECT haslo FROM uzytkownicy u WHERE u.email=?;";
    public static final String GET_UZYTKOWNIK = "SELECT u.id_uzytkownika, u.email, u.haslo FROM uzytkownicy u WHERE u.id_uzytkownika=?;";
    public static final String GET_WSZYSCY_UZYTKOWNICY = "SELECT u.id_uzytkownika, u.email, u.haslo FROM uzytkownicy u;";

    public static final String GET_PRODUKT = "SELECT p.id_produktu, p.nazwa, p.jednostka  FROM produkty p WHERE p.id_produktu=?;";
    public static final String GET_WSZYSTKIE_PRODUKTY = "SELECT p.id_produktu, p.nazwa, p.jednostka  FROM produkty p;";
    public static final String GET_PRODUKTY_UZYTKOWNIKA =
            "SELECT p.id_produktu, p.nazwa, p.jednostka FROM produkty p " +
            "JOIN produkty_uzytkownika pu ON p.id_produktu = pu.id_produktu " +
            "JOIN uzytkownicy u ON u.id_uzytkownika = pu.id_uzytkownika " +
            "WHERE u.id_uzytkownika=?;";
    public static final String GET_PRODUKTY_PRZEPISY = "" +
            "SELECT p.id_produktu, p.nazwa, p.jednostka  FROM produkty p " +
            "JOIN produkty_przepisy pp ON p.id_produktu = pp.id_produktu " +
            "JOIN przepisy pr ON pr.id_przepisu = pp.id_przepisu " +
            "WHERE pr.id_przepisu=?;";
    public static final String GET_WSZYSTKIE_PRZEPISY = "SELECT p.id_przepisu, p.nazwa, p.opis FROM przepisy p;";
    public static final String GET_PRODUKTY_SKLEPU =
            "SELECT p.id_produktu, p.nazwa, p.jednostka  FROM produkty p " +
            "JOIN produkty_sklepu ps ON p.id_produktu = ps.id_produktu " +
            "JOIN sklepy s ON s.id_sklepu = ps.id_przepisu " +
            "WHERE s.id_sklepu=?;";
    public static final String GET_WSZYSTKIE_SKLEPY = "SELECT s.id_sklepu, s.nazwa FROM sklepy s;";





    public static final String GET_USER_PERMISSION_GROUP = "SELECT permission_group FROM users WHERE user_name=?;";
    public static final String GET_AD_UNIT_ID = "SELECT ad.id from ad_units ad " +
            "JOIN applications app ON app.id = ad.application_id " +
            "WHERE app.name=? AND ad.name=?;";

    public static final String GET_APPLICATIONS = "SELECT id, name from applications ORDER BY 1";
    public static final String GET_PLATFORMS = "SELECT id, name from platforms ORDER BY 1";
    public static final String GET_AD_UNIT_GROUPS = "SELECT id, name from ad_unit_groups ORDER BY 1";

    public static final String GET_APPLICATIONS_BY_IDS = "SELECT id, name from applications WHERE id IN (?) ORDER BY 1";
    public static final String GET_PLATFORMS_BY_IDS = "SELECT id, name from platforms WHERE id IN (?) ORDER BY 1";
    public static final String GET_AD_UNIT_GROUPS_IDS = "SELECT id, name from ad_unit_groups WHERE id IN (?) ORDER BY 1";

    public static final String GET_TOTAL_INCOME = "SELECT sum(estimated_earnings) from ad_unit_stats;";
    public static final String GET_TOTAL_IMPRESSIONS = "SELECT sum(impressions) from ad_unit_stats;";

    //INSERT
    public static final String DODAJ_UZYTKOWNIKA = "INSERT INTO uzytkownicy(email, haslo) " +
            "VALUES (?,?) RETURNING id_uzytkownika;";
    public static final String DODAJ_PRODUKT = "INSERT INTO produkty(nazwa,jednostka) VALUES(?,?) RETURNING id_produktu;";

    public static final String DODAJ_PRZEPIS = "INSERT INTO przepisy(nazwa) VALUES(?) RETURNING id_przepisu;";

    public static final String DODAJ_SKLEP = "INSERT INTO sklepy(nazwa) VALUES(?) RETURNING id_sklepu;";

    public static final String DODAJ_PRODUKTY_UZYTKOWNIKA = "INSERT INTO produkty_uzytkownika(id_produktu,id_uzytkownika, ilosc) " +
            "VALUES (?,?,?);";

    //UPDATE

    //DELETE
    //public static final String USUN_PRODUKT = "DELETE FROM user_charts WHERE id=?;";


}
