package com.pluralsight;

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
           text colors
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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void display() {

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
                case 1 -> ;
                case 2 -> ;
                case 99 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();




    }

    public void processGetByPriceRequest(){

    }

    public void processGetByMakeModelRequest(){

    }

    public void processGetByYearRequest(){

    }

    public void processGetByColorRequest(){

    }

    public void processGetByMileageRequest(){

    }

    public void processGetByVehicleTypeRequest(){

    }

    public void processGetAllVehiclesRequest(){

    }

    public void processAddVehicleRequest(){

    }

    public void processRemoveVehicleRequest(){

    }





}
