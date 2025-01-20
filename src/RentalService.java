import java.sql.*;
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
        loadVehiclesFromDatabase();
        clientList = new ArrayList<>();
    }

    private void loadVehiclesFromDatabase() {
        String sql = "SELECT * FROM \"Vehicles\"";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through the result set and add Vehicle objects to the list
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int mileage = rs.getInt("mileage");
                int year = rs.getInt("year");
                float price = rs.getFloat("price");

                // Create a new Vehicle object and add it to the list
                Car vehicle = new Car(id, type, brand, model, mileage, year, price);
                vehicleList.add(vehicle);
            }

            System.out.println("Vehicles loaded successfully from the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading vehicles from the database.");
        }
    }

    public void loadClientsFromDatabase() {
        String clientQuery = "SELECT * FROM \"Clients\"";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet clientResultSet = stmt.executeQuery(clientQuery)) {

            // Clear the current list to avoid duplicates
            clientList.clear();

            // Iterate through each client record
            while (clientResultSet.next()) {
                int id = clientResultSet.getInt("id");
                String name = clientResultSet.getString("name");
                String surname = clientResultSet.getString("surname");
                int age = clientResultSet.getInt("age");
                String phone = clientResultSet.getString("phone");
                float cashAmount = clientResultSet.getFloat("cash_amount");

                // Create a new Client object
                Client client = new Client(name, surname, age, phone, cashAmount);

                // Set the client ID (to match the database)
                client.setId(id);

                // Load the vehicles owned by this client
                /*List<Vehicle> vehicles = loadVehiclesForClient(id);
                client.setVehicleList(vehicles);*/

                // Add the client to the client list
                clientList.add(client);
            }

            System.out.println("Clients loaded successfully from the database.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving clients from the database.");
        }
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
        String sql = "INSERT INTO \"Vehicles\" (type, brand, model, mileage, year, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getBrand());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getMileage());
            stmt.setInt(5, vehicle.getYear());
            stmt.setDouble(6, vehicle.getPrice());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("id");
                vehicle.setId(generatedId);
                System.out.println("Client added successfully with ID: " + generatedId);
            }
            System.out.println("Vehicle added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.vehicleList.add(vehicle);
    }

    public void addClient(Client client) {
        String insertClientSQL = "INSERT INTO \"Clients\" (name, surname, age, phone, cash_amount) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertClientSQL)) {

            // Set parameters
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setInt(3, client.getAge());
            stmt.setString(4, client.getPhone());
            stmt.setFloat(5, client.getCashAmount());

            // Execute the query and get the generated ID
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("id");
                client.setId(generatedId);
                System.out.println("Client added successfully with ID: " + generatedId);
            }

            // Optionally add vehicles for this client
            /*if (!client.getVehicleList().isEmpty()) {
                addVehiclesToClient(client.getId(), client.getVehicleList());
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding client to the database.");
        }
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
            String sql = "DELETE FROM \"Vehicles\" WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsDeleted = stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            String deleteClientSQL = "DELETE FROM \"Clients\" WHERE id = ?";
            String deleteClientVehiclesSQL = "DELETE FROM \"ClientVehicles\" WHERE client_id = ?";

            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement deleteVehiclesStmt = conn.prepareStatement(deleteClientVehiclesSQL);
                 PreparedStatement deleteClientStmt = conn.prepareStatement(deleteClientSQL)) {

                // First, delete all vehicle associations for the client
                /*deleteVehiclesStmt.setInt(1, clientId);
                deleteVehiclesStmt.executeUpdate();*/

                // Then, delete the client
                deleteClientStmt.setInt(1, clientToRemove.getId());

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error removing client from the database.");
            }
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
