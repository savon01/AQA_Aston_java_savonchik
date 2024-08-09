class Dog extends Animal {
    protected String name;
    protected static int totalDogs = 0;

    public Dog(String name) {
        super( 500,10);
        this.name = name;
        totalDogs++;
    }
}
