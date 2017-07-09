package sale;

import javax.swing.*;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class SaleBusiness {

//    private String saleCustomerValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String saleCustomerMessage = "Invalid customer, use only numbers.";

//    private String saleFlightValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String saleFlightMessage = "Invalid flight, use only numbers.";

    public boolean saleCustomerChecker (String toCheck) {
       /* if(isValidStr(toCheck, saleCustomerValidator, saleCustomerMessage)){
            return true;
        } else {
            return false;
        }*/
        try {

            Integer.parseInt(toCheck);
            System.out.println(toCheck + " validation passed.");

            return true;

        } catch (NumberFormatException e) {

            System.out.println(toCheck + " validation error.");
            JOptionPane.showMessageDialog(null,
                    saleCustomerMessage,
                    "Validation error",
                    JOptionPane.ERROR_MESSAGE);

            return false;

        }

    }

    public boolean saleFlightChecker (String toCheck) {
        /*if(isValidStr(toCheck, saleFlightValidator, saleFlightMessage)){
            return true;
        } else {
            return false;
        }*/

        try {

            Integer.parseInt(toCheck);
            System.out.println(toCheck + " validation passed.");
            return true;

        } catch (NumberFormatException e) {

            System.out.println(toCheck + " validation error.");
            JOptionPane.showMessageDialog(null,
                    saleFlightMessage,
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