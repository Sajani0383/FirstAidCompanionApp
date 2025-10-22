package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import firstaidcompanionapp.ui.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserMedicalRecordScreen extends JPanel {

    private JTextField nameField, contactField, addressField, bloodGroupField;
    private JTextArea illnessArea;
    private JButton saveButton, backButton;
    private final String FILE_PATH = "medical_record.txt";

    public UserMedicalRecordScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Theme.BACKGROUND_LIGHT);

        JLabel title = new JLabel("🩺 My Medical Record", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY_BLUE);
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Theme.BACKGROUND_LIGHT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and fields
        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(20);
        addField(formPanel, gbc, nameLabel, nameField, 0);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactField = new JTextField(20);
        addField(formPanel, gbc, contactLabel, contactField, 1);

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addField(formPanel, gbc, addressLabel, addressField, 2);

        JLabel bloodLabel = new JLabel("Blood Group:");
        bloodGroupField = new JTextField(20);
        addField(formPanel, gbc, bloodLabel, bloodGroupField, 3);

        JLabel illnessLabel = new JLabel("Medical Illness / History:");
        illnessArea = new JTextArea(5, 20);
        illnessArea.setLineWrap(true);
        illnessArea.setWrapStyleWord(true);
        JScrollPane illnessScroll = new JScrollPane(illnessArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(illnessLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(illnessScroll, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Theme.BACKGROUND_LIGHT);

        saveButton = Theme.createButton("💾 Save Record", Theme.SUCCESS_GREEN);
        backButton = Theme.createButton("⬅ Back", Theme.ACCENT_RED);

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load data from file
        loadData();

        // Save action
        saveButton.addActionListener(e -> saveData());

        // Back action
        backButton.addActionListener(e -> mainFrame.showScreen("EMERGENCY"));
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField field, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(field, gbc);
    }

    private void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("Name: " + nameField.getText() + "\n");
            bw.write("Contact: " + contactField.getText() + "\n");
            bw.write("Address: " + addressField.getText() + "\n");
            bw.write("Blood Group: " + bloodGroupField.getText() + "\n");
            bw.write("Medical History: " + illnessArea.getText() + "\n");
            JOptionPane.showMessageDialog(this, "Record saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving record!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            StringBuilder data = new StringBuilder();
            while ((line = br.readLine()) != null) {
                data.append(line).append("\n");
            }
            String[] lines = data.toString().split("\n");
            if (lines.length >= 5) {
                nameField.setText(lines[0].replace("Name: ", ""));
                contactField.setText(lines[1].replace("Contact: ", ""));
                addressField.setText(lines[2].replace("Address: ", ""));
                bloodGroupField.setText(lines[3].replace("Blood Group: ", ""));
                illnessArea.setText(lines[4].replace("Medical History: ", ""));
            }
        } catch (IOException ignored) {
        }
    }
}
