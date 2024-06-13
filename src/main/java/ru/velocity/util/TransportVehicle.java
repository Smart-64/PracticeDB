package ru.velocity.util;

public class TransportVehicle {

    private int id_TransportVehicle;
    private int velocity;

    public TransportVehicle() {

    }

    public TransportVehicle(int id_TransportVehicle, int velocity) {
        this.id_TransportVehicle = id_TransportVehicle;
        this.velocity = velocity;
    }


    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getId_TransportVehicle() {
        return id_TransportVehicle;
    }

    public void setId_TransportVehicle(int id_TransportVehicle) {
        this.id_TransportVehicle = id_TransportVehicle;
    }
}
