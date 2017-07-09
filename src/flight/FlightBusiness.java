package flight;

import _application.Business;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class FlightBusiness extends Business {

    /**
     * Flight origin validation
     */
    private String flightOriginValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String flightOriginMessage = "Invalid origin, use only letters.";

    public boolean flightOriginChecker (String toCheck) {
        return isValidStr(toCheck, flightOriginValidator, flightOriginMessage);
    }

    /**
     * Flight destination validation
     */
    private String flightDestinationValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String flightDestinationMessage1 = "Invalid destination, use only letters.";
    private String flightDestinationMessage2 = "The destination must be different from the origin.";

    public boolean flightDestinationChecker (String toCheck, String origin) {
        if (toCheck.equalsIgnoreCase(origin)) {
            validationError(toCheck, flightDestinationMessage2);
            return false;
        } else {
            return isValidStr(toCheck, flightDestinationValidator, flightDestinationMessage1);
        }
    }

    /**
     * Flight departure validation
     */
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String flightDepartureMessage = "Invalid departure, please use the following format: 'YYYY-MM-DD hh:mm'.";

    public boolean flightDepartureChecker (String toCheck) {
        return isValidDat(toCheck, flightDepartureMessage, timeFormatter);
    }
    public LocalDateTime timeParser(String toParse) {
        return LocalDateTime.parse(toParse, timeFormatter);
    }

    /**
     * Flight designated airplane validation
     */
    private String flightDesignatedAirplaneMessage = "Invalid airplane ID, it must be a number.";

    public boolean flightDesignatedAirplaneChecker (String toCheck) {
        return isValidInt(toCheck, flightDesignatedAirplaneMessage);
    }

}