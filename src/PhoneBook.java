import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String surname, String number) {
        List<String> phones = phoneBook.getOrDefault(surname, new ArrayList<>());
        phones.add(number);
        phoneBook.put(surname, phones);
    }

    public List<String> get(String surname) {
        return phoneBook.getOrDefault(surname, new ArrayList<>());
    }


    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "000000000");
        phoneBook.add("Петров", "025896378");
        phoneBook.add("Сидоров", "555555555");

        phoneBook.add("Иванов", "111111111");
        phoneBook.add("Петров", "222222222");
        phoneBook.add("Сидоров", "333333333");

        phoneBook.add("Иванов", "666666666");
        phoneBook.add("Петров", "456789455");
        phoneBook.add("Сидоров", "121212121");

        System.out.println("Номера по фамилии 'Иванов' - " + phoneBook.get("Иванов"));
        System.out.println("Номера по фамилии 'Петров' - " + phoneBook.get("Петров"));
        System.out.println("Номера по фамилии 'Сидоров' - " + phoneBook.get("Сидоров"));
    }
}
