package customer;


import _application.Business;

/**
 * Created by G.Battaglino on 09/07/2017.
 */
public class CustomerTableBusiness extends Business {
    /**
     * Customer identification validation
     */
    private String customerIdentificationValidator = "^[\\p{IsLatin}\\p{Digit}]{2,}([ ]{1}[\\p{IsLatin}\\p{Digit}]++)*$";
    private String customerIdentificationMessage = "Invalid identification, use only letters and numbers.";

    public boolean customerIdentificationChecker (String toCheck) {
        return isValidStr(toCheck, customerIdentificationValidator, customerIdentificationMessage);
    }

    /**
     * Customer name validation
     */
    private String customerNameValidator = "^[\\p{IsLatin}]{2,}([ ]{1}[\\p{IsLatin}]++)*$";
    private String customerNameMessage = "Invalid name, use only letters.";

    public boolean customerNameChecker (String toCheck) {
        return isValidStr(toCheck, customerNameValidator, customerNameMessage);
    }

    /**
     * Customer phone validation
     */
    private String customerPhoneValidator = "^[0-9]++([-]{1}[0-9]++)*$";
    private String customerPhoneMessage = "Invalid phone, use only letters and numbers.";

    public boolean customerPhoneChecker (String toCheck) {
        return isValidStr(toCheck, customerPhoneValidator, customerPhoneMessage);
    }
}
