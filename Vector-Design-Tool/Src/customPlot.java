import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * <h1>customPlot</h1>
 * <p>
 *  Describes a plot object by x, y, width, height, fillColour, penColour
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public class customPlot extends Ellipse2D.Double implements ShapeControl {
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.PLOT;

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour;
    private Color penColour;

    /**
     * Constructs a plot object with an x, y, penColour, width, height
     * @param x The desired x location on the Canvas.
     * @param y The desired y location on the Canvas.
     * @param penColour The colour of the plot.
     */
    public customPlot(double x, double y, Color penColour, double width, double height) {
        this.x = x;
        this.y = y;
        this.penColour = penColour;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the x coordinate for the plot object.
     * @return The desired x coordinate for the plot object.
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x coordinate to a new value.
     * @param x The desired x coordinate to be assigned.
     * @return Nothing.
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Sets the y coordinate to a new value.
     * @param y The desired y coordinate to be assigned.
     * @return Nothing.
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Returns the y coordinate for the plot object
     * @return The desired y coordinate for the plot object.
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Returns the width of the plot object.
     * @return The width of the plot object.
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the plot object.
     * @return The height of the plot object.
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the penColour for the plot object.
     * @param colour  The desired penColour for the plot object.
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Sets the fillColour for the plot object shape.
     * @return The fillColour for the plot object shape.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    /**
     * Returns the SHAPE_TYPE of the plot object shape.
     * @return The SHAPE_TYPE of the plot object shape.
     */
    @Override
    public GUI.ShapeType getShapeType() {
        return SHAPE_TYPE;
    }

    /**
     * Returns the plot object
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
        return penColour;
    }
}

