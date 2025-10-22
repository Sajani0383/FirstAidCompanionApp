package firstaidcompanionapp.services;

import java.sql.*;

public class DatabaseService {

    /**
     * Default: relative path (project root) so the project is portable.
     * If this doesn't work for you, temporarily replace with an absolute path,
     * for example: "C:/Users/YourName/Path/To/FirstAidCompanionApp/resources/firstaid.db"
     */
    private static final String DB_PATH = "resources/firstaid.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    public DatabaseService() {
        System.out.println("DatabaseService -> using DB: " + DB_PATH);
        // Optionally print absolute path for debugging:
        try {
            System.out.println("Absolute DB path: " + new java.io.File(DB_PATH).getAbsolutePath()
                    + " (exists=" + new java.io.File(DB_PATH).exists() + ")");
        } catch (Exception ignored) {}
    }

    /**
     * Get a JDBC connection to the SQLite database.
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Retrieve the instruction text for a symptom (case-insensitive).
     * Returns human-readable messages on missing input, not-found, or DB errors.
     */
    public String getInstruction(String symptomName) {
        if (symptomName == null || symptomName.trim().isEmpty()) {
            return "Please enter a symptom.";
        }

        final String normalized = symptomName.trim();
        final String sql = "SELECT instruction FROM symptoms WHERE LOWER(name) = LOWER(?) LIMIT 1";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, normalized);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String instruction = rs.getString("instruction");
                    if (instruction == null || instruction.isBlank()) {
                        return "No instruction text available for '" + normalized + "'.";
                    }
                    return instruction;
                } else {
                    return "No first aid information found for '" + normalized + "'.";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // useful for debugging in NetBeans console
            return "Database error: " + e.getMessage();
        }
    }
}
