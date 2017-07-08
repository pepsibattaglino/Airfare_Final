package flight;

import javax.swing.*;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class FlightBusiness {

    private String flightOriginValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String flightOriginMessage = "Invalid identification, use only letters and numbers.";

    private String flightDestinationValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String flightDestinationMessage = "Invalid name, use only letters.";

    private String flightDepartureValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String flightDepartureMessage = "Invalid identification, use only letters and numbers.";

    private String flightDesignatedAirplaneValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String flightDesignatedAirplaneMessage = "Invalid name, use only letters.";

//    private String flightAvailableSeatsValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String flightAvailableSeatsMessage = "Invalid number of seats.";


    public boolean flightOriginChecker (String toCheck) {
        if(isValidStr(toCheck, flightOriginValidator, flightOriginMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean flightDestinationChecker (String toCheck) {
        if(isValidStr(toCheck, flightDestinationValidator, flightDestinationMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean flightDepartureChecker (String toCheck) {
        if (isValidStr(toCheck, flightDepartureValidator, flightDepartureMessage)){
            return true;
        }else{
            return false;
        }
    }

    public boolean flightDesignatedAirplaneChecker (String toCheck) {
        if (isValidStr(toCheck, flightDesignatedAirplaneValidator, flightDesignatedAirplaneMessage)){
            return true;
        }else{
            return false;
        }
    }

    public boolean flightAvailableSeatsChecker (String toCheck) {
        try {
            Integer.parseInt(toCheck);
            System.out.println(toCheck + " validation passed.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println(toCheck + " validation error.");
            JOptionPane.showMessageDialog(null,
                    flightAvailableSeatsMessage,
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
