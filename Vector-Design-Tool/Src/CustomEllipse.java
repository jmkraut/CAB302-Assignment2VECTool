import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomEllipse extends Ellipse2D.Double implements ShapeControl {
    /**
     * <h1>CustomEllipse</h1>
     * <p>
     *     Describes a customEllipse shape object by its location, outline
     *     colour and fill colour.
     * </p>
     * @author Jessica Williams, William Daley, Jacob Kraut
     * @version 1.0
     * @since 2019-05-03
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.ELLIPSE;

    private double x1;
    private double y1;
    private double x2;
    private double y2;


    private Color fillColour;
    private Color penColour;

    /**
     * Constructs a <i>CustomEllipse</i> object with at the specified location
     * and colours.
     * @param x1 The x coordinate of the first point.
     * @param y1 The y coordinate of the first point.
     * @param x2 The x coordinate of the second point.
     * @param y2 The y coordinate of the second point.
     * Color penColour, Color fillColour
     */
    public CustomEllipse(double x1, double y1, double x2, double y2, Color penColour, Color fillColour) {
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
     * Returns the x coordinate for the <i>CustomEllipse</i> object.
     * @return The x coordinate of the first point.
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate for the  <i>CustomEllipse</i> object.
     * @return The y coordinate of the first point.
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Returns the x coordinate for the  <i>CustomEllipse</i> object.
     * @return The x coordinate of the second point.
     */
    public double getX2()
    {
        return x2;
    }

    /**
     * Returns the y coordinate for the  <i>CustomEllipse</i> object.
     * @return The y coordinate of the second point.
     */
    public double getY2()
    {
        return y2;
    }

    /**
     * Sets the x coordinate for the first point.
     * @param x1 The desired x coordinate for the first point.
     * @return Nothing.
     */
    public void setX1(double x1)
    {
        this.x1 = x1;
        this.x = x1;
    }

    /**
     * Sets the y coordinate for the first point.
     * @param y1 The desired y coordinate for the first point.
     * @return Nothing.
     */
    public void setY1(double y1)
    {
        this.y1 = y1;
        this.y = y1;
    }

    /**
     * Sets the x2 coordinate for the first point.
     * @param x2 The desired x2 coordinate for the first point.
     * @return Nothing.
     */
    public void setX2(double x2)
    {
        this.x2 = x2;
        this.width = this.x2 - this.x1;
    }

    /**
     * Sets the y2 coordinate for the first point.
     * @param y2 The desired y2 coordinate for the first point.
     * @return Nothing.
     */
    public void setY2(double y2)
    {
        this.y2 = y2;
        this.height = this.y2 - this.y1;
    }

    /**
     * Returns the width of the CustomEllipse object.
     * @return The width of the CustomEllipse object.
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the CustomEllipse object.
     * @return The height of the CustomEllipse object.
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the outline colour for the <i>CustomEllipse</i> object.
     * @param colour The desired penColour for the <i>CustomEllipse</i> object.
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Sets the fillColour for the <i>CustomEllipse</i> object.
     * @param colour The desired fillColour for the <i>CustomEllipse</i> object.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    /**
     * Returns the SHAPE_TYPE for the <i>CustomEllipse</i> object.
     * @return SHAPE_TYPE The SHAPE_TYPE for the <i>CustomEllipse</i> object.
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
     * Returns the colour of the outline for the <i>CustomEllipse</i> object.
     * @return The outline colour as a <i>Color</i> object for the
     * <i>CustomEllipse</i> object.
     */
    @Override
    public Color getShapePenColour() {
        return penColour;
    }

    /**
     * Returns the colour of the fill for the <i>CustomEllipse</i> object.
     * @return The fill colour as a <i>Color</i> object for the <i>CustomEllipse</i> object.
     */
    @Override
    public Color getShapeFillColour() {
        return fillColour;
    }
}

