package model;

public class Invoice {
    private final String invoiceID;
    private final String bookingID;
    private final String customerName;
    private final String roomNumber;
    private final double totalAmount;

    public Invoice(String invoiceID, String bookingID, String customerName, String roomNumber, double totalAmount) {
        this.invoiceID = invoiceID;
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayInfo() {
        System.out.println("InvoiceID: " + invoiceID + ", BookingID: " + bookingID + ", CustomerName: " + customerName + 
                           ", RoomNumber: " + roomNumber + ", TotalAmount: " + totalAmount);
    }
}
