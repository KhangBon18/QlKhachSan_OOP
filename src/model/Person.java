package model;

public abstract class Person {
    protected String name;
    protected String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getter cho name
    public String getName() {
        return name;
    }

    // Phương thức trừu tượng để hiển thị thông tin
    public abstract void displayInfo();
}
