package com.team4.progex;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectDatabaseTest {
    static Connection connection;

    @BeforeClass
    public static void beforeClass(){
        try (FileInputStream f = new FileInputStream("C:\\Users\\admin\\team4\\src\\main\\java\\com\\example\\db.properties")) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            // create a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find driver class");
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Cannot connect to MySQL");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @AfterClass
    public static void afterClass() {
        // close db connection
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("WARNING: cannot close MySQL connection");
        }
    }

    void loadFixtures(String[] fixtures) {
        int i = 0;
        try {
            // truncate all tables
            connection.createStatement().execute("CALL TRUNCATE_ALL()");

            // load data from fixture files to tables
            for (; i < fixtures.length; i++)
                executeSQLFile(fixtures[i]);
        } catch (SQLException e) {
            System.err.println("Corrupted fixture file " + fixtures[i]);
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void executeSQLFile(String filePath) throws SQLException {
        try {
            // open file
            URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
            if (url == null) {
                throw new FileNotFoundException("Fixture " + filePath + " is not found");
            }

            File file = new File(url.getPath());
            BufferedReader br = new BufferedReader(new FileReader(file));

            // read the whole file content
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null)
                sb.append(s);
            br.close();

            // Execute SQL file
            Statement statement = connection.createStatement();
            statement.executeUpdate(sb.toString());

        } catch (IOException e) {
            System.err.println("Error while loading fixture in file " + filePath);
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
