package com.pluralsight;

import java.io.*;

/*
DealershipFileManager will be responsible for reading the dealership file,
parsing the data, and creating a Dealership object full of vehicles from the
file. It will also be responsible for saving a dealership and the vehicles back
into the file in the same pipe-delimited format.
 */
public class DealershipFileManager {
    private static String fileName = "dealership.csv";
    /***
     * This method reads a file "dealership.csv" . Then load the first line as the dealership,
     * starting 2nd line till the end of file each line will be loaded as a vehicle
     * and add to dealership inventory array list.
     * @return a new Dealership or if failed throws exception
     */
    public Dealership getDealership() {

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

    /**
     *overwrite the file "fileName" with the current dealership list.
     * @param dealership the currently using dealership in UserInterface
     */
    public void saveDealership(Dealership dealership){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            // Write the Dealership line (Name|Address|Phone) on the first line of the file.
            String dealershipLine = String.format("%s|%s|%s",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone());
            writer.write(dealershipLine);
            writer.newLine();

            //string from 2nd line each line will be a vehicle
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.toFileString());
                writer.newLine();
            }

            System.out.println("Dealership successfully saved to " + fileName);

        } catch (Exception e) {
            System.err.println("Error. Failed to read file name. " + e);
        }

    }
}
