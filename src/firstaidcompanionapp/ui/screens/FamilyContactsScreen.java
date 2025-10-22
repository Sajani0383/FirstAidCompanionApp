package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import firstaidcompanionapp.ui.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class FamilyContactsScreen extends JPanel {

    private JTable contactTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, numberField, relationField, addressField;
    private JButton addButton, deleteButton, saveButton, backButton;
    private final String FILE_PATH = "family_contacts.txt";

    public FamilyContactsScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Theme.BACKGROUND_LIGHT);

        JLabel title = new JLabel("👨‍👩‍👧‍👦 Family / Emergency Contacts", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY_BLUE);
        add(title, BorderLayout.NORTH);

        // Table model setup
        tableModel = new DefaultTableModel(new String[]{"Name", "Number", "Relation", "Address"}, 0);
        contactTable = new JTable(tableModel);
        contactTable.setFont(Theme.BODY_FONT);
        contactTable.setRowHeight(24);
        JScrollPane tableScroll = new JScrollPane(contactTable);
        add(tableScroll, BorderLayout.CENTER);

        // Form panel for new entry
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add / Edit Contact"));
        formPanel.setBackground(Theme.BACKGROUND_LIGHT);

        nameField = new JTextField();
        numberField = new JTextField();
        relationField = new JTextField();
        addressField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JLabel("Number:"));
        formPanel.add(new JLabel("Relation:"));
        formPanel.add(new JLabel("Address:"));
        formPanel.add(nameField);
        formPanel.add(numberField);
        formPanel.add(relationField);
        formPanel.add(addressField);

        add(formPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Theme.BACKGROUND_LIGHT);

        addButton = Theme.createButton("➕ Add Contact", Theme.PRIMARY_BLUE);
        deleteButton = Theme.createButton("🗑 Delete Contact", Theme.ACCENT_RED);
        saveButton = Theme.createButton("💾 Save Contacts", Theme.SUCCESS_GREEN);
        backButton = Theme.createButton("⬅ Back", Theme.DARK_GRAY);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load existing contacts
        loadContacts();

        // Add new contact
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String number = numberField.getText().trim();
            String relation = relationField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || number.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Number are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tableModel.addRow(new String[]{name, number, relation, address});
            nameField.setText("");
            numberField.setText("");
            relationField.setText("");
            addressField.setText("");
        });

        // Delete selected contact
        deleteButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Save contacts to file
        saveButton.addActionListener(e -> saveContacts());

        // Back to emergency screen
        backButton.addActionListener(e -> mainFrame.showScreen("EMERGENCY"));
    }

    private void loadContacts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading contacts!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Vector<?> row = tableModel.getDataVector().elementAt(i);
                writer.write(row.get(0) + "," + row.get(1) + "," + row.get(2) + "," + row.get(3));
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Contacts saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving contacts!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
