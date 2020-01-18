import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class customRectangleTest {

    customRectangle testTangle;

    @BeforeEach
    void setUp() {
        testTangle = new customRectangle(10,
                12,
                36,
                32,
                Color.BLACK,
                Color.PINK);
    }

    @Test
    void getX() {
        assertEquals(10, testTangle.getX());
    }

    @Test
    void getY() {
        assertEquals(12, testTangle.getY());
    }

    @Test
    void getWidth() {
        assertEquals(26, testTangle.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(20, testTangle.getHeight());
    }

    @Test
    void setShapePenColour() {
        testTangle.setShapePenColour(Color.GREEN);
        assertEquals(Color.GREEN, testTangle.getShapePenColour());
    }

    @Test
    void setShapeFillColour() {
        testTangle.setShapeFillColour(Color.PINK);
        assertEquals(Color.PINK, testTangle.getShapeFillColour());
    }

    @Test
    void getShapeType() {
        assertEquals(GUI.ShapeType.RECTANGLE, testTangle.getShapeType());
    }

    @Test
    void getShape() {
        assertEquals(testTangle, testTangle.getShape());
    }

    @Test
    void getShapePenColour() {
    }

    @Test
    void getShapeFillColour() {
    }
}