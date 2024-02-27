package ua.goit.database;

import lombok.Data;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Data
public class Database {
    private static final Database INSTANCE = new Database();
    private static final String DB_URL = "jdbc:h2:./SpaceTravel";
    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Database getInstance() {
        return INSTANCE;
    }

    public String getDbUrl() {
        return DB_URL;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
