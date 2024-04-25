import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Track {
    private int startX = 200;
    private int startY = 200;
    private int endX = 400;
    private int endY = 400;
    private int dx;
    private int dy;
    private double length;
    private double angle;
    private int trackWidth = 20;
    private Polygon shape = new Polygon();
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;
    private ArrayList <Point> circle1Points = new ArrayList<Point>();
    private ArrayList <Point> circle2Points = new ArrayList<Point>();
    public Track (int startX, int endX, int startY, int endY){
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        dx = endX - startX;
        dy = endY - startY;
        try {
            angle = Math.atan((double) dy/dx);
        } catch (Exception e) {
            angle = Math.PI/2;
        }

        length = Math.sqrt(Math.pow(dx,2)+ Math.pow(dy,2));
        formFigure();
        xyMinMax();
        //System.out.println(xMin + " " + xMax + " " + yMin + " " + yMax);
    }

    public Track (int startX, int endX, int startY, int endY, int width){
        this.trackWidth = width;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        dx = endX - startX;
        dy = endY - startY;
        try {
            angle = Math.atan((double) dy/dx);
        } catch (Exception e) {
            angle = Math.PI/2;
        }

        length = Math.sqrt(Math.pow(dx,2)+ Math.pow(dy,2));
        formFigure();
        xyMinMax();
        //System.out.println(xMin + " " + xMax + " " + yMin + " " + yMax);
    }

    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillPolygon(shape);
        g.fillArc(startX- trackWidth,startY-trackWidth,trackWidth*2,trackWidth*2,0,360);
        g.fillArc(endX- trackWidth,  endY - trackWidth,trackWidth*2,trackWidth*2,0,360);
    }

    public void formFigure(){

    //    int[] xPoints = {startX, startX, endX, endX};
      //  int[] yPoints = {startY - trackWidth, startY + trackWidth, endY + trackWidth, endY -trackWidth};
        int[] xPoints = {(int) (startX - trackWidth * Math.cos(angle + Math.PI/2)), (int) (startX + trackWidth * Math.cos(angle + Math.PI/2)), (int) (endX + trackWidth * Math.cos(angle + Math.PI/2)), (int) (endX - trackWidth * Math.cos(angle + Math.PI/2))};
        int[] yPoints = {(int) (startY - trackWidth * Math.sin(angle + Math.PI/2)), (int) (startY + trackWidth * Math.sin(angle + Math.PI/2)), (int) (endY + trackWidth * Math.sin(angle + Math.PI/2)), (int) (endY - trackWidth * Math.sin(angle + Math.PI/2))};
        shape.xpoints = xPoints;
        shape.ypoints = yPoints;
        shape.npoints = 4;
        /*for (int i = 0; i < 4; i++)
            System.out.println("x " + xPoints[i] + "| y " + yPoints[i]); */
    }

    private void xyMinMax(){
        xMax = shape.xpoints[0];
        xMin = shape.xpoints[0];
        yMax = shape.ypoints[0];
        yMin = shape.ypoints[0];
        for (int i = 0; i < shape.xpoints.length; i++) {
            if (shape.xpoints[i] > xMax)
                xMax = shape.xpoints[i];
            if (shape.xpoints[i] < xMin)
                xMin = shape.xpoints[i];
            if (shape.ypoints[i] > yMax)
                yMax = shape.ypoints[i];
            if (shape.ypoints[i] < yMin)
                yMin = shape.ypoints[i];
        }
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public double getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }

    public Polygon getShape() {
        return shape;
    }

    public int getxMax() {
        return xMax;
    }

    public int getxMin() {
        return xMin;
    }

    public int getyMax() {
        return yMax;
    }

    public int getyMin() {
        return yMin;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}
