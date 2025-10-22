package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {

    private final MainFrame mainFrame;

    public HomeScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridLayout(2, 2, 40, 40));
        setBackground(new Color(245, 250, 255));
        setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 100));

        JButton symptomChecker = createButton("🩺 Check Symptoms", new Color(0, 102, 204));
        JButton emergencyHelp = createButton("🚨 Emergency Help", new Color(204, 51, 51));
        JButton nearbyHospitals = createButton("🏥 Nearby Hospitals", new Color(0, 153, 76));
        JButton logout = createButton("🔙 Logout", new Color(153, 0, 0));

        // Button actions
        symptomChecker.addActionListener(e -> mainFrame.showScreen("SYMPTOM_MENU"));
        emergencyHelp.addActionListener(e -> mainFrame.showScreen("EMERGENCY"));
        nearbyHospitals.addActionListener(e -> mainFrame.showScreen("HOSPITALS"));
        logout.addActionListener(e -> mainFrame.showScreen("LOGIN"));

        add(symptomChecker);
        add(emergencyHelp);
        add(nearbyHospitals);
        add(logout);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        return btn;
    }
}
