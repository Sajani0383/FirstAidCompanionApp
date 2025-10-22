package firstaidcompanionapp.services;

public class UserService {

    private DatabaseService databaseService;

    // ✅ Add constructor to accept DatabaseService
    public UserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // Default constructor (optional)
    public UserService() {}

    // Example methods (you can expand later)
    public boolean validateUser(String username, String password) {
        // For now, static validation
        return username.equalsIgnoreCase("admin") && password.equals("1234");
    }

    public void saveUserData(String name, String contact, String bloodGroup) {
        // Placeholder for saving user data
        System.out.println("Saved user data for: " + name);
    }
}
