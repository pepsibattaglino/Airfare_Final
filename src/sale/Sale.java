package sale;

import java.time.LocalDateTime;

/**
 * Created by Bernardo on 10/06/2017.
 */
public class Sale {
    private int saleID;
    private int saleCustomer;
    private int saleFlight;
    private java.time.LocalDateTime timeOfPurchase;

    public Sale() {}

    public Sale(int saleID, int saleCustomer, int saleFlight, LocalDateTime timeOfPurchase) {
        this.saleID = saleID;
        this.saleCustomer = saleCustomer;
        this.saleFlight = saleFlight;
        this.timeOfPurchase = timeOfPurchase;
    }

    public Sale(int saleCustomer, int saleFlight, LocalDateTime timeOfPurchase) {
        this.saleCustomer = saleCustomer;
        this.saleFlight = saleFlight;
        this.timeOfPurchase = timeOfPurchase;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getSaleCustomer() {
        return saleCustomer;
    }

    public void setSaleCustomer(int saleCustomer) {
        this.saleCustomer = saleCustomer;
    }

    public int getSaleFlight() {
        return saleFlight;
    }

    public void setSaleFlight(int saleFlight) {
        this.saleFlight = saleFlight;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }
}
