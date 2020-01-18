import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <h1>Canvas</h1>
 * <p>
 * The Canvas class controls the drawing space for the
 * vector design tool.
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public class Canvas extends JPanel {
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;
    private Graphics2D drawController;

    private Color penColor = Color.BLACK;
    private Color fillColor = null;

    private Point origin;
    private Point destination;

    private GUI.ShapeType currentSelectedShape = GUI.ShapeType.LINE;
    private List<ShapeControl> shapesDrawn = new ArrayList<>();

    // When clicking and dragging, this shape will be displayed to show what
    // the result will be when the mouse is released.
    private ShapeControl displayShape;
    boolean shapeBeingPreviewed = false;

    private boolean gridOn = false;
    private int gridSize = 10;

    public List<ShapeControl> getShapesDrawn() {
        return shapesDrawn;
    }

    public void toggleGrid()
    {
        gridOn = !gridOn;
        repaint();
    }

    public void updateGrid(int newSize) {
        gridSize = newSize;
        repaint();
    }

    /**
     * Nested class that handles mouse inputs.<br>
     * Delivers 2 points that pass on x and y co-ordinates
     * to the shapes to be drawn.
     */
    private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            origin = e.getPoint();
            if (currentSelectedShape != GUI.ShapeType.POLYGON)
                shapeBeingPreviewed = true;
            displayPreviewShape(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            displayPreviewShape(e);
        }
        
        private void displayPreviewShape(MouseEvent e)
        {
            if (shapeBeingPreviewed) {
                destination = e.getPoint();
                double x1 = roundIntForGrid(origin.x);
                double y1 = roundIntForGrid(origin.y);
                double x2 = roundIntForGrid(destination.x);
                double y2 = roundIntForGrid(destination.y);
                switch(currentSelectedShape){
                    case RECTANGLE:
                        displayShape = addRectangleEllipse(x1, y1, x2, y2, GUI.ShapeType.RECTANGLE);
                        break;
                    case LINE:
                        displayShape = new customLine(x1, y1, x2, y2, penColor);
                        break;
                    case ELLIPSE:
                        displayShape = addRectangleEllipse(x1, y1, x2, y2, GUI.ShapeType.ELLIPSE);
                        break;
                    case PLOT:
                        displayShape = new customPlot(x2 - 2, y2 - 2, penColor, 4, 4);
                        break;
                }
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapeBeingPreviewed = false;
            addShape(e);
            repaint();
        }
    }

    /**
     * This method updates the coordinates of all the shapes based off how much
     * they need to expand/contract and in which direction.
     * @param widthFactor The new width as a decimal of the current width.
     *                    for example:
     *                                1   : no change
     *                                1.1 : expand width 10%
     *                                0.9 : shrink width 10%
     * @param heightFactor The new height as a decimal of the current height.
     *                     for example:
     *                                1   : no change
     *                                1.1 : expand height 10%
     *                                0.9 : shrink height 10%
     * @return Nothing.
     */
    public void resizeShapes(double widthFactor, double heightFactor)
    {
        for (int i = 0; i < shapesDrawn.size(); i++)
        {
            ShapeControl shape = shapesDrawn.get(i);
            switch (shapesDrawn.get(i).getShapeType())
            {
                case PLOT:
                    double x =((customPlot)shape).getX();
                    double y =((customPlot)shape).getY();

                    x *= widthFactor;
                    y *= heightFactor;

                    ((customPlot)shape).setX(x);
                    ((customPlot)shape).setY(y);
                    break;
                case LINE:
                    double lx1 =((customLine)shape).getX1();
                    double ly1 =((customLine)shape).getY1();
                    double lx2 =((customLine)shape).getX2();
                    double ly2 =((customLine)shape).getY2();

                    lx1 *= widthFactor;
                    ly1 *= heightFactor;
                    lx2 *= widthFactor;
                    ly2 *= heightFactor;

                    ((customLine)shape).setX1(lx1);
                    ((customLine)shape).setY1(ly1);
                    ((customLine)shape).setX2(lx2);
                    ((customLine)shape).setY2(ly2);
                    break;
                case RECTANGLE:
                    double rx1 =((customRectangle)shape).getX();
                    double ry1 =((customRectangle)shape).getY();
                    double rx2 =((customRectangle)shape).getX2();
                    double ry2 =((customRectangle)shape).getY2();

                    rx1 *= widthFactor;
                    ry1 *= heightFactor;
                    rx2 *= widthFactor;
                    ry2 *= heightFactor;

                    ((customRectangle)shape).setX1(rx1);
                    ((customRectangle)shape).setY1(ry1);
                    ((customRectangle)shape).setX2(rx2);
                    ((customRectangle)shape).setY2(ry2);
                    break;
                case ELLIPSE:
                    double ex1 =((CustomEllipse)shape).getX();
                    double ey1 =((CustomEllipse)shape).getY();
                    double ex2 =((CustomEllipse)shape).getX2();
                    double ey2 =((CustomEllipse)shape).getY2();

                    ex1 *= widthFactor;
                    ey1 *= heightFactor;
                    ex2 *= widthFactor;
                    ey2 *= heightFactor;

                    ((CustomEllipse)shape).setX1(ex1);
                    ((CustomEllipse)shape).setY1(ey1);
                    ((CustomEllipse)shape).setX2(ex2);
                    ((CustomEllipse)shape).setY2(ey2);
                    break;
                case POLYGON:
                    int[] XCoords = ((CustomPolygon)shape).getXCoordinates();
                    int[] YCoords = ((CustomPolygon)shape).getYCoordinates();

                    for (int z = 0; z < XCoords.length; z++)
                    {
                        XCoords[z] *= widthFactor;
                        YCoords[z] *= heightFactor;
                    }
                    ((CustomPolygon) shape).setXCoordinates(XCoords);
                    ((CustomPolygon) shape).setYCoordinates(YCoords);
                    break;
            }
        }
        repaint();
    }

    /**
     * Adds a shape of the currently selected type at the location of the cursor.
     * @param e The event passed from the mouse.
     * @return Nothing.
     */
    private void addShape(MouseEvent e)
    {
        destination = e.getPoint();

        if(origin != null){
            double x1 = roundIntForGrid(origin.x);
            double y1 = roundIntForGrid(origin.y);
            double x2 = roundIntForGrid(destination.x);
            double y2 = roundIntForGrid(destination.y);

            switch(currentSelectedShape){
                case RECTANGLE:
                    shapesDrawn.add(addRectangleEllipse(x1, y1, x2, y2, currentSelectedShape));
                    break;
                case LINE:
                    customLine Line = new customLine(x1, y1, x2, y2, penColor);
                    shapesDrawn.add(Line);
                    break;
                case ELLIPSE:
                    shapesDrawn.add(addRectangleEllipse(x1, y1, x2, y2, currentSelectedShape));
                    break;
                case PLOT:
                    customPlot plot = new customPlot(x2 - 2, y2 - 2, penColor, 4, 4);
                    shapesDrawn.add(plot);
                    break;
                case POLYGON:
                    addPolygon(x1, y1, x2, y2);
                    break;
            }
        }
        if (GUI.superKoolRainbowFunMode) {
            shapesDrawn.get(shapesDrawn.size() - 1).setShapeFillColour(randomColor());
            shapesDrawn.get(shapesDrawn.size() - 1).setShapePenColour(randomColor());
        }
    }

    public Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

    //Ellipse and Rectangle are both drawn the same so can be combined.
    private ShapeControl addRectangleEllipse(double x1, double y1, double x2, double y2, GUI.ShapeType shapeTypeToDraw) {
        //If y1 is greater, swap them so that y2 is always greater. This ensures that y1 is always the top value.
        if (y1 > y2){
            double yTemp = y1;
            y1 = y2;
            y2 = yTemp;
        }
        //If x1 is greater, swap them so that x2 is always greater. This ensures that x1 is always the top value.
        if (x1 > x2){
            double xTemp = x1;
            x1 = x2;
            x2 = xTemp;
        }
        switch (shapeTypeToDraw)
        {
            case RECTANGLE:
                customRectangle rectangle = new customRectangle(x1, y1, x2, y2, penColor, fillColor);
                return rectangle;
            case ELLIPSE:
                CustomEllipse ellipse = new CustomEllipse(x1, y1, x2, y2, penColor, fillColor);
                return ellipse;
        }
        return null;
    }
    private void addPolygon(double x1, double y1, double x2, double y2)
    {
        // If there is already a polygon started, add to it, otherwise, create one
        if (!shapesDrawn.isEmpty() && shapesDrawn.get(
                shapesDrawn.size() - 1).getShapeType() == GUI.ShapeType.POLYGON) {
            CustomPolygon polyInProgress =
                    (CustomPolygon) shapesDrawn.get(shapesDrawn.size() - 1).getShape();
            polyInProgress.addPoint((int) x2, (int) y2);

            //If any colour has changed, update it
            if (fillColor != polyInProgress.getShapeFillColour())
                polyInProgress.setShapeFillColour(fillColor);
            if (penColor != polyInProgress.getShapePenColour())
                polyInProgress.setShapePenColour(penColor);

            removeLastShape();
            shapesDrawn.add(polyInProgress);
        }
        else {
            //Creating a list of coordinates for the polygon
            double[] firstPoint = {x2,y2};
            List<double[]> polygonCoordinates = new ArrayList<>();
            polygonCoordinates.add(firstPoint);

            CustomPolygon polygon = new CustomPolygon(
                    polygonCoordinates, penColor, fillColor);
            shapesDrawn.add(polygon);
        }
    }

    //Rounds value to the grid size, allowing the shapes to snap to the grid.
    private double roundIntForGrid(int valueToRound)
    {
        if (gridOn)
            return Math.round(valueToRound / (double)gridSize) * gridSize;
        else
            return valueToRound;
    }

    /**
     * This method erases the shapes stored in <i>shapesDrawn</i> and replaces
     * it with the a new list of <i>Shape</i> objects.
     * @param newShapeList The list of <i>ShapeControl</i>s that will override the current list.
     * @return Nothing.
     */
    public void overrideCanvas(List<ShapeControl> newShapeList)
    {
        shapesDrawn.clear();
        for (int i = 0; i < newShapeList.size(); i++)
        {
            shapesDrawn.add(newShapeList.get(i));
        }
        repaint();
    }

    /**
     * Default Constructor.
     * Sets up the drawing area and enables mouse inputs.
     */
    public Canvas(){
        setDoubleBuffered(false);
        setBackground(DEFAULT_BACKGROUND_COLOUR);
        Mouse minnie = new Mouse();
        addMouseListener(minnie);
        addMouseMotionListener(minnie);
    }

    /**
     * Clears the Canvas. Removing any drawn shapes on the
     * Canvas.
     * @return Nothing.
     */
    public void clear(){
        shapesDrawn.clear();
        repaint();
    }


    /**
     * Paints all <i>ShapeControl</i> objects onto the Canvas.
     * @param g The graphics object that will draw te shapes.
     * @return Nothing.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawController = (Graphics2D) g;

        //Draws the drawn shapes
        for(ShapeControl shape : shapesDrawn) paintShapeToCanvas(shape);
        //Draws grid lines
        if (gridOn)
            for (ShapeControl line : getGridLineShapes()) paintShapeToCanvas(line);
         //Draws the preview shape
        if (shapeBeingPreviewed)
            paintShapeToCanvas(displayShape);
    }

    //Generates the line objects that show the grid on the Canvas
    private List<ShapeControl> getGridLineShapes() {
        int width = this.getWidth();
        int height = this.getHeight();

        int numOfVerticalGridLines = width/gridSize;
        int numOfHorizontalGridLines = height/gridSize;

        List<ShapeControl> gridLines = new ArrayList<>();

        for (int i = 1; i < numOfHorizontalGridLines+1; i++)
            gridLines.add(new customLine(0,i*gridSize, width, i*gridSize, Color.BLACK));
        for (int i = 1; i < numOfVerticalGridLines+1; i++)
            gridLines.add(new customLine(i*gridSize,0, i*gridSize,height, Color.BLACK));
        return gridLines;

    }

    private void paintShapeToCanvas(ShapeControl shapeToDraw)
    {
        if(shapeToDraw.getShapeFillColour() != null){
            drawController.setPaint(shapeToDraw.getShapeFillColour());
            drawController.fill(shapeToDraw);
        }

        drawController.draw(shapeToDraw);

        if(shapeToDraw.getShapePenColour() != null){
            drawController.setPaint(shapeToDraw.getShapePenColour());
        }
        drawController.draw(shapeToDraw);
    }

    /**
     * Removes the Last Shape drawn on the Canvas.
     * @return Nothing.
     */
    public void removeLastShape() {
        shapesDrawn.remove(shapesDrawn.size() - 1);
    }

    /**
     * Checks if the last shape drawn is of the specified type.
     * @param shapeType The shape type being checked for.
     * @return True if the last shape is the specified type or not.
     */
    public boolean checkLastShape(GUI.ShapeType shapeType) {
        ShapeControl shapeBeingChecked = shapesDrawn.get(shapesDrawn.size()-1);
        GUI.ShapeType typeOfShapeBeingChecked = shapeBeingChecked.getShapeType();

        if (typeOfShapeBeingChecked == GUI.ShapeType.POLYGON ||
                typeOfShapeBeingChecked == GUI.ShapeType.RECTANGLE ||
                typeOfShapeBeingChecked == GUI.ShapeType.ELLIPSE)
        {
            if (typeOfShapeBeingChecked == shapeType && shapeBeingChecked.getShapeFillColour() == fillColor &&
                    shapeBeingChecked.getShapePenColour() == penColor)
            {
                return true;
            }
        }
        else if (typeOfShapeBeingChecked == GUI.ShapeType.LINE ||
                typeOfShapeBeingChecked == GUI.ShapeType.PLOT)
        {
            if (typeOfShapeBeingChecked == shapeType && shapeBeingChecked.getShapePenColour() == penColor)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the current pen colour in use by the Canvas as a <i>Color</i> object.
     * @return The current pen colour in use by the Canvas as a <i>Color</i> object.
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     * Sets the Pen Color in use by the Canvas to change shape border color.
     * @param penColor The desired <i>Color</i>.
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     * Sets the currently selected shape.
     * @param currentSelectedShape The shape to be selected.
     * @return Nothing.
     */
    public void setCurrentSelectedShape(GUI.ShapeType currentSelectedShape) {
        this.currentSelectedShape = currentSelectedShape;
    }

    /**
     * Returns the shape that is currently selected.
     * @return The shape that is currently selected.
     */
    public GUI.ShapeType getCurrentSelectedShape() {
        return currentSelectedShape;
    }

    /**
     * Returns the current fill color being used by the Canvas to fill shapes.
     * @return The current fill color.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the current fill Color for shapes being drawn on the Canvas.
     * @param fillColor The desired <i>Color</i> for the fill.
     * @return Nothing.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}