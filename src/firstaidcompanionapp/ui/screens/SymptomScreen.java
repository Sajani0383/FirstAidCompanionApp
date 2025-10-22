package firstaidcompanionapp.ui.screens;

import firstaidcompanionapp.ui.MainFrame;
import firstaidcompanionapp.services.DatabaseService;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SymptomScreen extends JPanel {

    private final MainFrame mainFrame;
    private JTextField symptomField;
    private JTextArea infoArea;
    private JLabel imageLabel;
    private JButton checkButton, clearButton, backButton;
    private final Map<String, String> imagePaths = new HashMap<>();
    private final DatabaseService databaseService;

    public SymptomScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.databaseService = new DatabaseService();
        initializeImagePaths();
        initializeUI();
    }

    private void initializeImagePaths() {
        imagePaths.put("burn", "images/burn.jpeg");
        imagePaths.put("cut", "images/cut.jpeg");
        imagePaths.put("fever", "images/fever.jpeg");
        imagePaths.put("fracture", "images/fracture.jpeg");
        imagePaths.put("snake bite", "images/snakebite.jpeg");
        imagePaths.put("snakebite", "images/snakebite.jpeg");
        imagePaths.put("headache", "images/headache.jpeg");
        imagePaths.put("choking", "images/choking.jpeg");
        imagePaths.put("nosebleed", "images/nosebleed.jpeg");
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Enter a symptom:");
        symptomField = new JTextField(20);
        checkButton = createButton("Check", new Color(0, 51, 102));
        clearButton = createButton("Clear", new Color(160, 160, 160));
        backButton = createButton("Back", new Color(204, 51, 51));

        topPanel.add(label);
        topPanel.add(symptomField);
        topPanel.add(checkButton);
        topPanel.add(clearButton);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        imageLabel = new JLabel("Image not found", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(600, 350));
        add(imageLabel, BorderLayout.CENTER);

        infoArea = new JTextArea(6, 60);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("First Aid Instructions"));
        add(scrollPane, BorderLayout.SOUTH);

        checkButton.addActionListener(e -> showAdvice(symptomField.getText().trim()));
        clearButton.addActionListener(e -> {
            symptomField.setText("");
            infoArea.setText("");
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found");
        });
        backButton.addActionListener(e -> mainFrame.showScreen("SYMPTOM_MENU"));
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    public void showAdvice(String symptom) {
        if (symptom == null || symptom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a symptom!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // DB fetch
        String instruction = databaseService.getInstruction(symptom);
        infoArea.setText(instruction);

        // Image lookup
        String key = symptom.trim().toLowerCase();
        String imagePath = imagePaths.get(key);
        if (imagePath == null) imagePath = imagePaths.get(key.replaceAll("\\s+"," "));

        if (imagePath != null) {
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage()
                        .getScaledInstance(400, 250, Image.SCALE_SMOOTH));
                imageLabel.setIcon(icon);
                imageLabel.setText("");
            } else {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not found");
            }
        } else {
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found");
        }
    }
}


