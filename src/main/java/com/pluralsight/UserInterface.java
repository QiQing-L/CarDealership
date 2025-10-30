package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
UserInterface will be responsible for all output to the screen, reading of
user input, and "dispatching" of the commands to the Dealership as
needed.
 */
public class UserInterface {
    private Dealership dealership;

    /* ------------------------------------------------------------------
           text colors and share data
        ------------------------------------------------------------------ */
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String RED2 = "\u001B[91m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String YELLOW2 = "\u001B[93m";
    private static final String BLUE = "\u001B[34m";
    private static final String BLUE2 = "\u001B[94m";
    private static final String BOLD = "\u001B[1m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String WHITE2 = "\u001B[97m";
    private static final String CYAN = "\u001B[36m";
    private static final String CYAN2 = "\u001B[96m";

    private static final String listHeaderLine = String.format(BOLD + CYAN +
                    "%-5s | %-4s | %-15s | %-10s | %-12s | %-10s | %-8s | %s",
            "VIN", "Year", "Make", "Model","Vehicle Type", "Color","Mileage","Price" + RESET);

    //constructor
    public UserInterface() {

    }


    //methods:
    private void init(){
        DealershipFileManager newFileManager = new DealershipFileManager();

        try {
            Dealership dealership = newFileManager.getDealership();
            if (dealership == null) {
                System.out.println("Failed to load dealership.");
            }else {
                this.dealership = dealership;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void display() {
        init();

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 99) {

            // display menu
            System.out.println(YELLOW2 +  BOLD + "\nWelcome to the Car Dealership!\n" + RESET);
            System.out.println(BLUE + "1 - Find vehicles within a price range" + RESET);
            System.out.println(BLUE + "2 - Find vehicles by make / model" + RESET);
            System.out.println(BLUE + "3 - Find vehicles by year range " + RESET);
            System.out.println(BLUE + "4 - Find vehicles by color " + RESET);
            System.out.println(BLUE + "5 - Find vehicles by mileage range" + RESET);
            System.out.println(BLUE + "6 - Find vehicles by type (car, truck, SUV, van)" + RESET);
            System.out.println(BLUE + "7 - List ALL vehicles" + RESET);
            System.out.println(BLUE + "8 - Add a vehicle " + RESET);
            System.out.println(BLUE + "9 - Remove a vehicle " + RESET);
            System.out.println(RED2 + "99 - Quit" + RESET);
            System.out.println(CYAN2 + "Your choice: " + RESET );

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processGetByPriceRequest(scanner);
                case 2 -> processGetByMakeModelRequest(scanner);
                case 3 -> processGetByYearRequest(scanner);
                case 4 -> processGetByColorRequest(scanner);
                case 5 -> processGetByMileageRequest(scanner);
                case 6 -> processGetByVehicleTypeRequest(scanner);
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest(scanner);
                case 9 -> processRemoveVehicleRequest(scanner);
                case 99 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    private void displayVehicles(List<Vehicle> inventory){
        System.out.println(BOLD + "\nVehicles: \n" + RESET);
        System.out.println(listHeaderLine);
        try {
            for (Vehicle vehicle : inventory) {
                System.out.println(vehicle);
            }
        } catch (Exception e) {
            System.out.println(RED + "Error displaying list. " + e + RESET);
        }
    }

    public void processGetByPriceRequest(Scanner input){
        double minPrice = 0.0;
        double maxPrice = 0.0;
        boolean done=false;

        while (!done) {
            try{
                System.out.print("Enter minimum price: ");
                minPrice = input.nextDouble();
                System.out.print("Enter maximum price: ");
                maxPrice = input.nextDouble();

                done = true;

            } catch (Exception e){
                System.out.println(RED2 + "Invalid input. Please enter a number." + RESET);
                input.nextLine();
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

        }
    }

    public void processGetByMakeModelRequest(Scanner input){
        boolean done=false;
        while (!done) {

            System.out.print("Enter Make: ");
            String make = input.nextLine().trim();
            System.out.print("Enter Model: ");
            String model = input.nextLine().trim();

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (make.equalsIgnoreCase(vehicle.getMake()) && model.equalsIgnoreCase(vehicle.getModel())) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            done = true;
        }

    }

    public void processGetByYearRequest(Scanner input){
        int year = 0;
        boolean done=false;
        while (!done) {

            try{
                System.out.print("Enter year: ");
                year = input.nextInt();

                done = true;
            } catch (Exception e){
                System.out.println(RED2 + "Invalid input. Please enter a number." + RESET);
                input.nextLine();
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (year == vehicle.getYear()) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

        }

    }

    public void processGetByColorRequest(Scanner input){
        boolean done=false;
        while (!done) {
            System.out.print("Enter color: ");
            String color = input.nextLine().trim();

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (color.equalsIgnoreCase(vehicle.getColor())) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            done = true;
        }

    }

    public void processGetByMileageRequest(Scanner input){
        double minMileage = 0;
        double maxMileage = 0;
        boolean done=false;

        while (!done) {

            try{
                System.out.print("Enter minimum mileage: ");
                minMileage = input.nextDouble();
                System.out.print("Enter maximum mileage: ");
                maxMileage = input.nextDouble();
                done = true;

            } catch (Exception e){
                System.out.println(RED2 + "Invalid input. Please enter a number." + RESET);
                input.nextLine();
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

        }

    }

    public void processGetByVehicleTypeRequest(Scanner input){
        boolean done=false;
        while (!done) {
            System.out.print("Enter Vehicle Type: ");
            String vehicleType = input.nextLine().trim();

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicleType.equalsIgnoreCase(vehicle.getVehicleType())) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            done = true;
        }

    }

    public void processGetAllVehiclesRequest(){
        List<Vehicle> inventory = this.dealership.getAllVehicles();
        displayVehicles(inventory);
    }

    public void processAddVehicleRequest(Scanner input){

        System.out.println("Enter the following vehicle information to add a vehicle to inventory:");
        System.out.print("Enter VIN: ");
        int vin = input.nextInt();
        input.nextLine();
        System.out.print("Enter Year: ");
        int year = input.nextInt();
        input.nextLine();
        System.out.print("Enter Make: ");
        String make = input.nextLine();
        System.out.print("Enter model: ");
        String model = input.nextLine();
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = input.nextLine();
        System.out.print("Enter Color: ");
        String color = input.nextLine();
        System.out.print("Enter Odometer: ");
        int odometer = input.nextInt();
        System.out.print("Enter product price: ");
        double price = input.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year,make,model,vehicleType,color, odometer,price);
        dealership.addVehicle(vehicle);

        System.out.println("Successfully added: " + vehicle);

        //rewrite and save dealership to file.
        new DealershipFileManager().saveDealership(dealership);


    }

    public void processRemoveVehicleRequest(Scanner input){

        System.out.println("Enter the following vehicle information to add a vehicle to inventory:");
        System.out.print("Enter VIN: ");
        int vin = input.nextInt();
        input.nextLine();
        System.out.print("Enter Year: ");
        int year = input.nextInt();
        input.nextLine();
        System.out.print("Enter Make: ");
        String make = input.nextLine();
        System.out.print("Enter model: ");
        String model = input.nextLine();
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = input.nextLine();
        System.out.print("Enter Color: ");
        String color = input.nextLine();
        System.out.print("Enter Odometer: ");
        int odometer = input.nextInt();
        System.out.print("Enter product price: ");
        double price = input.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year,make,model,vehicleType,color, odometer,price);
        dealership.removeVehicle(vehicle);

        System.out.println("Successfully removed: " + vehicle);

        //rewrite and save dealership to file.
        new DealershipFileManager().saveDealership(dealership);

    }


}
