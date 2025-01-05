public class Car extends Vehicle {
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
