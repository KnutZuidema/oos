public class Circle extends Point {
    protected int radius;

    public Circle() {
        super();
        this.radius = 0;
    }

    public Circle(Circle circle) {
        super(circle);
        this.radius = circle.radius;
    }

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Circle)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Circle circle = (Circle) object;
        return this.radius == circle.radius;
    }

    @Override
    public String toString() {
        return String.format("Circle(%d, %d, radius=%d)", this.x, this.y, this.radius);
    }
}
