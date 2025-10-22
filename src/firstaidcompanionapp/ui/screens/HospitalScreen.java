package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import firstaidcompanionapp.ui.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;

public class HospitalScreen extends JPanel {

    public HospitalScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Theme.BACKGROUND_LIGHT);

        JLabel title = new JLabel("🏥 Nearby Hospitals", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY_BLUE);
        add(title, BorderLayout.NORTH);

        JPanel hospitalPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        hospitalPanel.setBackground(Theme.BACKGROUND_LIGHT);
        hospitalPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Add hospitals (name, contact, map link)
        addHospital(hospitalPanel, "Apollo Hospitals", "044-2829 3333",
                "https://www.google.com/maps/search/?api=1&query=Apollo+Hospitals,+Greams+Road,+Chennai");

        addHospital(hospitalPanel, "Fortis Malar Hospital", "044-4200 6789",
                "https://www.google.com/maps/search/?api=1&query=Fortis+Malar+Hospital,+Adyar,+Chennai");

        addHospital(hospitalPanel, "SRM Medical College Hospital", "044-4743 2500",
                "https://www.google.com/maps/search/?api=1&query=SRM+Hospital,+Kattankulathur,+Chennai");

        addHospital(hospitalPanel, "Global Hospitals", "044-4477 7000",
                "https://www.google.com/maps/search/?api=1&query=Global+Hospitals,+Perumbakkam,+Chennai");

        addHospital(hospitalPanel, "Kauvery Hospital", "044-4000 6000",
                "https://www.google.com/maps/search/?api=1&query=Kauvery+Hospital,+Alwarpet,+Chennai");

        addHospital(hospitalPanel, "MIOT International", "044-4200 2288",
                "https://www.google.com/maps/search/?api=1&query=MIOT+International,+Manapakkam,+Chennai");

        addHospital(hospitalPanel, "MGM Healthcare", "044-4524 2407",
                "https://www.google.com/maps/search/?api=1&query=MGM+Healthcare,+Anna+Nagar,+Chennai");

        addHospital(hospitalPanel, "SIMS Hospital", "044-2000 2001",
                "https://www.google.com/maps/search/?api=1&query=SIMS+Hospital,+Vadapalani,+Chennai");

        JScrollPane scrollPane = new JScrollPane(hospitalPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Theme.BACKGROUND_LIGHT);

        JButton nearbyButton = Theme.createButton("📍 Find Current Nearby Hospitals", Theme.SUCCESS_GREEN);
        JButton backButton = Theme.createButton("⬅ Back", Theme.ACCENT_RED);

        bottomPanel.add(nearbyButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        nearbyButton.addActionListener(e ->
                openLink("https://www.google.com/maps/search/?api=1&query=hospitals+near+me"));

        backButton.addActionListener(e -> mainFrame.showScreen("HOME"));
    }

    private void addHospital(JPanel panel, String name, String phone, String mapUrl) {
        JPanel hospitalBox = new JPanel(new BorderLayout());
        hospitalBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        hospitalBox.setBackground(Color.WHITE);
        hospitalBox.setPreferredSize(new Dimension(600, 70));

        // Hospital details (name + phone)
        JLabel label = new JLabel("<html><b>" + name + "</b><br>📞 " + phone + "</html>");
        label.setFont(Theme.BODY_FONT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        hospitalBox.add(label, BorderLayout.CENTER);

        // Buttons for call and map
        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonGroup.setBackground(Color.WHITE);

        JButton callButton = Theme.createButton("📞 Call", Theme.PRIMARY_BLUE);
        JButton mapButton = Theme.createButton("🗺 View Map", Theme.SUCCESS_GREEN);

        callButton.addActionListener(e -> makePhoneCall(phone));
        mapButton.addActionListener(e -> openLink(mapUrl));

        buttonGroup.add(callButton);
        buttonGroup.add(mapButton);
        hospitalBox.add(buttonGroup, BorderLayout.EAST);

        panel.add(hospitalBox);
    }

    private void makePhoneCall(String phone) {
        try {
            // This opens dialer on supported systems
            Desktop.getDesktop().browse(new URI("tel:" + phone));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Cannot open dialer. Please call manually: " + phone,
                    "Dial Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to open location link.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
