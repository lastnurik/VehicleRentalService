import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private String rentalName;
    private String address;
    private String email;
    private List<Vehicle> vehicleList;
    public RentalService(String rentalName, String address, String email) {
        this.rentalName = rentalName;
        this.address = address;
        this.email = email;
        vehicleList = new ArrayList<>();
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

    public void printVehicleList() {
        System.out.println("The rental service " + rentalName + " has following vehicles:");
        for (Vehicle vehicle : vehicleList) {
            System.out.println("-" + vehicle.getBrand());
        }
        System.out.println();
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicleList.remove(vehicle);
    }

    public void sellTheVehicle(Client client, Vehicle vehicle) {
        if (!this.vehicleList.contains(vehicle)) {
            System.out.println("There is no such vehicle as " + vehicle.getBrand() + " " + vehicle.getModel() + " at " + rentalName +  "!");
            return;
        }
        if (client.getCashAmount() >= vehicle.getPrice()) {
            client.setCashAmount(client.getCashAmount() - vehicle.getPrice());
            this.vehicleList.remove(vehicle);
            client.addVehicle(vehicle);
            System.out.println("Sold the " +vehicle.getBrand() + " " + vehicle.getModel() + " to " + client.getName() + " at " + rentalName +  "!");
        }
        else {
            System.out.println("Not enough cash! " + client.getName() + " has only " + client.getCashAmount() + ", and the vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " costs " + vehicle.getPrice());
        }
    }
}
