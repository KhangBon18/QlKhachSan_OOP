package model;

public class Employee extends Person {
    private final String employeeID;
    private final String position;

    public Employee(String name, String phone, String employeeID, String position) {
        super(name, phone);
        this.employeeID = employeeID;
        this.position = position;
    }

    @Override
    public void displayInfo() {
        System.out.println("Employee: " + name + ", Phone: " + phone + 
                           ", ID: " + employeeID + ", Position: " + position);
    }
}
