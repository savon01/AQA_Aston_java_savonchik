public class Main {
    public static void main(String[] args) {
        Dog dogBars = new Dog("Пес");

        dogBars.run(150);
        dogBars.swim(5);

        FoodBowl bowl = new FoodBowl(30);

        Cat[] cats = new Cat[2];
        cats[0] = new Cat("Мурзик");
        cats[1] = new Cat("Барсик");

        cats[0].run(150);
        cats[0].swim(5);

        cats[1].run(200);
        cats[1].swim(0);

        for (Cat cat : cats) {
            cat.eat(bowl, 25);
        }

        System.out.println("Информация о сытости котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "сыт" : "голоден"));
        }

        System.out.println("Общее количество животных: " + Animal.totalAnimals);
        System.out.println("Общее количество котов: " + Cat.totalCats);
        System.out.println("Общее количество собак: " + Dog.totalDogs);
    }
}