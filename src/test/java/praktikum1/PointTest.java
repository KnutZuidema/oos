package praktikum1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PointTest {
    private final int expected1 = 0;
    private final int expected2 = 10;
    private final int moveDelta = 5;
    private Point point1, point2, point3;

    @BeforeEach
    void setUp() {
        point1 = new Point();
        point2 = new Point(expected2, expected2);
        point3 = new Point(point2);
    }

    @Test
    void getX() {
        assertEquals(expected1, point1.getX());
        assertEquals(expected2, point2.getX());
        assertEquals(expected2, point3.getX());
    }

    @Test
    void getY() {
        assertEquals(expected1, point1.getY());
        assertEquals(expected2, point2.getY());
        assertEquals(expected2, point3.getY());
    }

    @Test
    void getLocation() {
        assertEquals(point1, point1.getLocation());
    }

    @Test
    void setLocation() {
        point1.setLocation(point2);
        assertEquals(point1, point2);
    }

    @Test
    void setLocation1() {
        point1.setLocation(expected2, expected2);
        assertEquals(point1, point2);
    }

    @Test
    void move() {
        point1.move(moveDelta, moveDelta);
        assertEquals(expected1 + moveDelta, point1.getX());
        assertEquals(expected1 + moveDelta, point1.getY());
    }

    @Test
    void equals() {
        assertEquals(point2, point3);
        assertNotEquals(point1, point2);
    }

    @Test
    void toString1() {
        assertEquals(String.format("praktikum1.Point(%1$d, %1$d)", expected1), point1.toString());
    }
}