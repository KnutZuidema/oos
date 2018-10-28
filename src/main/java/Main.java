public class Main {
    public static void main(String[] args) {
        HasArea objects[] = {
                new Square(5, 6, 1, 1),
                new Square(2, 4, 1, 1),
                new CircleAggregation(45, 76, 1),
                new CircleAggregation(56, 12, 1)
        };

        double totalArea = 0;
        for (HasArea object : objects) {
            totalArea += object.area();
        }

        System.out.println(totalArea);
    }
}
