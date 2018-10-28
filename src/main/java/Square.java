public class Square implements Cloneable, HasArea {
    private Point position;
    private int height, width;

    public Square() {
        this.position = new Point();
        this.height = 0;
        this.width = 0;
    }

    public Square(Point position, int width, int height) {
        this.position = position;
        this.height = height;
        this.width = width;
    }

    public Square(int x, int y, int width, int height) {
        this.position = new Point(x, y);
        this.height = height;
        this.width = width;
    }

    public double area() {
        return this.height * this.width;
    }

    @Override
    public Square clone() {
        return new Square(this.position, this.width, this.height);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Square)) {
            return false;
        }
        Square square = (Square) object;
        if (!this.position.equals(square.position)) {
            return false;
        }
        return this.height == square.height && this.width == square.width;
    }

    @Override
    public String toString() {
        return String.format("Square(position=%s, width=%d, height=%d)", this.position, this.width, this.height);
    }
}
