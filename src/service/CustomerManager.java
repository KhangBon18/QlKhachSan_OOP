package service;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerManager {
    private final List<Customer> customers = new ArrayList<>();

    // Xem danh sách khách hàng
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Khong co khach hang nao.");
        } else {
            customers.forEach(Customer::displayInfo);
        }
    }

    // Thêm khách hàng mới
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Da them khach hang: " + customer.getName());
    }

    // Sửa thông tin khách hàng
    public void updateCustomer(String phone, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPhone().equals(phone)) {
                customers.set(i, updatedCustomer);
                System.out.println("Da cap nhat thong tin khach hang.");
                return;
            }
        }
        System.out.println("Khong tim thay khach hang co so dien thoai: " + phone);
    }

    // Xóa khách hàng
    public void deleteCustomer(String phone) {
        customers.removeIf(customer -> customer.getPhone().equals(phone));
        System.out.println("Da xoa khach hang co so dien thoai: " + phone);
    }

    // Tìm kiếm khách hàng theo tên
    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        System.out.println("Khong tim thay khach hang voi ten: " + name);
        return null;
    }

    public String getCustomerNameById(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer.getName();
            }
        }
        return "Khach hang khong ton tai";
    }
    
}
