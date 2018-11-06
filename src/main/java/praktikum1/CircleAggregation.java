package praktikum1;

public class CircleAggregation implements Cloneable, HasArea {
    private Point position;
    private int radius;

    public CircleAggregation() {
        this.position = new Point();
        this.radius = 0;
    }

    public CircleAggregation(Point position, int radius) {
        this.position = position;
        this.radius = radius;
    }

    public CircleAggregation(int x, int y, int radius) {
        this.position = new Point(x, y);
        this.radius = radius;
    }

    public double area() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public CircleAggregation clone() {
        return new CircleAggregation(this.position, this.radius);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CircleAggregation)) {
            return false;
        }
        CircleAggregation circleAggregation = (CircleAggregation) object;
        if (!this.position.equals(circleAggregation.position)) {
            return false;
        }
        return this.radius == circleAggregation.radius;
    }

    @Override
    public String toString() {
        return String.format("praktikum1.CircleAggregation(position=%s, radius=%d)", this.position, this.radius);
    }
}
