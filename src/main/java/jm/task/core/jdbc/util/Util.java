package jm.task.core.jdbc.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Properties prop = new Properties();
            String dbSettingsPropertyFile = "src/main/resources/database.properties";
            FileReader fileReader = new FileReader(dbSettingsPropertyFile);
            prop.load(fileReader);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            if (!"".equals(driver) && !"".equals(url)) {
                Class.forName(driver);
            }


            conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData meta = conn.getMetaData();
            String name = meta.getDatabaseProductName();
            String version = meta.getDatabaseProductVersion();
            System.out.println(name + " " + version);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}


