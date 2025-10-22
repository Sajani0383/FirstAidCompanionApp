package firstaidcompanionapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String DB_PATH = "resources/firstaid.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createTable = """
                CREATE TABLE IF NOT EXISTS symptoms (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    instruction TEXT NOT NULL
                );
            """;

            stmt.execute(createTable);

            stmt.executeUpdate("INSERT INTO symptoms (name, instruction) VALUES ('burn', 'Cool under running water for 10–15 minutes. Cover with clean cloth. Avoid butter.');");
            stmt.executeUpdate("INSERT INTO symptoms (name, instruction) VALUES ('cut', 'Clean with antiseptic and cover with sterile bandage.');");
            stmt.executeUpdate("INSERT INTO symptoms (name, instruction) VALUES ('fever', 'Rest, hydrate, and take paracetamol if required.');");

            System.out.println("✅ Database created successfully at: " + DB_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
