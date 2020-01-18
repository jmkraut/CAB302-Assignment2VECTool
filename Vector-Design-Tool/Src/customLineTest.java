import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class customLineTest {

    private customLine testLine;

    @BeforeEach
    void setUp() {
        testLine = new customLine(1,1,5,5, Color.black);
    }

    @Test
    void getX1() {
        assertEquals(testLine.getX1(), 1);
    }

    @Test
    void getY1() {
        assertEquals(testLine.getY1(), 1);
    }


    @Test
    void getX2() {
        assertEquals(testLine.getX2(), 5);
    }

    @Test
    void getY2() {
        assertEquals(testLine.getY2(), 5);
    }

    @Test
    void setShapePenColour() {
        testLine.setShapePenColour(Color.pink);
        assertEquals(testLine.getShapePenColour(), Color.pink);
    }

    @Test
    void getShapeType() {
        assertEquals(testLine.getShapeType(), GUI.ShapeType.LINE );
    }

    @Test
    void getShape() {
        assertEquals(testLine.getShape(), testLine);
    }

    @Test
    void getShapePenColour() {
        testLine.setShapePenColour(Color.pink);
        assertEquals(testLine.getShapePenColour(), Color.pink);
    }

    @Test
    void getShapeFillColour() {
        testLine.setShapeFillColour(Color.pink);
    }
}