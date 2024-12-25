import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        RentalService rentalService = new RentalService("AutoKz", "Kabanbay Batyr 60A", "info@auto.kz");
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Sedan", "Bentley", "Flying Spur", 1000, 2023, 115000000.0f));
        vehicles.add(new Vehicle("Sedan", "BMW", "M5", 2400, 2022, 80000000.0f));
        vehicles.add(new Vehicle("SUV", "Lamborghini", "Urus", 2000, 2021, 150000000.0f));
        vehicles.add(new Vehicle("Sedan", "Chevrolet", "Cobalt", 7000, 2023, 6000000.0f));
        rentalService.addVehicle(vehicles);

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Arsen", "Ilyasov", 26, "+77057655676", 12500700.5f));
        clients.add(new Client("Amirhan", "Kairat", 19, "+77052223232", 100000447.3f));
        rentalService.addClient(clients);

        while (true) {
            System.out.println("\n==============================");
            System.out.println(" Welcome to " + rentalService.getRentalName() + " Admin Panel");
            System.out.println("==============================");
            System.out.println("Please choose an option:");
            System.out.println("1. Vehicle menu");
            System.out.println("2. Client base");
            System.out.println("3. Sells menu");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Vehicle menu ---");
                    VehicleMenu(rentalService);
                    break;

                case 2:
                    System.out.println("\n--- Client base ---");
                    CLientMenu(rentalService);
                    break;

                case 3:
                    System.out.println("\n--- Sells menu ---");
                    SellsMenu(rentalService);
                    break;

                case 4:
                    System.out.println("\nThank you for using our system!");
                    return;

            }
        }
    }


    public static void VehicleMenu(RentalService rentalService) {
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. View all Vehicles");
            System.out.println("2. Add a new Vehicle");
            System.out.println("3. Remove a Vehicle");
            System.out.println("4. Back");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Vehicle List ---");
                    System.out.println("Please choose a sorting option:");
                    System.out.println("1. Sort by Id");
                    System.out.println("2. Sort by Brand");
                    System.out.println("3. Sort by Price");
                    int sortOption = sc.nextInt();
                    rentalService.printVehicleList(sortOption);
                    break;

                case 2:
                    System.out.println("\n--- Add a new Vehicle ---");
                    System.out.print("Enter Vehicle Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Vehicle Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Enter Vehicle Model: ");
                    String model = sc.nextLine();
                    System.out.print("Enter Vehicle Mileage: ");
                    int mileage = sc.nextInt();
                    System.out.print("Enter Vehicle Year: ");
                    int year = sc.nextInt();
                    System.out.print("Enter Vehicle Price: ");
                    float price = sc.nextFloat();
                    sc.nextLine();

                    Vehicle newVehicle = new Vehicle(type, brand, model, mileage, year, price);
                    rentalService.addVehicle(newVehicle);
                    System.out.println("\n[+] Vehicle Added Successfully!");
                    break;

                case 3:
                    System.out.println("\n--- Remove a Vehicle ---");
                    rentalService.printVehicleList(1);
                    System.out.print("Enter the id of the vehicle to remove: ");
                    int removeId = sc.nextInt();

                    rentalService.removeVehicle(removeId);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("\n[!] Invalid Option! Please try again.");
            }
        }
    }

    public static void CLientMenu(RentalService rentalService) {
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. View all Clients");
            System.out.println("2. Add a new Client");
            System.out.println("3. Remove a Client");
            System.out.println("4. Back");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Clients List ---");
                    rentalService.printClientList();
                    break;

                case 2:
                    System.out.println("\n--- Add a New Client ---");
                    System.out.print("Enter Client's Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Client's Surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Enter Client's Age: ");
                    int age = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Enter Client's Phone Number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Client's Cash Amount: ");
                    float cashAmount = sc.nextFloat();
                    sc.nextLine();

                    Client newClient = new Client(name, surname, age, phone, cashAmount);
                    rentalService.addClient(newClient);
                    System.out.println("\n[+] Client Added Successfully!");

                    break;

                case 3:
                    System.out.println("\n--- Remove a Vehicle ---");
                    rentalService.printClientList();
                    System.out.print("Enter the id of the client to remove: ");
                    int removeId = sc.nextInt();

                    rentalService.removeClient(removeId);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("\n[!] Invalid Option! Please try again.");
            }
        }
    }

    public static void SellsMenu(RentalService rentalService) {
        System.out.println("Please choose a Client:");

        System.out.print("Enter the ID of the Client: ");
        int clientId = sc.nextInt();
        sc.nextLine();

        Client client = null;
        for (Client c : rentalService.getClientList()) {
            if (c.getId() == clientId) {
                client = c;
                break;
            }
        }

        if (client == null) {
            System.out.println("[!] Client not found!");
            return;
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(client.toString());
        System.out.println("----------------------------------------------------------------------------");

        System.out.println("Select a Vehicle to purchase:");

        System.out.print("Enter the ID of the Vehicle: ");
        int vehicleId = sc.nextInt();
        sc.nextLine();

        Vehicle vehicle = null;
        for (Vehicle v : rentalService.getVehicleList()) {
            if (v.getId() == vehicleId) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("[!] Vehicle not found!");
            return;
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(vehicle.toString());
        System.out.println("----------------------------------------------------------------------------");

        rentalService.sellTheVehicle(client, vehicle);
    }
}


