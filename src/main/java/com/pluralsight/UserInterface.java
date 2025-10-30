package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
UserInterface will be responsible for all output to the screen, reading of
user input, and "dispatching" of the commands to the Dealership as
needed. (ex: when the user selects "List all Vehicles", UserInterface would
call the appropriate Dealership method and then display the vehicles it
returns.)
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
        DealershipFileManager newManager = new DealershipFileManager();

        try {
            Dealership dealership = newManager.getDealership();
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
              /* display this:

        1 - Find vehicles within a price range
        2 - Find vehicles by make / model
        3 - Find vehicles by year range
        4 - Find vehicles by color
        5 - Find vehicles by mileage range
        6 - Find vehicles by type (car, truck, SUV, van)
        7 - List ALL vehicles
        8 - Add a vehicle
        9 - Remove a vehicle
        99 - Quit

         */
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
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
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

    public void processGetByPriceRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter minimum price: ");
            double minPrice = input.nextDouble();
            System.out.print("Enter maximum price: ");
            double maxPrice = input.nextDouble();

            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();                 // discard bad input
                continue;
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            input.close();
            done = true;
        }
    }

    public void processGetByMakeModelRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
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

            input.close();
            done = true;
        }

    }

    public void processGetByYearRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter year: ");
            int year = input.nextInt();

            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();                 // discard bad input
                continue;
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (year == vehicle.getYear()) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            input.close();
            done = true;
        }

    }

    public void processGetByColorRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
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

            input.close();
            done = true;
        }

    }

    public void processGetByMileageRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter minimum mileage: ");
            double minMileage = input.nextDouble();
            System.out.print("Enter maximum mileage: ");
            double maxMileage = input.nextDouble();

            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();                 // discard bad input
                continue;
            }

            List<Vehicle> inventory = this.dealership.getAllVehicles();
            List<Vehicle> filteredInventory = new ArrayList<>();

            for (Vehicle vehicle : inventory) {
                if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                    filteredInventory.add(vehicle);
                }
            }
            displayVehicles(filteredInventory);

            input.close();
            done = true;
        }

    }

    public void processGetByVehicleTypeRequest(){
        boolean done=false;
        while (!done) {
            Scanner input = new Scanner(System.in);
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

            input.close();
            done = true;
        }

    }

    public void processGetAllVehiclesRequest(){
        List<Vehicle> inventory = this.dealership.getAllVehicles();
        displayVehicles(inventory);
    }

    public void processAddVehicleRequest(){

        Scanner input = new Scanner(System.in);

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

        input.close();

    }

    public void processRemoveVehicleRequest(){
        Scanner input = new Scanner(System.in);

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
        dealership.removeVehicle(vehicle);;

        input.close();


    }





}
