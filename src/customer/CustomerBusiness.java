package customer;

import javax.swing.*;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class CustomerBusiness {

    private String customerIdentificationValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String customerIdentificationMessage = "Invalid identification, use only letters and numbers.";

    private String customerNameValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String customerNameMessage = "Invalid name, use only letters.";

    private String customerPhoneValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String customerPhoneMessage = "Invalid identification, use only letters and numbers.";

    public boolean customerIdentificationChecker (String toCheck) {
        if(isValidStr(toCheck, customerIdentificationValidator, customerIdentificationMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean customerNameChecker (String toCheck) {
        if(isValidStr(toCheck, customerNameValidator, customerNameMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean customerPhoneChecker (String toCheck) {
       if (isValidStr(toCheck, customerPhoneValidator, customerPhoneMessage)){
           return true;
       }else{
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