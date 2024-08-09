public class Animal {
    protected int runLimit;
    protected int swimLimit;
    protected static int totalAnimals = 0;

    public Animal(int runLimit, int swimLimit) {
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        totalAnimals++;
    }

    public void run(int distance) {
        if (distance < 1) {
            System.out.println("Расстояние для бега должно быть больше или равно 1.");
        } else {
            if (distance <= runLimit) {
                if (getClass().getSimpleName().equals("Animal")) {
                    System.out.println("Животное пробежало " + distance + " м.");
                } else {
                    String animalName = (this instanceof Cat) ? ((Cat) this).name : ((Dog) this).name;
                    System.out.println(animalName + " пробежал " + distance + " м.");
                }
            } else {
                if (getClass().getSimpleName().equals("Animal")) {
                    System.out.println(" Животное не может пробежать такое расстояние.");
                } else {
                    String animalName = (this instanceof Cat) ? ((Cat) this).name : ((Dog) this).name;
                    System.out.println(animalName + " не может пробежать такое расстояние.");
                }
            }
        }
    }

    public void swim (int distance) {
        if (swimLimit == 0) {
            if (this instanceof Cat) {
                System.out.println(((Cat) this).name + " не умеет плавать.");
            }
            else {
                System.out.println(getClass().getSimpleName() + " не умеет плавать.");
            }
        } else if (distance < 1) {
            System.out.println("Расстояние плавания должно быть больше или равно 1.");
        } else {
            if (distance <= swimLimit) {
                if (getClass().getSimpleName().equals("Animal")) {
                    System.out.println("Животное проплыло " + distance + " м.");
                } else {
                    if (this instanceof Cat) {
                        System.out.println(((Cat) this).name + " не умеет плавать.");
                    }
                    else {
                        String animalName = (this instanceof Dog) ? ((Dog) this).name : "Животное";
                        System.out.println(animalName + " проплыл " + distance + " м.");
                    }
                }
            } else {
                if (getClass().getSimpleName().equals("Animal")) {
                    System.out.println("Животное не может проплыть такое растояние");
                } else {
                    String animalName = (this instanceof Cat) ? ((Cat) this).name : ((Dog) this).name;
                    System.out.println(animalName + " не может проплыть такое растояние");
                }
            }
        }
    }
}
