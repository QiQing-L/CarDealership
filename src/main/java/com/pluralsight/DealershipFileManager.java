package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/*
DealershipFileManager will be responsible for reading the dealership file,
parsing the data, and creating a Dealership object full of vehicles from the
file. It will also be responsible for saving a dealership and the vehicles back
into the file in the same pipe-delimited format.
 */
public class DealershipFileManager {

    /***
     * This method reads a file "dealership.csv" . Then load the first line as the dealership,
     * starting 2nd line till the end of file each line will be loaded as a vehicle
     * and add to dealership inventory array list.
     * @return a new Dealership or if failed throws exception
     * @throws Exception
     */
    public Dealership getDealership() throws Exception{
        String fileName = "dealership.csv";
        Dealership dealership = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String firstLine;
            firstLine = reader.readLine();

            if (firstLine != null) {
                String[] newDealershipParts = firstLine.split("\\|");
                if (newDealershipParts.length >= 3) {
                    String name = newDealershipParts[0];
                    String address = newDealershipParts[1];
                    String phoneNumber = newDealershipParts[2];
                    dealership = new Dealership(name, address, phoneNumber);

                    String objectLine;
                    while ((objectLine = reader.readLine()) != null) {
                        String[] newVehicleParts = objectLine.split("\\|");
                        int vin = Integer.parseInt(newVehicleParts[0]);
                        int year = Integer.parseInt(newVehicleParts[1]);
                        String make = newVehicleParts[2];
                        String model = newVehicleParts[3];
                        String type = newVehicleParts[4];
                        String color = newVehicleParts[5];
                        int odometer = Integer.parseInt(newVehicleParts[6]);
                        double price = Double.parseDouble(newVehicleParts[7]);

                        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));

                    }
                }else {
                    System.err.println("Error: First line of file is missing data fields.");
                }
            } else {
                System.out.println("The file is empty.");
            }

            reader.close();

        }catch (Exception exception){
            System.err.println("Error. Failed to read file. " + exception);

        }

        return dealership;

    }
    public void saveDealership(Dealership dealership){

    }
}
