import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomPolygonTest {

    CustomPolygon testPolygon;

    @BeforeEach
    void setUp() {
        List<double[]> coordinates = new ArrayList<>();
        testPolygon = new CustomPolygon(coordinates, Color.black, null);
    }

    @Test
    void setShapePenColour() {
        testPolygon.setShapePenColour(Color.pink);
        assertEquals(Color.pink, testPolygon.getShapePenColour());
    }

    @Test
    void setShapeFillColour() {
        testPolygon.setShapeFillColour(Color.pink);
        assertEquals(Color.pink, testPolygon.getShapeFillColour());
    }

    @Test
    void getShapeType() {
        assertEquals(testPolygon.getShapeType(), GUI.ShapeType.POLYGON);
    }

    @Test
    void getShape() {
        assertEquals(testPolygon.getShape(), testPolygon);
    }

    @Test
    void getShapePenColour() {
        testPolygon.setShapePenColour(Color.pink);
        assertEquals(Color.pink, testPolygon.getShapePenColour());
    }

    @Test
    void getShapeFillColour() {
        testPolygon.setShapeFillColour(Color.pink);
        assertEquals(Color.pink, testPolygon.getShapeFillColour());
    }
}