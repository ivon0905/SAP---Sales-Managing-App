package myApp.com.models;

public class Manager {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNum;
    private String brand;

    public Manager(String id, String name, String lastName, String email, String phoneNum, String brand){
        this.id=id;
        this.name=name;
        this.lastName=lastName;
        this.email=email;
        this.phoneNum=phoneNum;
        this.brand=brand;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getBrand() {
        return brand;
    }
}
