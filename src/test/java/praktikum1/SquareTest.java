package praktikum1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    private final int expected1 = 0;
    private final int expected2 = 10;
    private Square square1, square2, square3;

    @BeforeEach
    void setUp() {
        square1 = new Square();
        square2 = new Square(new Point(expected2, expected2), expected2, expected2);
        square3 = new Square(expected2, expected2, expected2, expected2);
    }

    @Test
    void area() {
        assertEquals(expected1 * expected1, square1.area());
        assertEquals(expected2 * expected2, square2.area());
    }

    @Test
    void clone1() {
        assertEquals(square1, square1.clone());
        assertNotSame(square1, square1.clone());
    }

    @Test
    void equals() {
        assertEquals(square2, square3);
        assertNotEquals(square1, square2);
    }

    @Test
    void toString1() {
        assertEquals(String.format("praktikum1.Square(position=praktikum1.Point(%1$d, %1$d), width=%1$d, height=%1$d)", expected1),
                square1.toString());
    }
}