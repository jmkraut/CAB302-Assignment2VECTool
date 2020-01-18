import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <h1>IO</h1>
 * <p>
 * The IO class controls the importing and exporting
 * of .vec files.
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public class IO {
    enum ioOptions{
        save,
        load,
    }

    private Container parentContainer;
    Canvas drawingCanvas;
    private File fileSelected;

    /**
     * Constructs an IO object given the parent container and the drawing Canvas.
     * @param parentContainer The parent container is used to host the file
     *                        chooser window
     * @param drawingCanvas The drawing Canvas is used to extract all the
     *                      information needed to generate the .vec file
     */
    public IO(Container parentContainer, Canvas drawingCanvas)
    {
        this.parentContainer = parentContainer;
        this.drawingCanvas = drawingCanvas;
    }

    private Boolean promptUserToSelectFile(ioOptions options)
    {
        final JFileChooser fileChooser = new JFileChooser();
        int returnVal;

        switch (options){
            case load:
                returnVal = fileChooser.showOpenDialog(parentContainer);
                break;
            case save:
                returnVal = fileChooser.showSaveDialog(parentContainer);
                break;
            default:
                returnVal =  JFileChooser.CANCEL_OPTION;
                break;
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileSelected = fileChooser.getSelectedFile();
            return true;
        }
        return false;
    }

    /**
     * This method prompts the user to select the file they would like to save
     * to. The .vec file is then generated
     * @param shapes A list of the shapes as ShapeControl objects that will be
     *               written to the .vec file.
     */
    public void saveImage(List<ShapeControl> shapes)
    {
        Boolean fileChosenSuccess = promptUserToSelectFile(IO.ioOptions.save);
        if (fileChosenSuccess) {
            String outputString = formatShapeControlListToString(shapes);
            try {
                writeStringToFile(outputString);
            } catch (IOException e) {
                displaySaveError();
            }
        }
    }

    private String formatShapeControlListToString(List<ShapeControl> shapes){
        StringBuilder outputString = new StringBuilder();

        Color currentFillColour = null;
        Color currentPenColour = null;

        //Iterates over the list of Shape control objects
        for (int i = 0; i < shapes.size(); i++) {
            //Convert the ShapeControl to Shape type
            Shape shapeToWrite = shapes.get(i).getShape();

            Color shapeFillColour = shapes.get(i).getShapeFillColour();
            Color shapePenColour = Color.BLACK;
            if (shapes.get(i).getShapePenColour() != null)
                shapePenColour = shapes.get(i).getShapePenColour();

            if (shapeFillColour != currentFillColour)
            {
                currentFillColour = shapeFillColour;
                if (shapeFillColour == null)
                    outputString.append("FILL OFF\r\n");
                else
                    outputString.append("FILL " + rgbToHex(currentFillColour) + "\r\n");
            }

            if (shapePenColour != currentPenColour)
            {
                currentPenColour = shapePenColour;
                outputString.append("PEN " + rgbToHex(currentPenColour) + "\r\n");
            }

            switch (shapes.get(i).getShapeType())
            {
                case LINE:
                    outputString.append(convertLineShapeToString(shapeToWrite));
                    break;
                case POLYGON:
                    outputString.append(convertPolygonShapeToString(shapeToWrite));
                    break;
                case RECTANGLE:
                    outputString.append(convertRectangleShapeToString(shapeToWrite));
                    break;
                case PLOT:
                    outputString.append(convertPlotShapeToString(shapeToWrite));
                    break;
                case ELLIPSE:
                    outputString.append(convertEllipseShapeToString(shapeToWrite));
                    break;
                default:
                    displaySaveError();
                    break;
            }
            outputString.append("\r\n");
        }
        return outputString.toString();
    }

    private String rgbToHex(Color colorToConvert){
        if (colorToConvert == null)
            return null;
        String hex = "#"+Integer.toHexString(colorToConvert.getRGB()).substring(2);
        return hex;
    }

    private String convertLineShapeToString(Shape shapeToWrite)
    {
        customLine lineToWrite = (customLine) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the Canvas for vec format
        double x1 = lineToWrite.getX1() / drawingCanvas.getWidth();
        double y1 = lineToWrite.getY1() / drawingCanvas.getHeight();
        double x2 = lineToWrite.getX2() / drawingCanvas.getWidth();
        double y2 = lineToWrite.getY2() / drawingCanvas.getHeight();

        outputString += "LINE " + x1 + " " + y1 + " " + x2 + " " + y2;
        return outputString;
    }

    private String convertPolygonShapeToString(Shape shapeToWrite)
    {
        CustomPolygon polygonToWrite = (CustomPolygon) shapeToWrite;
        String outputString = "";

        double x = 0;
        double y = 0;

        outputString += "POLYGON ";
        for (int o = 0; o < polygonToWrite.npoints; o++) {
            //Encode absolute coordinates to fractions of the Canvas for vec format
            x = (double) polygonToWrite.xpoints[o] / drawingCanvas.getWidth();
            y = (double) polygonToWrite.ypoints[o] / drawingCanvas.getHeight();

            outputString += x + " " + y + " ";
        }
        return outputString;
    }

    private String convertRectangleShapeToString(Shape shapeToWrite)
    {
        customRectangle rectangleToWrite = (customRectangle) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the Canvas for vec format
        double x1 = rectangleToWrite.getX() / drawingCanvas.getWidth();
        double y1 = rectangleToWrite.getY() / drawingCanvas.getHeight();
        double x2 = (rectangleToWrite.getX() + rectangleToWrite.getWidth()) / drawingCanvas.getWidth();
        double y2 = (rectangleToWrite.getY() + rectangleToWrite.getHeight()) / drawingCanvas.getHeight();

        outputString += "RECTANGLE " +
                x1 + " " +
                y1 + " " +
                x2 + " " +

                y2;
        return outputString;

    }

    private String convertPlotShapeToString(Shape shapeToWrite)
    {
        customPlot plotToWrite = (customPlot) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the Canvas for vec format
        double x = plotToWrite.getX() / drawingCanvas.getWidth();
        double y = plotToWrite.getY() / drawingCanvas.getHeight();

        outputString += "PLOT " +
                x + " " +
                y;
        return outputString;
    }

    private String convertEllipseShapeToString(Shape shapeToWrite)
    {
        CustomEllipse ellipseToWrite = (CustomEllipse) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the Canvas for vec format
        double x1 = ellipseToWrite.getX() / drawingCanvas.getWidth();
        double y1 = ellipseToWrite.getY() / drawingCanvas.getHeight();
        double x2 = (ellipseToWrite.getX() + ellipseToWrite.getWidth()) / drawingCanvas.getWidth();
        double y2 = (ellipseToWrite.getY() + ellipseToWrite.getHeight()) / drawingCanvas.getHeight();

        outputString += "ELLIPSE " +
                x1 + " " +
                y1 + " " +
                x2 + " " +
                y2;
        return outputString;
    }

    private void writeStringToFile(String shapeData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileSelected));
        writer.write(shapeData);
        writer.close();
    }

    /**
     * This method prompts the user to select the file they would like to load
     * using the file chooser. It then formats and returns the data into a list
     * of Shape objects.
     * @return A list of shape objects that can then be rendered onto a Canvas
     */
    public List<ShapeControl> loadImage() {
        Boolean fileChosenSuccess = promptUserToSelectFile(IO.ioOptions.load);
        if (fileChosenSuccess) {
            List<ShapeControl> imageData;

            try {
                imageData = formatData(retrieveDataAsString());
                return imageData;
            } catch (IOException e) {
                displayLoadError();
            }
        }
        //Returns an empty list if loading fails or user cancels
        return new ArrayList<>();
    }

    private String retrieveDataAsString() throws IOException {
        BufferedReader thingThatsReadingTheFiles = new BufferedReader(new FileReader(fileSelected));
        StringBuilder sb = new StringBuilder();
        String line = thingThatsReadingTheFiles.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = thingThatsReadingTheFiles.readLine();
        }
        return sb.toString();
    }

    private List<ShapeControl> formatData(String dataString) {
        ArrayList<ArrayList<String>> dataAsStrings = splitString(dataString);
        List<ShapeControl> formattedShapes = getCommandsFromStrings(dataAsStrings);
        return formattedShapes;
    }

    private ArrayList<ArrayList<String>> splitString(String dataString)
    {
        ArrayList<ArrayList<String>> dataAsStrings = new ArrayList<>();

        //Separate shapes
        String[] dataSeperatedShapes = dataString.split("\r\n");

        //Separate arguments
        for (int i = 0; i < dataSeperatedShapes.length; i++) {
            ArrayList<String> temp = new ArrayList<>();
            String[] dataSeperatedbyArgs = dataSeperatedShapes[i].split(" ");
            for (int o = 0; o < dataSeperatedbyArgs.length; o++) {
                temp.add(dataSeperatedbyArgs[o]);
            }
            dataAsStrings.add(temp);
        }
        return dataAsStrings;
    }

    private List<ShapeControl> getCommandsFromStrings(ArrayList<ArrayList<String>> dataAsStrings)
    {
        List<ShapeControl> formattedShapes = new ArrayList<>();
        Color currentPenColour = Color.BLACK;
        Color currentFillColour = null;

        for (int shapeNo = 0; shapeNo < dataAsStrings.size(); shapeNo++) {
            Shape shapeBeingRead;
            String command = dataAsStrings.get(shapeNo).get(0);
            switch (command) {
                case "LINE":
                    formattedShapes.add(
                            convertStringsToLine(dataAsStrings.get(shapeNo), currentPenColour)
                    );
                    break;
                case "POLYGON":
                    formattedShapes.add(
                            convertStringsToPolygon(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "RECTANGLE":
                    formattedShapes.add(
                            convertStringsToRectangle(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "PLOT":
                    formattedShapes.add(
                            convertStringsToPlot(dataAsStrings.get(shapeNo), currentPenColour)
                    );
                    break;
                case "ELLIPSE":
                    formattedShapes.add(
                            convertStringsToEllipse(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "PEN":
                    currentPenColour = new Color(Color.decode(dataAsStrings.get(shapeNo).get(1)).getRGB());
                    break;
                case "FILL":
                    if (dataAsStrings.get(shapeNo).get(1).equals("OFF"))
                        currentFillColour = null;
                    else
                        currentFillColour = new Color(Color.decode(dataAsStrings.get(shapeNo).get(1)).getRGB());
                    break;
                default:
                    displayLoadError();
                    break;
            }
        }
        return formattedShapes;
    }

    private ShapeControl convertStringsToLine(List<String> lineDataAsStrings, Color currentPenColour)
    {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(lineDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(lineDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new customLine(coords[0],coords[1],coords[2],coords[3],currentPenColour);
    }

    private ShapeControl convertStringsToPolygon(List<String> polygonDataAsStrings, Color currentPenColour, Color currentFillColour)
    {
        List<double[]> coordinates = new ArrayList<>();
        for (int i = 1; i < polygonDataAsStrings.size()-1; i++)
        {
            try {
                double x = Double.parseDouble(polygonDataAsStrings.get(i)) * drawingCanvas.getWidth();
                double y = Double.parseDouble(polygonDataAsStrings.get(++i)) * drawingCanvas.getHeight();
                double[] coords = {x,y};
                coordinates.add(coords);
            }
            catch (Exception e)
            {
                displayLoadError();
            }
        }
        return new CustomPolygon(coordinates, currentPenColour, currentFillColour);
    }

    private ShapeControl convertStringsToRectangle(List<String> rectangleDataAsStrings, Color currentPenColour, Color currentFillColour) {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(rectangleDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(rectangleDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new customRectangle(coords[0],coords[1],coords[2],coords[3],currentPenColour, currentFillColour);
    }

    private ShapeControl convertStringsToPlot(List<String> plotDataAsStrings, Color currentPenColour)
    {
        double x = Double.parseDouble(plotDataAsStrings.get(1)) *  drawingCanvas.getWidth();
        double y = Double.parseDouble(plotDataAsStrings.get(2)) *  drawingCanvas.getHeight();
        return new customPlot(x, y, currentPenColour, 3, 3);
    }

    private ShapeControl convertStringsToEllipse(List<String> ellipseDataAsStrings, Color currentPenColour, Color currentFillColour)
    {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(ellipseDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(ellipseDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new CustomEllipse(coords[0],coords[1],coords[2],coords[3],currentPenColour, currentFillColour);
    }

    private void displayLoadError() {
        JOptionPane.showMessageDialog(null, "Sorry! There was an error loading this file.");
    }

    private void displaySaveError() {
        JOptionPane.showMessageDialog(null, "Sorry! There was an error loading this file");
    }
}