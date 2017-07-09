package messages;

import javax.swing.*;

/**
 * Created by Bernardo on 08/07/2017.
 */
public class MessageController{

    /**
     * Error MessageController
     * Generates error message pop-ups.
     */
    public static void eMes(String title, String message) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}
