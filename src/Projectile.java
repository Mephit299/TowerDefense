import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Track{

    private int pierce = 1;
    private int dmg = 1;
    private int speed = 3;
    double direction = 0;
    private double distanceTraveled = 0;
    private double maxDistance = 200;
    private int orgStartX;
    private int orgStartY;
    private int orgEndX;
    private int orgEndY;

    public Projectile(int startX, int endX, int startY, int endY, int width){
        super(startX, endX, startY, endY, width);
        this.orgStartX = startX;
        this.orgEndX = endX;
        this.orgStartY = startY;
        this.orgEndY = endY;
    }

    public void update(){
        distanceTraveled += speed;
        this.setStartX(this.orgStartX + (int) Math.round( distanceTraveled * Math.cos(direction)));
        this.setEndX(this.orgEndX + (int) Math.round( distanceTraveled * Math.cos(direction)));
        this.setStartY(this.orgStartY + (int) Math.round( distanceTraveled * Math.sin(direction)));
        this.setEndY(this.orgEndY + (int) Math.round( distanceTraveled * Math.sin(direction)));
        formFigure();
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillPolygon(getShape());
    }

    public Boolean removeProjectile(){
        boolean remove = false;
        if (distanceTraveled >= maxDistance){
            remove = true;
        }
        return remove;
    }

}
