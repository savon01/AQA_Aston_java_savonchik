class Cat extends Animal {
    protected String name;
    protected static int totalCats = 0;
    public boolean isFull = false;

    public Cat (String name) {
        super(200, 0);
        this.name = name;
        totalCats++;
    }

    public void eat (FoodBowl bowl, int foodAmount) {
        if (foodAmount < 0) {
            System.out.println("Нельзя есть отрицательное количество еды.");
        } else {
            if (foodAmount > bowl.getFoodAmount()) {
                System.out.println("Недостаточно еды в миске.");
            } else {
                bowl.foodAdd(-foodAmount);
                isFull = true;
                System.out.println(name + " покушал из миски.");
            }
        }
    }

    public boolean isFull() {
        return isFull;
    }
}

class FoodBowl {
    private int foodAmount;

    public FoodBowl (int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void foodAdd (int amount) {
        foodAmount += amount;
        System.out.println("Добавлено " + amount + " единиц еды в миску.");
        System.out.println("В миске " + foodAmount + " еды.");
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
