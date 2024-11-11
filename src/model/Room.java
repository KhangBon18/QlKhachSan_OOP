package model;

public class Room {
    private String type;
    private final String number;
    private double price;

    public Room(String type, String number, double price) {
        this.type = type;
        this.number = number;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Phong: " + number + ", Loai: " + type + ", Gia: " + price);
    }
}
