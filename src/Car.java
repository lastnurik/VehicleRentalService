public class Car extends Vehicle {
    public Car(int id, String type, String brand, String model, int mileage, int year, float price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        count++;
    }

    public Car(String type, String brand, String model, int mileage, int year, float price) {
        this.id = count;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        count++;
    }
}
