import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomEllipseTest {

    private CustomEllipse testEllipse;

    @BeforeEach
    void setUp() {
        testEllipse = new CustomEllipse(10, 15, 13, 26, Color.BLACK, null);
    }

    @Test
    void getX() {
        assertEquals(10, testEllipse.getX());
    }

    @Test
    void getY() {
        assertEquals(15, testEllipse.getY());
    }

    @Test
    void getWidth() {
        assertEquals(13, testEllipse.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(26, testEllipse.getHeight());
    }

    @Test
    void setShapePenColour() {
        testEllipse.setShapePenColour(Color.GREEN);
        assertEquals(Color.GREEN, testEllipse.getShapePenColour());
    }

    @Test
    void setShapeFillColour() {
        testEllipse.setShapeFillColour(Color.RED);
        assertEquals(Color.RED, testEllipse.getShapeFillColour());
    }

    @Test
    void getShapePenColour() {
        assertEquals(Color.BLACK, testEllipse.getShapePenColour());
    }

    @Test
    void getShapeFillColour() {
        assertNull(testEllipse.getShapeFillColour());
    }

    @Test
    void GetShapeType(){
        assertEquals(GUI.ShapeType.ELLIPSE, testEllipse.getShapeType());
    }

    @Test
    void GetShape() {
        assertEquals(testEllipse, testEllipse.getShape());
    }
}