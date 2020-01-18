import java.awt.*;
import java.awt.geom.Rectangle2D;

public class customRectangle extends Rectangle2D.Double implements ShapeControl {
    /**
     * <h1>customRectangle</h1>
     * <p>
     *     Describes a rectangle shape object by its location, width, height,
     *     fillColour and penColour.
     * </p>
     * @author Jessica Williams, William Daley, Jacob Kraut
     * @version 1.0
     * @since 2019-05-03
     */

    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.RECTANGLE;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    /**
     * Constructs a new <i>customRectangle</i> object at the specified coordinates
     * with the specified colour.
     * @param x1 The x coordinate for the top left corner.
     * @param y1 The y coordinate for the top left corner.
     * @param x2 The x coordinate for the bottom right corner.
     * @param y2 The y coordinate for the bottom right corner.
     * @param penColour The colour of the outline.
     * @param fillColour The inside colour of the shape.
     */
    public customRectangle(double x1, double y1, double x2, double y2, Color penColour, Color fillColour) {
        x = x1;
        y = y1;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = y2 - y1;
        this.width = x2 - x1;
        this.penColour = penColour;
        this.fillColour = fillColour;
    }

    /**
     * Returns the x coordinate for the <i>customRectangle</i> object.
     * @return The x coordinate of the first point.
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate for the <i>customRectangle</i> object.
     * @return The y coordinate of the first point.
     */
    @Override
    public double getY() {
        /**
         * Returns the Y coordinate for the rectangle object
         * @returns y
         */
        return this.y;
    }

    /**
     * Returns the x coordinate for the second point of the <i>customRectangle</i> object.
     * @return The x coordinate of the second point.
     */
    public double getX2()
    {
        return x1 + width;
    }

    /**
     * Returns the y coordinate for the second point of the <i>customRectangle</i> object.
     * @return The y coordinate of the second point.
     */
    public double getY2()
    {
        return y1 + height;
    }

    /**
     * Sets the x coordinate for the first point.
     * @param x1 The desired x coordinate for the first point.
     * @return Nothing.
     */
    public void setX1(double x1)
    {
        this.x = x1;
        this.x1 = x1;
    }

    /**
     * Sets the y coordinate for the first point.
     * @param y1 The desired y coordinate for the first point.
     * @return Nothing.
     */
    public void setY1(double y1)
    {
        this.y = y1;
        this.y1 = y1;
    }

    /**
     * Sets the x coordinate for the first point.
     * @param x2 The desired x coordinate for the second point.
     * @return Nothing.
     */
    public void setX2(double x2)
    {
        this.width = x2 - x1;
        this.x2 = x2;
    }

    /**
     * Sets the y coordinate for the first point.
     * @param y2 The desired y coordinate for the second point.
     * @return Nothing.
     */
    public void setY2(double y2)
    {
        this.height = y2 - y1;
        this.y2 = y2;
    }

    /**
     * Sets the outline colour for the <i>customRectangle</i> object.
     * @param colour The desired outline colour for the <i>customRectangle</i>
     *               object.
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Sets the fill colour for the <i>customRectangle</i> object.
     * @param colour The desired fill colour for the <i>customRectangle</i> object.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    /**
     * Returns the SHAPE_TYPE for the <i>customRectangle</i> object.
     * @return The SHAPE_TYPE for the <i>customRectangle</i> object.
     */
    @Override
    public GUI.ShapeType getShapeType() {
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
     * Returns the colour of the outline for the <i>customRectangle</i> object.
     * @return The penColour as a <i>Color</i> object for the <i>customRectangle</i> object.
     */
    @Override
    public Color getShapePenColour() {
        return penColour;
    }

    /**
     * Returns the colour of the fill for the <i>customRectangle</i> object.
     * @return The fill colour as a <i>Color</i> object for the <i>customRectangle</i> object.
     */
    @Override
    public Color getShapeFillColour() {
        return fillColour;
    }
}

