import java.util.ArrayList;
import java.util.List;

public class Client {
    private static int count = 0;
    private int id;
    private String name;
    private String surname;
    private int age;
    private String phone;
    private float cashAmount;
    private List<Vehicle> vehicleList = new ArrayList<>();

    public Client(String name, String surname, int age, String phone, float cashAmount) {
        this.id = count;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.cashAmount = cashAmount;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getCashAmount() {
        return cashAmount;
    }

    public void printCashAmount() {
        System.out.println(name + " has " + cashAmount + " amount of cash");
    }

    public void setCashAmount(float cashAmount) {
        this.cashAmount = cashAmount;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    public void printVehicleList() {
        System.out.println(name + " has following vehicles:");
        for (Vehicle vehicle : vehicleList) {
            System.out.println("-" + vehicle.getBrand());
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-15s %-5d %-15s %-15.2f%n",
                this.id,
                this.name,
                this.surname,
                this.age,
                this.phone,
                this.cashAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
