package ua.goit.database;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class DatabaseInitService {
    public void initDb(Database database) throws SQLException {
        Flyway flyway = Flyway
                .configure()
                .dataSource(database.getDbUrl(), null, null)
                .load();

        flyway.migrate();
    }



}