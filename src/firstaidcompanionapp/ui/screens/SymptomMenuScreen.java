package firstaidcompanionapp.ui.screens;

import javax.swing.*;
import java.awt.*;
import firstaidcompanionapp.ui.MainFrame;

public class SymptomMenuScreen extends JPanel {

    private final MainFrame mainFrame;

    public SymptomMenuScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("🩺 Symptom Checker Menu");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JButton checkButton = createButton("Check Symptoms", new Color(0, 102, 204));
        JButton backButton = createButton("Back to Home", new Color(204, 51, 51));

        checkButton.addActionListener(e -> mainFrame.showScreen("SYMPTOMS"));
        backButton.addActionListener(e -> mainFrame.showScreen("HOME"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);
        gbc.gridy = 1;
        add(checkButton, gbc);
        gbc.gridy = 2;
        add(backButton, gbc);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        return btn;
    }
}
