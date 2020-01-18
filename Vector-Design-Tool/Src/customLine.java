import java.awt.*;
import java.awt.geom.Line2D;


public class customLine extends Line2D.Double implements ShapeControl{
    /**
     * <h1>customLine</h1>
     * <p>
     *     Describes a customLine shape  shape object by its location and colour.
     * </p>
     * @author Jessica Williams, William Daley, Jacob Kraut
     * @version 1.0
     * @since 2019-05-03
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.LINE;

    private double x1, x2;
    private double y1, y2;
    private Color fillColour;
    private Color penColour;

    /**
     * Constructs a new <i>customLine</i> object at the specified coordinates
     * with the specified colour.
     * @param x1 The x coordinate for the starting location.
     * @param y1 The y coordinate for the starting location.
     * @param x2 The x coordinate for the end location.
     * @param y2 The y coordinate for the end location.
     * @param penColour The colour of the line.
     */
    public customLine(double x1, double y1, double x2, double y2, Color penColour){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.penColour = penColour;
    }

    /**
     * Returns the x1 coordinate of the <i>customLine</i> object.
     * @return The x1 coordinate of the line as a double.
     */
    @Override
    public double getX1() {
        return this.x1;
    }

    /**
     * Returns the y1 coordinate of the <i>customLine</i> object.
     * @return The y1 coordinate of the <i>customLine</i> object as a double.
     */
    @Override
    public double getY1() {
        return this.y1;
    }

    /**
     * Returns the x2 coordinate of the <i>customLine</i> object.
     * @return The x2 coordinate of the <i>customLine</i> object as a double.
     */
    @Override
    public double getX2() {
        return this.x2;
    }

    /**
     * Returns the y2 coordinate of the <i>customLine</i> object.
     * @return The y2 coordinate of the <i>customLine</i> object as a double.
     */
    @Override
    public double getY2() {
        return this.y2;
    }

    /**
     * Sets the x coordinate for the first point.
     * @param x1 The desired x coordinate for the first point.
     * @return Nothing.
     */
    public void setX1(double x1)
    {
        this.x1 = x1;
    }

    /**
     * Sets the y1 to a new value.
     * @param y1 The new value to be assigned.
     * @return Nothing.
     */
    public void setY1(double y1)
    {
        this.y1 = y1;
    }

    /**
     * Sets the x2 to a new value.
     * @param x2 The new value to be assigned.
     * @return Nothing.
     */
    public void setX2(double x2)
    {
        this.x2 = x2;
    }

    /**
     * Sets the y2 to a new value.
     * @param y2 The new value to be assigned.
     * @return Nothing.
     */
    public void setY2(double y2)
    {
        this.y2 = y2;
    }

    /**
     * Sets the colour of the pen for the outline.
     * @param colour the desired <i>Color</i>.
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Returns the type of shape as a <i>GUI.ShapeType</i>.
     * @return The type of shape as a <i>GUI.ShapeType</i>.
     */
    @Override
    public GUI.ShapeType getShapeType() {
        /**
         * Returns the SHAPE_TYPE for the line object
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    /**
     * Returns itself.
     * @return An instance of itself as a <i>Shape</i> object.
     */
    @Override
    public Shape getShape() {
        return this;
    }

    /**
     * Returns the colour of the outline for the <i>customLine</i> object.
     * @return The outline colour as a <i>Color</i> object for the <i>customLine</i> object.
     */
    @Override
    public Color getShapePenColour() {
        return this.penColour;
    }

    /**
     * Returns the colour of the fill for the <i>customLine</i> object.
     * @return The fill colour as a <i>Color</i> object for the <i>customLine</i> object.
     */
    @Override
    public Color getShapeFillColour() {
        return this.fillColour;
    }

    /**
     * Sets the fillColour for the <i>customLine</i> object.
     * @return The desired <i>Color</i>.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

}
