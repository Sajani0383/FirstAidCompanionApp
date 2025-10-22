package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {

    private final MainFrame mainFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔐 First Aid Companion Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        add(loginButton, gbc);

        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both username and password!");
            return;
        }

        if (username.equals("admin") && password.equals("1234")) {
            mainFrame.setLoggedInUser(username); // ✅ Fixed method
            mainFrame.showScreen("HOME");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }
}
