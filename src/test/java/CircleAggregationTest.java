import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleAggregationTest {
    private CircleAggregation circle1, circle2, circle3;
    private final int expected1 = 0;
    private final int expected2 = 10;

    @BeforeEach
    void setUp() {
        circle1 = new CircleAggregation();
        circle2 = new CircleAggregation(new Point(expected2, expected2), expected2);
        circle3 = new CircleAggregation(expected2, expected2, expected2);
    }

    @Test
    void area() {
        assertEquals(Math.PI * expected2 * expected2, circle2.area());
    }

    @Test
    void clone1() {
        assertEquals(circle1, circle1.clone());
        assertNotSame(circle1, circle1.clone());
    }

    @Test
    void equals() {
        assertEquals(circle2, circle3);
        assertNotEquals(circle1, circle2);
    }

    @Test
    void toString1() {
        assertEquals(String.format("CircleAggregation(position=Point(%1$d, %1$d), radius=%1$d)", expected1),
                circle1.toString());
    }
}