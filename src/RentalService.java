import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class RentalService {
    private String rentalName;
    private String address;
    private String email;
    private List<Vehicle> vehicleList;
    private List<Client> clientList;

    public RentalService(String rentalName, String address, String email) {
        this.rentalName = rentalName;
        this.address = address;
        this.email = email;
        vehicleList = new ArrayList<>();
        clientList = new ArrayList<>();
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void printVehicleList(int sortOption) {
        System.out.println("The rental service " + rentalName + " has following vehicles:");
        System.out.printf("%-5s %-10s %-15s %-15s %-10s %-5s %-15s%n", "ID", "Type", "Brand", "Model", "Mileage", "Year", "Price");
        System.out.println("----------------------------------------------------------------------------");
        if (sortOption == 1) {
            vehicleList.sort(Comparator.comparingInt(Vehicle::getId));
        }
        else if (sortOption == 2) {
            vehicleList.sort(Comparator.comparing(Vehicle::getBrand));
        }
        else if (sortOption == 3) {
            vehicleList.sort(Comparator.comparingDouble(Vehicle::getPrice));
        }
        else {
            vehicleList.sort(Comparator.comparingInt(Vehicle::getId));
        }

        for (Vehicle vehicle : vehicleList) {
            System.out.printf(vehicle.toString());
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
    }

    public void printClientList() {
        System.out.println("The rental service has the following clients:");
        System.out.printf("%-5s %-15s %-15s %-5s %-15s %-15s%n", "ID", "Name", "Surname", "Age", "Phone", "Cash Amount");
        System.out.println("------------------------------------------------------------------------------");
        for (Client client : clientList) {
            System.out.printf(client.toString());
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
    }

    public void addVehicle(List<Vehicle> vehicleList) {
        this.vehicleList.addAll(vehicleList);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    public void addClient(Client client) {
        this.clientList.add(client);
    }

    public void addClient(List<Client> clientList) {
        this.clientList.addAll(clientList);
    }

    public void removeVehicle(int id) {
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == id) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            vehicleList.remove(vehicleToRemove);
            System.out.println("\n[-] Vehicle Removed Successfully!");
        } else {
            System.out.println("\n[!] Vehicle Not Found!");
        }
    }

    public void removeClient(int id) {
        Client clientToRemove = null;
        for (Client client : clientList) {
            if (client.getId() == id) {
                clientToRemove = client;
                break;
            }
        }

        if (clientToRemove != null) {
            clientList.remove(clientToRemove);
            System.out.println("\n[-] Client Removed Successfully!");
        } else {
            System.out.println("\n[!] Client Not Found!");
        }
    }

    public void sellTheVehicle(Client client, Vehicle vehicle) {
        if (client.getCashAmount() >= vehicle.getPrice()) {
            client.setCashAmount(client.getCashAmount() - vehicle.getPrice());
            this.vehicleList.remove(vehicle);
            client.addVehicle(vehicle);
            System.out.println("[*] Sold the " +vehicle.getBrand() + " " + vehicle.getModel() + " to " + client.getName() + " at " + rentalName +  "!");
        }
        else {
            System.out.println("[!] Not enough cash! " + client.getName() + " has only " + client.getCashAmount() + ", and the vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " costs " + vehicle.getPrice());
        }
    }
}
