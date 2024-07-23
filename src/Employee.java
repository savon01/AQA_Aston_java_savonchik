// задания 1-2
public class Employee {
    private String fullName;
    private String post;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public Employee (String fullName, String post, String email, String phone, double salary, int age) {
        this.fullName = fullName;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Должность: " + post);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
    }

    public static void main (String[] args) {
        Employee[] persArray = new Employee[5];
        persArray[0] = new Employee("Ivanov Ivan", "Engineer",
                "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Employee("Попов Петр Петрович", "менеджер",
                "popov@mail.com", "123456789", 40000, 25);
        persArray[2] = new Employee("Васин Василий Васильевич", "разработчик",
                "vasin@mail.com", "9379992", 120000, 28);
        persArray[3] = new Employee("Сергеев Сергей Сергеевич", "грузчик",
                "sergeev@mail.com", "3758964578", 25000, 35);
        persArray[4] = new Employee("Бородулин Борода Бородинович", "стажер",
                "boroda@mail.com", "8563214", 10000, 22);
    }
}
