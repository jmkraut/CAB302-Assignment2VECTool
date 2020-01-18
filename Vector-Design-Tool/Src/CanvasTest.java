import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class CanvasTest {

    private Canvas testCanvas;
    private List<ShapeControl> testShapes;
    private customRectangle testTangle;

    @BeforeEach
    void setUp() {
        testCanvas = new Canvas();
        testShapes = new ArrayList<>();
        testTangle = new customRectangle(10, 10, 10, 10,
                Color.BLACK,
                Color.WHITE);
    }

    @Test
    void arrayGrowsDynamicallyTest(){
        testShapes.add(testTangle);

        assertEquals(1, testShapes.size());

        testShapes.add(testTangle);

        assertEquals(2, testShapes.size());
    }

    @Test
    void clearTest() {
        for(int i = 0; i < 5; i++){
            testShapes.add(testTangle);
        }

        assertEquals(5, testShapes.size());

        testShapes.clear();

        assertEquals(0, testShapes.size());
    }

    @Test
    void paintComponent() {

    }

    @Test
    void getPenColor() {
        assertEquals(Color.BLACK, testCanvas.getPenColor());
    }

    @Test
    void setPenColor() {
        testCanvas.setPenColor(Color.GREEN);
        assertEquals(Color.GREEN, testCanvas.getPenColor());
    }

    @Test
    void setCurrentSelectedShape() {
        testCanvas.setCurrentSelectedShape(GUI.ShapeType.RECTANGLE);
        assertEquals(GUI.ShapeType.RECTANGLE, testCanvas.getCurrentSelectedShape());
    }

    @Test
    void getCurrentSelectedShape() {
        assertEquals(GUI.ShapeType.LINE, testCanvas.getCurrentSelectedShape());
    }

    @Test
    void getFillColor() {
        assertEquals(null, testCanvas.getFillColor());
    }

    @Test
    void setFillColor() {
        testCanvas.setFillColor(Color.GREEN);
        assertEquals(Color.GREEN, testCanvas.getFillColor());
    }
}