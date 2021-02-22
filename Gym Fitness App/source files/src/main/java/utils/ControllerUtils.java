package utils;

import javax.swing.*;

public class ControllerUtils {

    public static void createSwingErrorMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void createSwingSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
