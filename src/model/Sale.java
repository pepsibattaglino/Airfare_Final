package model;

import java.time.LocalDateTime;

/**
 * Created by Bernardo on 10/06/2017.
 */
public class Sale {
    private int saleID;
    private int saleCustomer;
    private int saleFlight;
    private java.time.LocalDateTime timeOfPurchase;

    public Sale(int saleID, int saleCustomer, int saleFlight, LocalDateTime timeOfPurchase) {
        this.saleID = saleID;
        this.saleCustomer = saleCustomer;
        this.saleFlight = saleFlight;
        this.timeOfPurchase = timeOfPurchase;
    }
}
