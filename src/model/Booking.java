package model;

public class Booking {
    private final String bookingID;
    private String customerName;
    private String customerPhone;
    private String roomNumber;
    private String checkInDate;
    private String checkOutDate;

    public Booking(String bookingID, String customerName, String customerPhone, String roomNumber, String checkInDate, String checkOutDate) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void displayInfo() {
        System.out.println("BookingID: " + bookingID + ", CustomerName: " + customerName + ", CustomerPhone: " + customerPhone + 
                           ", RoomNumber: " + roomNumber + ", CheckInDate: " + checkInDate + ", CheckOutDate: " + checkOutDate);
    }
}
