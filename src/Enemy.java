import java.awt.*;

public class Enemy {
    private int type = 1;
    private double speed = 1;
    private int positionX = 200;
    private int positionY = 200;
    private double distanceTraveled = 0;
    private int currentTrack = 0;
    private Color[] colors = {Color.cyan, Color.red, Color.blue, Color.green, Color.yellow, Color.pink};

    public Enemy(int type) {
        this.type = type;
        this.speed = (1 + type *0.5);
    }

    public void draw(Graphics g){
        g.setColor(colors[type]);
        g.fillArc(positionX, positionY,20 +3*type,20 +3*type, 0, 360);


    }

    public void update(){}

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
}
