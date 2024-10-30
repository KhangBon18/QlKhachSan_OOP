package service;

import java.io.*;
import java.util.*;
import model.Room;

public class RoomService {
    private final List<Room> rooms;

    public RoomService() {
        rooms = new ArrayList<>();
    }

    public void loadRoomsFromFile() {
        File file = new File("data/rooms.txt");
        if (!file.exists()) {
            System.out.println("File rooms.txt khong ton tai.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    String number = parts[1];
                    double price = Double.parseDouble(parts[2]);

                    Room room = new Room(type, number, price);
                    rooms.add(room);
                } else {
                    System.out.println("Dong khong hop le: " + line);
                }
            }
            System.out.println("Da load thong tin phong tu file.");
        } catch (IOException e) {
            System.out.println("Loi khi doc file rooms.txt: " + e.getMessage());
        }
    }

    public void saveRoomsToFile() {
        File file = new File("data/rooms.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Room room : rooms) {
                writer.write(room.getType() + "," + room.getNumber() + "," + room.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displayAllRooms() {
        for (Room room : rooms) {
            room.displayInfo();
        }
    }

    public void addNewRoom(Scanner scanner) {
        System.out.print("Nhap loai phong: ");
        String type = scanner.nextLine();
        String number;
        while (true) {
            System.out.print("Nhap so phong: ");
            number = scanner.nextLine();
            if (isRoomAvailable(number)) {
                System.out.println("So phong da ton tai. Vui long nhap so phong khac.");
            } else {
                break;
            }
        }
        System.out.print("Nhap gia phong: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Gia phong khong hop le. Vui long nhap lai.");
            return;
        }

        Room newRoom = new Room(type, number, price);
        rooms.add(newRoom);
        System.out.println("Da them phong moi: " + number);
    }

    public void updateRoom(Scanner scanner) {
        System.out.print("Nhap so phong can sua: ");
        String number = scanner.nextLine();
        Room roomToUpdate = null;
        for (Room room : rooms) {
            if (room.getNumber().equals(number)) {
                roomToUpdate = room;
                break;
            }
        }

        if (roomToUpdate == null) {
            System.out.println("Khong tim thay phong voi so phong: " + number);
            return;
        }

        System.out.print("Nhap loai phong moi: ");
        String newType = scanner.nextLine();
        System.out.print("Nhap gia phong moi: ");
        double newPrice;
        try {
            newPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Gia phong khong hop le. Vui long nhap lai.");
            return;
        }

        roomToUpdate.setType(newType);
        roomToUpdate.setPrice(newPrice);
        System.out.println("Da cap nhat phong: " + number);
    }

    public void deleteRoom(Scanner scanner) {
        System.out.print("Nhap so phong can xoa: ");
        String number = scanner.nextLine();
        Room roomToDelete = null;
        for (Room room : rooms) {
            if (room.getNumber().equals(number)) {
                roomToDelete = room;
                break;
            }
        }

        if (roomToDelete == null) {
            System.out.println("Khong tim thay phong voi so phong: " + number);
            return;
        }

        rooms.remove(roomToDelete);
        System.out.println("Da xoa phong: " + number);
    }

    public boolean isRoomAvailable(String roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                return true;
            }
        }
        return false;
    }

    public void searchRoomByType(String type) {
        boolean found = false;
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(type)) {
                room.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay phong voi loai: " + type);
        }
    }

    public void searchRoomByNumber(String number) {
        boolean found = false;
        for (Room room : rooms) {
            if (room.getNumber().equals(number)) {
                room.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay phong voi so phong: " + number);
        }
    }
}