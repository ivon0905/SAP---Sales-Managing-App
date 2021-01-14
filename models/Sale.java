package myApp.com.models;

public class Sale {
    private String id;
    private int quantity;
    private double price;
    private double finalPrice;
    private String brand;
    private String date;

    public Sale(String id, int quantity, double price, double finalPrice, String brand, String date){
        this.id=id;
        this.quantity=quantity;
        this.price=price;
        this.finalPrice=finalPrice;
        this.brand=brand;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getBrand() {
        return brand;
    }

    public String getDate() {
        return date;
    }
}
