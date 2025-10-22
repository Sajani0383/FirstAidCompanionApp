package firstaidcompanionapp.ui;

import javax.swing.*;
import java.awt.*;

public class Theme {

    // ===== 🎨 COLOR PALETTE =====
    public static final Color PRIMARY_BLUE = new Color(0, 70, 140);
    public static final Color PRIMARY_RED = new Color(200, 0, 0);
    public static final Color ACCENT_RED = new Color(220, 50, 47);
    public static final Color SUCCESS_GREEN = new Color(46, 204, 113);
    public static final Color DARK_GRAY = new Color(60, 60, 60);
    public static final Color SECONDARY_GRAY = new Color(230, 230, 230);
    public static final Color BACKGROUND_LIGHT = new Color(245, 245, 255);
    public static final Color BACKGROUND_DARK = new Color(30, 30, 30);
    public static final Color TEXT_DARK = new Color(33, 33, 33);
    public static final Color TEXT_LIGHT = Color.WHITE;

    // ===== 🖋️ FONTS =====
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 15);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 15);

    // ===== 🧩 BUTTON CREATOR =====
    public static JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);

        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(color.darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(color);
            }
        });
        return button;
    }

    // ===== 🧱 PANEL BACKGROUND CREATOR =====
    public static JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }

    // ===== 🧭 LABEL CREATOR =====
    public static JLabel createTitleLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        return label;
    }
}
