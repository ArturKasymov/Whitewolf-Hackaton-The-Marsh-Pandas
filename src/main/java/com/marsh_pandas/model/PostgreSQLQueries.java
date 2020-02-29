package com.marsh_pandas.model;

public class PostgreSQLQueries {

    public static final String CHECK_USERS = "CREATE TABLE IF NOT EXISTS users (\n" +
            "user_id serial primary key,\n" +
            "user_name varchar(32) not null unique,\n" +
            "permission_group INTEGER not null,\n" +
            "password_hash varchar(256) not null\n" +
            ");";
    public static final String CHECK_PROJECTS = "CREATE TABLE IF NOT EXISTS projects (\n" +
            "id serial primary key,\n" +
            "name varchar(64) not null unique\n" +
            ");";
    public static final String CHECK_PLATFORMS = "CREATE TABLE IF NOT EXISTS platforms(\n" +
            "id serial primary key,\n" +
            "name varchar(64) not null unique\n" +
            ");";
    public static final String CHECK_APPLICATIONS = "CREATE TABLE IF NOT EXISTS applications (\n" +
            "id serial primary key,\n" +
            "name varchar(64) not null,\n" +
            "platform_id INTEGER REFERENCES platforms,\n" +
            "project_id INTEGER REFERENCES projects\n" +
            ");";
    public static final String CHECK_AD_UNIT_GROUPS = "CREATE TABLE IF NOT EXISTS ad_unit_groups (\n" +
            "id serial primary key,\n" +
            "name varchar(64) not null unique\n" +
            ");";
    public static final String CHECK_AD_UNITS = "CREATE TABLE IF NOT EXISTS ad_units (\n" +
            "id serial primary key,\n" +
            "application_id INTEGER REFERENCES applications,\n" +
            "name varchar(64) not null,\n" +
            "ad_unit_group_id INTEGER REFERENCES ad_unit_groups\n" +
            ");";
    public static final String CHECK_AD_UNIT_STATS = "CREATE TABLE IF NOT EXISTS ad_unit_stats (\n" +
            "id serial primary key,\n" +
            "ad_unit_id INTEGER REFERENCES ad_units,\n" +
            "stat_date TIMESTAMP not null,\n" +
            "estimated_earnings numeric(10,2) not null,\n" +
            "impressions numeric(16) not null,\n" +
            "clicks numeric(16) not null,\n" +
            "CONSTRAINT unique_ad_unit_date UNIQUE (ad_unit_id,stat_date)" +
            ");";
    public static final String CHECK_USER_CHARTS = "CREATE TABLE IF NOT EXISTS user_charts (\n" +
            "id serial primary key,\n" +
            "user_id INTEGER REFERENCES users,\n" +
            "name varchar(256) not null,\n" +
            "url varchar(512) not null\n" +
            ");";

    //GET
    public static final String GET_USER_HASH = "SELECT password_hash FROM users WHERE user_name=?;";
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
    public static final String INSERT_USER = "INSERT INTO users(user_name, permission_group, password_hash) " +
            "VALUES (?,?,?) RETURNING user_id;";
    public static final String INSERT_PROJECT = "INSERT INTO projects(name) VALUES(?) RETURNING id;";
    public static final String INSERT_PLATFORM = "INSERT INTO platforms(name) VALUES(?) RETURNING id;";
    public static final String INSERT_APPLICATION = "INSERT INTO applications(name, platform_id, project_id) " +
            "VALUES (?,?,?) RETURNING id;";
    public static final String INSERT_AD_UNIT_GROUP = "INSERT INTO ad_unit_groups(name) VALUES(?) RETURNING id;";
    public static final String INSERT_AD_UNIT = "INSERT INTO ad_units(application_id, name, ad_unit_group_id) " +
            "VALUES (?,?,?) RETURNING id;";
    public static final String INSERT_AD_UNIT_STATS = "INSERT INTO ad_unit_stats(ad_unit_id, stat_date, estimated_earnings, " +
            "impressions, clicks) VALUES(?,?,?,?,?) " +
            "ON CONFLICT ON CONSTRAINT unique_ad_unit_date " +
            "DO UPDATE SET " +
            "estimated_earnings = EXCLUDED.estimated_earnings, " +
            "impressions = EXCLUDED.impressions, " +
            "clicks = EXCLUDED.clicks;";
    public static final String INSERT_USER_CHART = "INSERT INTO user_charts(user_id, name, url) VALUES (?,?,?);";
    //UPDATE

    //DELETE
    public static final String DELETE_USER_CHART = "DELETE FROM user_charts WHERE id=?;";


}
