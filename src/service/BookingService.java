package service;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import model.Booking;
import model.Invoice;
import model.Room;

public class BookingService {
    private final List<Booking> bookings;
    private final RoomService roomService;
    private final List<Invoice> invoices;

    public BookingService(RoomService roomService) {
        this.bookings = new ArrayList<>();
        this.roomService = roomService;
        this.invoices = new ArrayList<>();
    }

    public void loadBookingsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String bookingID = parts[0];
                    String customerName = parts[1];
                    String customerPhone = parts[2];
                    String roomNumber = parts[3];
                    String checkInDate = parts[4];
                    String checkOutDate = parts[5];

                    Booking booking = new Booking(bookingID, customerName, customerPhone, roomNumber, checkInDate, checkOutDate);
                    bookings.add(booking);
                } else {
                    System.out.println("Dong khong hop le: " + line);
                }
            }
            System.out.println("Da load thong tin dat phong tu file.");
        } catch (IOException e) {
            System.out.println("Loi khi doc file bookings.txt: " + e.getMessage());
        }
    }

    public void saveBookingsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/bookings.txt"))) {
            for (Booking booking : bookings) {
                writer.write(booking.getBookingID() + "," + booking.getCustomerName() + "," + booking.getCustomerPhone() + "," + 
                             booking.getRoomNumber() + "," + booking.getCheckInDate() + "," + booking.getCheckOutDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("Khong co dat phong nao.");
        } else {
            for (Booking booking : bookings) {
                booking.displayInfo();
            }
        }
    }

    public void addNewBooking(Scanner scanner) {
        String bookingID;
        while (true) {
            System.out.print("Nhap ma dat phong: ");
            bookingID = scanner.nextLine();
            if (isBookingIDExists(bookingID)) {
                System.out.println("Ma dat phong da ton tai. Vui long nhap ma khac.");
            } else {
                break;
            }
        }
        System.out.print("Nhap ten khach hang: ");
        String customerName = scanner.nextLine();
        System.out.print("Nhap so dien thoai khach hang: ");
        String customerPhone = scanner.nextLine();
        System.out.print("Nhap so phong: ");
        String roomNumber = scanner.nextLine();
        // Kiểm tra sự tồn tại của phòng
        if (!roomService.isRoomAvailable(roomNumber)) {
            System.out.println("Phong khong ton tai. Khong the dat phong.");
            return;
        }
        System.out.print("Nhap ngay check-in (yyyy-MM-dd): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Nhap ngay check-out (yyyy-MM-dd): ");
        String checkOutDate = scanner.nextLine();
        // Kiểm tra xem phòng có sẵn trong khoảng thời gian này không
        if (!isRoomAvailableForBooking(roomNumber, checkInDate, checkOutDate)) {
            System.out.println("Phong da duoc dat trong khoang thoi gian nay. Khong the dat phong.");
            return;
        }
        Booking booking = new Booking(bookingID, customerName, customerPhone, roomNumber, checkInDate, checkOutDate);
        bookings.add(booking);
        System.out.println("Da them dat phong: " + bookingID);
    }

    private boolean isBookingIDExists(String bookingID) {
        for (Booking booking : bookings) {
            if (booking.getBookingID().equals(bookingID)) {
                return true;
            }
        }
        return false;
    }

    public void updateBooking(Scanner scanner) {
        System.out.print("Nhap ma dat phong can sua: ");
        String bookingID = scanner.nextLine();
        Booking bookingToUpdate = null;
        for (Booking booking : bookings) {
            if (booking.getBookingID().equals(bookingID)) {
                bookingToUpdate = booking;
                break;
            }
        }
        if (bookingToUpdate == null) {
            System.out.println("Khong tim thay dat phong voi ma: " + bookingID);
            return;
        }
        System.out.print("Nhap ten khach hang moi: ");
        String newCustomerName = scanner.nextLine();
        System.out.print("Nhap so dien thoai khach hang moi: ");
        String newCustomerPhone = scanner.nextLine();
        System.out.print("Nhap so phong moi: ");
        String newRoomNumber = scanner.nextLine();
        // Kiểm tra sự tồn tại của phòng
        if (!roomService.isRoomAvailable(newRoomNumber)) {
            System.out.println("Phong khong ton tai. Khong the cap nhat dat phong.");
            return;
        }
        System.out.print("Nhap ngay check-in moi (yyyy-MM-dd): ");
        String newCheckInDate = scanner.nextLine();
        System.out.print("Nhap ngay check-out moi (yyyy-MM-dd): ");
        String newCheckOutDate = scanner.nextLine();
        // Kiểm tra xem phòng có sẵn trong khoảng thời gian này không
        if (!isRoomAvailableForBooking(newRoomNumber, newCheckInDate, newCheckOutDate, bookingID)) {
            System.out.println("Phong da duoc dat trong khoang thoi gian nay. Khong the cap nhat dat phong.");
            return;
        }
        bookingToUpdate.setCustomerName(newCustomerName);
        bookingToUpdate.setCustomerPhone(newCustomerPhone);
        bookingToUpdate.setRoomNumber(newRoomNumber);
        bookingToUpdate.setCheckInDate(newCheckInDate);
        bookingToUpdate.setCheckOutDate(newCheckOutDate);
        System.out.println("Da cap nhat dat phong: " + bookingID);
    }

    public void deleteBooking(Scanner scanner) {
        System.out.print("Nhap ma dat phong can xoa: ");
        String bookingID = scanner.nextLine();

        boolean removed = bookings.removeIf(b -> b.getBookingID().equals(bookingID));
        if (removed) {
            System.out.println("Da xoa dat phong voi ma: " + bookingID);
        } else {
            System.out.println("Khong tim thay dat phong voi ma: " + bookingID);
        }
    }

    public void searchBookingByCustomerName(String customerName) {
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.getCustomerName().equalsIgnoreCase(customerName)) {
                booking.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay dat phong voi ten khach hang: " + customerName);
        }
    }

    public void searchBookingByCustomerPhone(String customerPhone) {
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.getCustomerPhone().equals(customerPhone)) {
                booking.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay dat phong voi so dien thoai: " + customerPhone);
        }
    }

    public void searchBookingByDate(String date) {
        boolean found = false;
        for (Booking booking : bookings) {
            if (date.compareTo(booking.getCheckInDate()) >= 0 && date.compareTo(booking.getCheckOutDate()) <= 0) {
                booking.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay dat phong nao trong ngay: " + date);
        }
    }

    private boolean isRoomAvailableForBooking(String roomNumber, String checkInDate, String checkOutDate) {
        return isRoomAvailableForBooking(roomNumber, checkInDate, checkOutDate, null);
    }

    private boolean isRoomAvailableForBooking(String roomNumber, String checkInDate, String checkOutDate, String excludeBookingID) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber().equals(roomNumber) &&
                (excludeBookingID == null || !booking.getBookingID().equals(excludeBookingID)) &&
                !(checkOutDate.compareTo(booking.getCheckInDate()) <= 0 || checkInDate.compareTo(booking.getCheckOutDate()) >= 0)) {
                return false;
            }
        }
        return true;
    }

    public void calculateInvoice(Scanner scanner) {
        System.out.print("Nhap ma dat phong: ");
        String bookingID = scanner.nextLine();
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.getBookingID().equals(bookingID)) {
                booking = b;
                break;
            }
        }
        if (booking == null) {
            System.out.println("Khong tim thay dat phong voi ma: " + bookingID);
            return;
        }

        Room room = roomService.getRoomByNumber(booking.getRoomNumber());
        if (room == null) {
            System.out.println("Khong tim thay phong voi so phong: " + booking.getRoomNumber());
            return;
        }

        double totalAmount = calculateTotalAmount(booking, room);
        String invoiceID = "INV" + (invoices.size() + 1);
        Invoice invoice = new Invoice(invoiceID, bookingID, booking.getCustomerName(), booking.getRoomNumber(), totalAmount);
        invoices.add(invoice);
        saveInvoicesToFile();
        System.out.println("Da tinh hoa don: ");
        invoice.displayInfo();
    }

    private double calculateTotalAmount(Booking booking, Room room) {
        // Giả sử mỗi ngày có giá phòng là room.getPrice()
        long days = ChronoUnit.DAYS.between(LocalDate.parse(booking.getCheckInDate()), LocalDate.parse(booking.getCheckOutDate()));
        return days * room.getPrice();
    }

    public void saveInvoicesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/invoices.txt"))) {
            for (Invoice invoice : invoices) {
                writer.write(invoice.getInvoiceID() + "," + invoice.getBookingID() + "," + invoice.getCustomerName() + "," + 
                             invoice.getRoomNumber() + "," + invoice.getTotalAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadInvoicesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/invoices.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String invoiceID = parts[0];
                    String bookingID = parts[1];
                    String customerName = parts[2];
                    String roomNumber = parts[3];
                    double totalAmount = Double.parseDouble(parts[4]);

                    Invoice invoice = new Invoice(invoiceID, bookingID, customerName, roomNumber, totalAmount);
                    invoices.add(invoice);
                } else {
                    System.out.println("Dong khong hop le: " + line);
                }
            }
            System.out.println("Da load thong tin hoa don tu file.");
        } catch (IOException e) {
            System.out.println("Loi khi doc file invoices.txt: " + e.getMessage());
        }
    }
}