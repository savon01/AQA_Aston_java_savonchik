interface Shape {
    String getFillColor();
    String getBorderColor();


    default double getPerimeter() {
        throw new UnsupportedOperationException("Метод getPerimeter должен быть переопределен в классе фигуры.");
    }


    default double getArea() {
        throw new UnsupportedOperationException("Метод getArea должен быть переопределен в классе фигуры.");
    }


    default void printInfo() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}


public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5, "Красный", "Черный");
        System.out.println("Информация о круге:");
        circle.printInfo();

        Shape rectangle = new Rectangle(5, 5, "Желтый", "Черный");
        System.out.println("Информация о прямоугольнике:");
        rectangle.printInfo();

        Shape triangle = new Triangle(3, 4, 5,"Белый", "Красный");
        System.out.println("Информация о треугольнике:");
        triangle.printInfo();
    }
}