package firstaidcompanionapp.main;

import firstaidcompanionapp.ui.MainFrame;
import javax.swing.*;

public class FirstAidCompanionApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // ✅ Modern flat look
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}

