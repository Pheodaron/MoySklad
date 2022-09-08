package com.moysklad.auxilary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        try {
            PropertiesReader reader = new PropertiesReader();
            config.setJdbcUrl(reader.getProperty("database-postgres-url"));
            config.setUsername(reader.getProperty("database-username"));
            config.setPassword(reader.getProperty("database-password"));
            config.addDataSourceProperty("cachePrepStmts" , "true");
            config.addDataSourceProperty("prepStmtCacheSize" , "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
            config.setDriverClassName(reader.getProperty("database-driver"));
            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
