import java.awt.*;

public class Enemy { // Dän här klassen saknar mestadels av dess funktioner.
    private int type = 1; // hp
    private double speed = 1;
    private int positionX = 200;
    private int positionY = 200;
    private double distanceTraveled = 0;
    private int currentTrack = 0; // används i Map för att flytta fienden,
    private int diameter = 20;
    private Color[] colors = {Color.cyan, Color.red, Color.blue, Color.green, Color.yellow, Color.pink};

    public Enemy(int type) {
        this.type = type;
        this.speed = (1 + type *0.5);
        diameter += 3*type;
    }

    public void draw(Graphics g){
        try {
            g.setColor(colors[type]);
        }catch (Exception e) {
            g.setColor(colors[0]);
        }

        g.fillArc(positionX - diameter/2, positionY- diameter/2,diameter,diameter, 0, 360);


    }

    public int getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public int getCurrentTrack() {
        return currentTrack;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public void setCurrentTrack(int currentTrack) {
        this.currentTrack = currentTrack;
    }

    public int getDiameter() {
        return diameter;
    }
}
