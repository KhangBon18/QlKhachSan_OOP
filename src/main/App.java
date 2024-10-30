package main;

import java.util.Scanner;
import service.BookingService;
import service.RoomService;

public class App {
    public static void main(String[] args) {
        // Sử dụng try-with-resources để tự động đóng Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            RoomService roomService = new RoomService();
            BookingService bookingService = new BookingService(roomService);

            // Đọc dữ liệu từ file khi khởi chạy chương trình
            roomService.loadRoomsFromFile();
            bookingService.loadBookingsFromFile("data/bookings.txt");

            int choice = 0;
            do {
                System.out.println("\n===== QUAN LY KHACH SAN =====");
                System.out.println("1. Quan ly phong");
                System.out.println("2. Quan ly dat phong");
                System.out.println("3. Tim kiem");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                try {
                    choice = Integer.parseInt(scanner.nextLine()); // Đọc giá trị nhập từ người dùng
                } catch (NumberFormatException e) {
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                    continue;
                }

                switch (choice) {
                    case 1 -> manageRooms(scanner, roomService);
                    case 2 -> manageBookings(scanner, bookingService);
                    case 3 -> search(scanner, roomService, bookingService);
                    case 0 -> System.out.println("Cam on ban da su dung he thong!");
                    default -> System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                }
            } while (choice != 0); // Tiếp tục chạy cho đến khi người dùng chọn 0

            System.out.println("Chuong trinh ket thuc.");
        }
    }

    // Quản lý phòng
    private static void manageRooms(Scanner scanner, RoomService roomService) {
        int choice = 0;
        do {
            System.out.println("\n===== QUAN LY PHONG =====");
            System.out.println("1. Hien thi tat ca phong");
            System.out.println("2. Them phong moi");
            System.out.println("3. Sua phong");
            System.out.println("4. Xoa phong");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Lua chon cua ban: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                continue;
            }

            switch (choice) {
                case 1 -> roomService.displayAllRooms();
                case 2 -> {
                    roomService.addNewRoom(scanner);
                    roomService.saveRoomsToFile();
                }
                case 3 -> {
                    roomService.updateRoom(scanner);
                    roomService.saveRoomsToFile();
                }
                case 4 -> {
                    roomService.deleteRoom(scanner);
                    roomService.saveRoomsToFile();
                }
                case 0 -> System.out.println("Tro ve menu chinh.");
                default -> System.out.println("Lua chon khong hop le. Vui long nhap lai.");
            }
        } while (choice != 0);
    }

    // Quản lý đặt phòng
    private static void manageBookings(Scanner scanner, BookingService bookingService) {
        int choice = 0;
        do {
            System.out.println("\n===== QUAN LY DAT PHONG =====");
            System.out.println("1. Hien thi tat ca dat phong");
            System.out.println("2. Them dat phong moi");
            System.out.println("3. Sua dat phong");
            System.out.println("4. Xoa dat phong");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Lua chon cua ban: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                continue;
            }

            switch (choice) {
                case 1 -> bookingService.displayAllBookings();
                case 2 -> {
                    bookingService.addNewBooking(scanner);
                    bookingService.saveBookingsToFile();
                }
                case 3 -> {
                    bookingService.updateBooking(scanner);
                    bookingService.saveBookingsToFile();
                }
                case 4 -> {
                    bookingService.deleteBooking(scanner);
                    bookingService.saveBookingsToFile();
                }
                case 0 -> System.out.println("Tro ve menu chinh.");
                default -> System.out.println("Lua chon khong hop le. Vui long nhap lai.");
            }
        } while (choice != 0);
    }

    // Tìm kiếm
    private static void search(Scanner scanner, RoomService roomService, BookingService bookingService) {
        int choice = 0;
        do {
            System.out.println("\n===== TIM KIEM =====");
            System.out.println("1. Tim kiem phong theo loai");
            System.out.println("2. Tim kiem dat phong theo ten khach hang");
            System.out.println("3. Tim kiem dat phong theo ngay");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Lua chon cua ban: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhap loai phong: ");
                    String roomType = scanner.nextLine();
                    roomService.searchRoomByType(roomType);
                }
                case 2 -> {
                    System.out.print("Nhap ten khach hang: ");
                    String customerName = scanner.nextLine();
                    bookingService.searchBookingByCustomerName(customerName);
                }
                case 3 -> {
                    System.out.print("Nhap ngay (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    bookingService.searchBookingByDate(date);
                }
                case 0 -> System.out.println("Tro ve menu chinh.");
                default -> System.out.println("Lua chon khong hop le. Vui long nhap lai.");
            }
        } while (choice != 0);
    }
}