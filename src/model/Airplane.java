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

    public int getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(int airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQntSeats() {
        return qntSeats;
    }

    public void setQntSeats(int qntSeats) {
        this.qntSeats = qntSeats;
    }
}
