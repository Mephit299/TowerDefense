import java.awt.*;

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

    }

    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillPolygon(shape);
        g.fillArc(startX- trackWidth,startY-trackWidth,trackWidth*2,trackWidth*2,0,360);
        g.fillArc(endX- trackWidth,  endY - trackWidth,trackWidth*2,trackWidth*2,0,360);

    }

    private void formFigure(){

    //    int[] xPoints = {startX, startX, endX, endX};
      //  int[] yPoints = {startY - trackWidth, startY + trackWidth, endY + trackWidth, endY -trackWidth};
        int[] xPoints = {(int) (startX - trackWidth * Math.cos(angle + Math.PI/2)), (int) (startX + trackWidth * Math.cos(angle + Math.PI/2)), (int) (endX + trackWidth * Math.cos(angle + Math.PI/2)), (int) (endX - trackWidth * Math.cos(angle + Math.PI/2))};
        int[] yPoints = {(int) (startY - trackWidth * Math.sin(angle + Math.PI/2)), (int) (startY + trackWidth * Math.sin(angle + Math.PI/2)), (int) (endY + trackWidth * Math.sin(angle + Math.PI/2)), (int) (endY - trackWidth * Math.sin(angle + Math.PI/2))};
        shape.xpoints = xPoints;
        shape.ypoints = yPoints;
        shape.npoints = 4;
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
}
