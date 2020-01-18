import java.awt.*;

/**
 * Interface for all Custom Shapes, Extends the <i>Shape</i> class.
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public interface ShapeControl extends Shape {
    void setShapePenColour(Color colour);
    void setShapeFillColour(Color colour);
    GUI.ShapeType getShapeType();
    Shape getShape();
    Color getShapePenColour();
    Color getShapeFillColour();

}