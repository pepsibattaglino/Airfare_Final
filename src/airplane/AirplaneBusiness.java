package airplane;

import javax.swing.*;

/**
 * Created by Bernardo on 06/07/2017.
 */
public class AirplaneBusiness {

    private String airplaneCodeValidator = "^[a-zA-Z]{3}-[0-9]{4}$";
    private String airplaneCodeMessage = "Invalid code, please use the following format: 'AAA-0000'.";

    private String airplaneModelValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String airplaneModelMessage = "Invalid model, use only letters and numbers.";

    private String airplaneQntSeatsMessage = "Invalid number of seats.";

    public boolean airplaneCodeChecker (String toCheck) {
        if(isValidStr(toCheck, airplaneCodeValidator, airplaneCodeMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean airplaneModelChecker (String toCheck) {
        if(isValidStr(toCheck, airplaneModelValidator, airplaneModelMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean airplaneQntSeatsChecker (String toCheck) {
        try {
            Integer.parseInt(toCheck);
            System.out.println(toCheck + " validation passed.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println(toCheck + " validation error.");
            JOptionPane.showMessageDialog(null,
                    airplaneQntSeatsMessage,
                    "Validation error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isValidStr (String toCheck, String rule, String errorMessage) {
        if (toCheck.matches(rule)) {
            System.out.println(toCheck + " validation passed.");
            return true;
        } else {
            System.out.println(toCheck + " validation error.");
            JOptionPane.showMessageDialog(null,
                    errorMessage,
                    "Validation error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
