package com.pluralsight;

/*
Program will be responsible for starting the application via its main()
method and then creating the user interface and getting it started.
 */
public class Program {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();
        userInterface.display();

    // this is for temporary testing getDealership() from DealershipFileManager class:
/*
        DealershipFileManager newManager = new DealershipFileManager();

        Dealership dealership = null;
        try {
            dealership = newManager.getDealership();
            if (dealership != null) {
                System.out.println(newManager.getDealership().getAddress());
                System.out.println(newManager.getDealership().getName());
                System.out.println(newManager.getDealership().getPhone());
                System.out.println(newManager.getDealership().getAllVehicles());

            }else {
                System.out.println("Failed to load dealership.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

 */
    //above should be removed after all testing completed.






    }
}
