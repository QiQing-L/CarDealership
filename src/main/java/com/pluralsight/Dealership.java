package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

/* Dealership will hold information about the dealership (name, address, ...)
and maintain a list of vehicles. Since it has the list of vehicles, it will also
have the methods that search the list for matching vehicles as well as
add/remove vehicles.
 */

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model){

    }
    public List<Vehicle> getVehiclesByYear(int min, int max){

    }

    public List<Vehicle> getVehiclesByColor(String color){

    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){

    }

    public List<Vehicle> getVehiclesByType(String vehicleType){

    }

    public List<Vehicle> getAllVehicles(){

    }

    public void addVehicle(Vehicle vehicle){

    }

    public void removeVehicle(Vehicle vehicle){

    }



}
