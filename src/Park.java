// задание 3
public class Park {
    private String title;

    public Park (String title) {
        this.title = title;
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction (String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }
    }
}
