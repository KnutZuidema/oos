public class Point {
    protected int x;
    protected int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getLocation() {
        return this;
    }

    public void setLocation(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Point)) {
            return false;
        }
        Point point = (Point) object;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public String toString() {
        return String.format("Point(%d, %d)", this.x, this.y);
    }
}
