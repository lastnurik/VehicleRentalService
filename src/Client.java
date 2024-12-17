import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private int age;
    private String email;
    private String phone;
    private float cashAmount;
    private List<Vehicle> vehicleList = new ArrayList<>();

    public Client(String name, int age, String email, String phone, float cashAmount) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.cashAmount = cashAmount;
    }

    public String getName() {
        return name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void printClientInfo() {
        System.out.println(name + "'s phone number is " + phone + ", email is " + email + " and he has " + cashAmount + " amount of cash");
    }
}
