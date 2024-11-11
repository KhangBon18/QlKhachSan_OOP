package service;

import java.util.ArrayList;
import java.util.List;
import model.Person;

public class PersonManager {
    private final List<Person> people = new ArrayList<>();

    // Thêm người (Customer hoặc Employee)
    public void addPerson(Person person) {
        people.add(person);
        System.out.println("Person added: " + person.getClass().getSimpleName() + " - " + person.getName());
    }

    // Xóa người theo tên
    public void deletePerson(String name) {
        people.removeIf(person -> person.getName().equals(name));
        System.out.println("Person with name: " + name + " deleted.");
    }

    // Tìm người theo tên
    public Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        System.out.println("Person with name: " + name + " not found.");
        return null;
    }

    // Hiển thị tất cả các người
    public void displayAllPeople() {
        people.forEach(Person::displayInfo);
    }
}
