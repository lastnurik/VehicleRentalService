import java.text.DecimalFormat;

public class Vehicle {
    protected static int count = 0;
    protected int id;
    protected String type;
    protected String brand;
    protected String model;
    protected int mileage;
    protected int year;
    protected float price;

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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
