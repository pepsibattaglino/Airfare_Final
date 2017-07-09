package airplane;

import _application.Business;

/**
 * Created by Bernardo on 06/07/2017.
 */
public class AirplaneBusiness extends Business {

    /**
     * Airplane code validation
     */
    private String airplaneCodeValidator = "^[a-zA-Z]{3}-[0-9]{4}$";
    private String airplaneCodeMessage = "Invalid code, please use the following format: 'AAA-0000'.";

    public boolean airplaneCodeChecker (String toCheck) {
        return isValidStr(toCheck, airplaneCodeValidator, airplaneCodeMessage);
    }

    /**
     * Airplane model validation
     */
    private String airplaneModelValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String airplaneModelMessage = "Invalid model, use only letters and numbers.";

    public boolean airplaneModelChecker (String toCheck) {
        return isValidStr(toCheck, airplaneModelValidator, airplaneModelMessage);
    }

    /**
     * Airplane quantity
     */
    private String airplaneQntSeatsMessage = "Invalid number of seats.";

    public boolean airplaneQntSeatsChecker (String toCheck) {
        return isValidInt(toCheck, airplaneQntSeatsMessage);
    }

}