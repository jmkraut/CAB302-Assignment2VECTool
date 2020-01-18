import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPolygon extends Polygon implements ShapeControl{
    /**
     * <h1>CustomPolygon</h1>
     * <p>
     *  Describes a polygon object by it's coordinates, fillColour and penColour
     * </p>
     * @author Jessica Williams, William Daley, Jacob Kraut
     * @version 1.0
     * @since 2019-05-03
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.POLYGON;

    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    /**
     * Constructs a new object at the specified location with the specified colours.
     * @param coordinates The x and y coordinates at all the points of the polygon.
     * @param penColour The colour of the shapes outline.
     * @param fillColour The colour of the fill.
     */
    public CustomPolygon(List<double[]> coordinates, Color penColour, Color fillColour) {
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);
        this.fillColour = fillColour;
        this.penColour = penColour;
    }

    /**
     * Returns an array of x coordinates of all the point of the polygon.
     * @return An <i>int</i> array of x coordinates of all the point of the polygon.
     */
    public int[] getXCoordinates()
    {
        return xpoints;
    }

    /**
     * Returns an array of y coordinates of all the point of the polygon.
     * @return An <i>int</i> array of y coordinates of all the point of the polygon.
     */
    public int[] getYCoordinates()
    {
        return ypoints;
    }

    /**
     * Sets the x coordinates to the new array of <i>ints</i>.
     * @param x The desired x coordinates to be assigned.
     * @return Nothing.
     */
    public void setXCoordinates(int[] xCoordinates)
    {
        xpoints = xCoordinates;
    }

    /**
     * Sets the y coordinates to the new array of <i>ints</i>.
     * @param y The desired y coordinates to be assigned.
     * @return Nothing.
     */
    public void setYCoordinates(int[] yCoordinates)
    {
        ypoints = yCoordinates;
    }

    /**
     * Sets the penColour for the polygon object.
     * @param colour  The desired penColour for the polygon object.
     * @return Nothing.
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Sets the fill colour for the polygon object.
     * @param colour  The desired fill colour for the polygon object.
     * @return Nothing.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    /**
     * Returns the type of the polygon.
     * @return The type of the polygon as a <i>SHAPE_TYPE</i>.
     */
    @Override
    public GUI.ShapeType getShapeType() {
        return SHAPE_TYPE;
    }

    /**
     * Returns the polygon object
     * @return An instance of itself as a <i>Shape</i> Object.
     */
    @Override
    public Shape getShape() {
        return this;
    }

    /**
     * Returns the penColour for the plot object.
     * @return The penColour for the plot object.
     */
    @Override
    public Color getShapePenColour() {
        return penColour;
    }

    /**
     * Returns the fillColour for the plot object.
     * @return The fillColour as a <i>Color</i> object for the plot object.
     */
    @Override
    public Color getShapeFillColour() {
        return fillColour;
    }
}
