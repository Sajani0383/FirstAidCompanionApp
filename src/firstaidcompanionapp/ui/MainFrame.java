package firstaidcompanionapp.ui;

import firstaidcompanionapp.ui.screens.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Screens
    private LoginScreen loginScreen;
    private HomeScreen homeScreen;
    private SymptomMenuScreen symptomMenuScreen;
    private SymptomScreen symptomScreen;
    private EmergencyScreen emergencyScreen;
    private HospitalScreen hospitalScreen;
    private UserMedicalRecordScreen userMedicalRecordScreen;
    private FamilyContactsScreen familyContactsScreen;

    private String loggedInUser;

    public MainFrame() {
        setTitle("🩺 First Aid Companion");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Initialize screens
        loginScreen = new LoginScreen(this);
        homeScreen = new HomeScreen(this);
        symptomMenuScreen = new SymptomMenuScreen(this);
        symptomScreen = new SymptomScreen(this);
        emergencyScreen = new EmergencyScreen(this);
        hospitalScreen = new HospitalScreen(this);
        userMedicalRecordScreen = new UserMedicalRecordScreen(this);
        familyContactsScreen = new FamilyContactsScreen(this);

        // Add to layout
        mainPanel.add(loginScreen, "LOGIN");
        mainPanel.add(homeScreen, "HOME");
        mainPanel.add(symptomMenuScreen, "SYMPTOM_MENU");
        mainPanel.add(symptomScreen, "SYMPTOMS");
        mainPanel.add(emergencyScreen, "EMERGENCY");
        mainPanel.add(hospitalScreen, "HOSPITALS");
        mainPanel.add(userMedicalRecordScreen, "MEDICAL_RECORD");
        mainPanel.add(familyContactsScreen, "FAMILY_CONTACTS");

        showScreen("LOGIN");
    }

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void setLoggedInUser(String username) {
        this.loggedInUser = username;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public SymptomScreen getSymptomScreen() {
        return symptomScreen;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
