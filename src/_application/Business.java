package _application;

import messages.MessageController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Bernardo on 08/07/2017.
 */
public class Business{

    protected boolean isValidStr(String toCheck, String rule, String errorMessage) {
        if (toCheck.matches(rule)) {
            System.out.println(toCheck + " validation passed.");
            return true;
        } else {
            System.out.println(toCheck + " validation error.");
            MessageController.eMes("Validation error", errorMessage);
            return false;
        }
    }

    protected boolean isValidInt (String toCheck, String errorMessage) {
        try {
            Integer.parseInt(toCheck);
            System.out.println(toCheck + " validation passed.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println(toCheck + " validation error.");
            MessageController.eMes("Validation error", errorMessage);
            return false;
        }
    }

    protected boolean isValidDat (String toCheck, String errorMessage, DateTimeFormatter timeFormatter) {
        try {
            LocalDateTime.parse(toCheck, timeFormatter);
            System.out.println(toCheck + " validation passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println(">>>>>>>>>>>>>>>>>: " + toCheck + " validation error.");
            MessageController.eMes("Validation error", errorMessage);
            return false;
//        } catch (RuntimeException e) {
//            System.out.println(">>>>>>>>>>>>>>>>>: " + toCheck + " validation error.");
//            MessageController.eMes("Validation error", errorMessage);
//            return false;
        }

    }

    protected void validationError (String toCheck, String errorMessage) {
        System.out.println(toCheck + " validation passed.");
        MessageController.eMes("Validation error", errorMessage);
    }

}
