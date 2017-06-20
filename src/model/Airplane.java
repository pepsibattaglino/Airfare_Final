package model;

/**
 * Created by Bernardo on 09/06/2017.
 */
public class Airplane {
    private int airplaneID;
    private String code;
    private String model;
    private int qntSeats;

    public Airplane(String code, String model, int qntSeats) {
        this.code = code;
        this.model = model;
        this.qntSeats = qntSeats;
    }

    public Airplane(int airplaneID, String code, String model, int qntSeats) {
        this.airplaneID = airplaneID;
        this.code = code;
        this.model = model;
        this.qntSeats = qntSeats;
    }
}
