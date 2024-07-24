import java.util.Arrays;

public class Lesson_7 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(checkSumRange(16, 5));
        printSign(-5);
        System.out.println(isNegative(-5));
        printStringNumberTimes("Hello", 5);
        System.out.println(isLeapYear(2022));
        printModifiedArray();
        printArrayOneHundred();
        modifyArray();
        createDiagonalMatrix(5);
        System.out.println(Arrays.toString(createInitializedArray(5, 10)));
    }

    // задание 1
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    // задание 2
    public static void checkSumSign() {
        int a = 50;
        int b = -50;
        int sum = (a + b);
        if (sum >= 0) System.out.println("Сумма положительная");
        else {
            System.out.println("Сумма отрицательная");
        }
    }

    // задание 3
    public static void printColor() {
        int value = 1;
        if (value <= 0) System.out.println("Красный");
        else if (value <= 100) System.out.println("Желтый");
        else {
            System.out.println("Зеленый");
        }
    }

    //  задание 4
    public static void compareNumbers() {
        int a = 50;
        int b = 50;
        System.out.println ((a >= b) ? "a >= b" : "a < b");
    }

    // задание 5
    public static boolean checkSumRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // задание 6
    public static void printSign(int number) {
        System.out.println((number >= 0) ? "Положительное" : "Отрицательное");
    }

    // задание 7
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // задание 8
    public static void printStringNumberTimes(String str, int number) {
        for (int i = 0; i < number; i++) {
            System.out.println(str);
        }
    }

    //задание 9
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // задание 10
    public static void printModifiedArray() {
        int[] arr = {1, 0, 0, 0, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    // задание 11
    public static void printArrayOneHundred() {
        int [] arr = new  int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    // задание 12
    public static void modifyArray() {
        int [] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    // задание 13
    public static void createDiagonalMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1; // Заполнение первой диагонали
            matrix[i][n - i - 1] = 1; // Заполнение второй диагонали
        }

        // Вывод матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // задание 14
    public static int[] createInitializedArray(int len, int initialValue) {
        int [] newArray = new int[len];
        for (int i = 0; i < len; i++) {
            newArray[i] = initialValue;
        }
        return newArray;
    }

}

