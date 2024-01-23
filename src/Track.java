public class Track {
    private int startX = 200;
    private int startY = 200;
    private int endX = 400;
    private int endY = 400;
    private int dx;
    private int dy;
    private double length;
    private double angle;

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
