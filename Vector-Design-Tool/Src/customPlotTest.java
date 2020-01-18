import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class customPlotTest {
    customPlot testPlot;

    @BeforeEach
    void setUp() {
        testPlot = new customPlot(1,1,Color.black,1,1);
    }

    @Test
    void getX() {
        assertEquals(1, testPlot.getX());
    }

    @Test
    void getY() {
        assertEquals(1,testPlot.getY());
    }

    @Test
    void getWidth() {
        assertEquals(1, testPlot.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(1, testPlot.getHeight());
    }

    @Test
    void setShapePenColour() {
        testPlot.setShapePenColour(Color.pink);
        assertEquals(Color.pink, testPlot.getShapePenColour());
    }

    @Test
    void setShapeFillColour() {
        testPlot.setShapeFillColour(Color.pink);
        assertEquals(Color.pink, testPlot.getShapeFillColour());
    }

    @Test
    void getShapeType() {
        assertEquals(testPlot.getShapeType(), GUI.ShapeType.PLOT);
    }

    @Test
    void getShape() {
        assertEquals(testPlot.getShape(), testPlot);
    }

    @Test
    void getShapeFillColour() {
        testPlot.setShapeFillColour(Color.pink);
        assertEquals(Color.pink, testPlot.getShapeFillColour());
    }
}