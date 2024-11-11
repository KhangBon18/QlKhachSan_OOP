package model;

public class Customer extends Person {
    public Customer(String name, String phone) {
        super(name, phone);
    }

    // Getter cho phone
    public String getPhone() {
        return phone;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer: " + name + ", Phone: " + phone);
    }

    public Object getCustomerID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerID'");
    }
}
