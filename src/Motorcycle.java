public class Motorcycle extends Vehicle {
    public Motorcycle(String type, String brand, String model, int mileage, int year, float price) {
        this.id = count;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        count++;
    }

    public Motorcycle(String brand, String model, int mileage, int year, float price) {
        this.id = count;
        this.type = "Standard";
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        count++;
    }
}
