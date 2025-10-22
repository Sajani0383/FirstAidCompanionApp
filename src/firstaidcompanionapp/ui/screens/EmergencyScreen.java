package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class EmergencyScreen extends JPanel {

    private final MainFrame mainFrame;

    public EmergencyScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 2, 30, 30));
        setBackground(new Color(255, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton callAmbulance = createButton("🚑 Call Ambulance (108)", new Color(204, 0, 0));
        JButton callPolice = createButton("👮 Call Police (100)", new Color(0, 51, 204));
        JButton myMedical = createButton("📋 My Medical Record", new Color(0, 153, 76));
        JButton familyContacts = createButton("👨‍👩‍👧 Family Contacts", new Color(255, 153, 51));
        JButton backButton = createButton("🔙 Back", new Color(128, 0, 0));

        // Actions
        callAmbulance.addActionListener(e -> makeCall("108"));
        callPolice.addActionListener(e -> makeCall("100"));
        myMedical.addActionListener(e -> mainFrame.showScreen("MEDICAL_RECORD"));
        familyContacts.addActionListener(e -> mainFrame.showScreen("FAMILY_CONTACTS"));
        backButton.addActionListener(e -> mainFrame.showScreen("HOME"));

        add(callAmbulance);
        add(callPolice);
        add(myMedical);
        add(familyContacts);
        add(backButton);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        return btn;
    }

    private void makeCall(String number) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start tel:" + number);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open tel:" + number);
            } else {
                Desktop.getDesktop().browse(new java.net.URI("tel:" + number));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open dialer. Please call manually: " + number);
        }
    }
}
