package sale;

import _application.Business;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class SaleBusiness extends Business {

    /**
     * Sale customer id validation
     */
    private String saleCustomerMessage = "Invalid customer ID, it must be a number.";

    public boolean saleCustomerChecker (String toCheck) {
        return isValidInt(toCheck, saleCustomerMessage);
    }

    /**
     * Sale flight id validation
     */
    private String saleFlightMessage = "Invalid flight ID, it must be a number.";

    public boolean saleFlightChecker (String toCheck) {
        return isValidInt(toCheck, saleFlightMessage);
    }

}