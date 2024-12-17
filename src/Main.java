import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RentalService rentalService = new RentalService("AutoKz", "Kabanbay Batyr 60A", "info@auto.kz");
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle bentely = new Vehicle("Sedan", "Bentley", "Flying spur", 1000, 2023, 115000000.0f);
        Vehicle bmwM5 = new Vehicle("Sedan", "BMW", "m5", 2400, 2022, 80000000.0f);
        Vehicle bmwM4 = new Vehicle("Coupe", "BMW", "m4", 5600, 2021, 65000000.0f);
        Vehicle lambo = new Vehicle("SUV", "Lamborgini", "Urus", 2000, 2021, 150000000.0f);
        Vehicle cobalt = new Vehicle("Sedan", "Chevrolet", "Cobalt", 7000, 2023, 6000000.0f);
        Vehicle mercedes = new Vehicle("Sedan", "Mercedes", "cls 63", 8000, 2023, 7000000.0f);
        vehicles.add(bentely);
        vehicles.add(bmwM5);
        vehicles.add(bmwM4);
        vehicles.add(lambo);
        vehicles.add(cobalt);
        rentalService.setVehicleList(vehicles);
        rentalService.printVehicleList();

        Client client1 = new Client("Arsen", 26, "arsen77@gmail.com", "+77057655676", 12500700.5f);
        Client client2 = new Client("Amirhan", 19, "amirhan1@gmail.com", "+77052223232", 100000447.3f);

        client1.printCashAmount();
        rentalService.sellTheVehicle(client1, lambo);
        rentalService.sellTheVehicle(client1, cobalt);
        client1.printCashAmount();
        client2.printCashAmount();
        rentalService.sellTheVehicle(client2, mercedes);
        rentalService.sellTheVehicle(client2, bmwM5);
        client2.printCashAmount();
        rentalService.sellTheVehicle(client2, bmwM4);
        rentalService.sellTheVehicle(client2, cobalt);
        client1.printVehicleList();
        client2.printVehicleList();
    }
}