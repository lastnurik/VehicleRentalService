import java.text.DecimalFormat;

public class Vehicle {
    public static int count = 0;
    private int id;
    private String type;
    private String brand;
    private String model;
    private int mileage;
    private int year;
    private float price;
    public Vehicle(String type, String brand, String model, int mileage, int year, float price) {
        this.id = count;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("%,.2f", price);

        return String.format("%-5d %-10s %-15s %-15s %-10d %-5d %-15.2f%n",
                this.id,
                this.type,
                this.brand,
                this.model,
                this.mileage,
                this.year,
                this.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
