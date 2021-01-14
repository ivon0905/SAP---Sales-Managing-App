package myApp.com.models;

public class Product {
    private String id;
    private String brand;
    private String color;
    private String description;
    private double price;
    private String type;
    private int quantity;
    private String section;

    public Product(String id, String brand, String color, String description, double price, String type, int quantity, String section){
        this.id=id;
        this.brand=brand;
        this.color=color;
        this.description=description;
        this.price=price;
        this.type=type;
        this.quantity=quantity;
        this.section=section;
    }

    public String getId() {
        return id;
    }
    public String getBrand(){
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSection() {
        return section;
    }
}
