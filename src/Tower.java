import java.awt.*;
import java.util.ArrayList;

public class Tower {
    private int diameter = 20;
    private int positionX = 200;
    private int positionY = 200;
    private int range = 60;
    private boolean collision = true;
    private int[] center = new  int[2];
    private Enemy target = null;


    private ArrayList<Point> punkter = new ArrayList<Point>();
    private ArrayList<Point> rangeCircle = new ArrayList<Point>();

    public Tower() {
    }

    public Tower(int diameter, int positionX, int positionY, int range) {
        this.diameter = diameter;
        this.positionX = positionX;
        this.positionY = positionY;
        this.range = range;
        generateCenter();
        generateRange();

    }
    public void generateCenter(){ // centerpunkten av tornet.
        center[0] = positionX + diameter/2;
        center[1] = positionY + diameter/2;
    }

    public void generatePoints() { // gör ett oktagon som cirkeln använder för att kollidera med andra objekt.
        punkter.add(new Point(0, 0));
        punkter.get(0).generateCirclePoints(8, punkter, diameter, positionX, positionY);
    }

    public void generateRange(){ // Gör en dekahexagon som används för att kolla om det finns fiender tillräckligt nära för att attakera.
        rangeCircle.add(new Point(0,0));
        rangeCircle.get(0).generateCirclePoints(16,rangeCircle,range,center[0] - range/2,center[1] - range/2);
    }

    public void placingTower(Graphics g, int[] mouse) { // Skapa ett nytt torn.
        positionX = mouse[0] - 2 * diameter /3 +2;
        positionY = mouse[1] - diameter -2;
        generatePoints();
        generateCenter();
        generateRange();

        if (!collision) {
            draw(g);
        } else {
            draw(g);
            int[] x = new int[]{punkter.get(1).getPositionX(), punkter.get(3).getPositionX(), punkter.get(5).getPositionX(), punkter.get(7).getPositionX()};
            int[] y = new int[]{punkter.get(1).getPositionY(), punkter.get(3).getPositionY(), punkter.get(5).getPositionY(), punkter.get(7).getPositionY()};
            g.setColor(Color.red);
            g.fillPolygon(x, y, 4);
        }
        g.setColor(Color.gray);
        for (int i = 0; i < rangeCircle.size(); i++) {
            g.fillRect(rangeCircle.get(i).getPositionX(), rangeCircle.get(i).getPositionY(), 4, 4);
        }

    }

    public boolean checkCollision(Map map) { // Kollision med kartan.
        collision = map.mapTowerCollision(punkter);
        return collision;
    }

    public void draw(Graphics g) {
        //g.setColor(color);
        g.fillArc(positionX, positionY, diameter, diameter, 0, 360);

        // Debug code
        /* g.setColor(Color.red);
        g.fillRect(getPositionX(), getPositionY(), 2, 2);
        g.drawRect(positionX,positionY,diameter,diameter);
        g.setColor(Color.cyan);
        for (int i = 0; i < getPunkter().size(); i++) {
            g.fillRect(getPunkter().get(i).getPositionX(), getPunkter().get(i).getPositionY(), 2, 2);
        }
        */
        /*
        g.setColor(Color.red);
        System.out.println(rangeCircle.size());
        for (int i = 0; i < rangeCircle.size(); i++){
            g.fillRect(rangeCircle.get(i).getPositionX(), rangeCircle.get(i).getPositionY(),4,4);
            System.out.println(rangeCircle.get(i).getPositionX() + " | " + rangeCircle.get(i).getPositionY());
        } */
        /* Center of circle.
        g.setColor(Color.red);
        g.fillRect(getCenter()[0],getCenter()[1],4,4);
        */
    }

    public void towerTowerCollision(ArrayList<Tower> towers) { // Om ett torn kolliderar med ett annat torn.
        for (int i = 0; i < towers.size(); i++) {
            for (int z = 0; z < punkter.size(); z++) {
                if (punkter.get(z).getPositionX() > towers.get(i).positionX
                        && punkter.get(z).getPositionX() < towers.get(i).positionX + towers.get(i).diameter
                        && punkter.get(z).getPositionY() > towers.get(i).positionY
                        && punkter.get(z).getPositionY() < towers.get(i).positionY + towers.get(i).diameter) {
                    collision = true;
                    return;
                }
            }
        }
        collision = false;
    }

    public void enemyInRange(ArrayList <Enemy> enemies, ArrayList <Projectile> projectiles){ // bestämmer om det finns en fiende tillräckligt när för att attackera och gör fienden som ska attackera den som är närmast slutet av banan.
        for (int i = 0; i < enemies.size(); i++) {
            if (rangeCircle.get(13).getPositionX() > enemies.get(i).getPositionX() // Kanske kommer att behövas ändras. Funktionen antar att det är en dekahexagon som används för range.
                    && rangeCircle.get(6).getPositionX() < enemies.get(i).getPositionX() + enemies.get(i).getDiameter()
                    && rangeCircle.get(13).getPositionY() > enemies.get(i).getPositionY()
                    && rangeCircle.get(6).getPositionY() < enemies.get(i).getPositionY() + enemies.get(i).getDiameter()){
                try{
                    if (enemies.get(i).getCurrentTrack() >= target.getCurrentTrack()){
                        if (enemies.get(i).getDistanceTraveled() > target.getDistanceTraveled()){
                            target = enemies.get(i);
                            continue;
                        }
                    }
                }catch (Exception e){
                    target = enemies.get(i);
                    continue;
                }
            }
            for (int z = 0; z < rangeCircle.size(); z++) {
                if (rangeCircle.get(z).getPositionX() > enemies.get(i).getPositionX()
                        && rangeCircle.get(z).getPositionX() < enemies.get(i).getPositionX() + enemies.get(i).getDiameter()
                        && rangeCircle.get(z).getPositionY() > enemies.get(i).getPositionY()
                        && rangeCircle.get(z).getPositionY() < enemies.get(i).getPositionY() + enemies.get(i).getDiameter()) {
                    try{
                        if (enemies.get(i).getCurrentTrack() >= target.getCurrentTrack()){
                            if (enemies.get(i).getDistanceTraveled() > target.getDistanceTraveled()){
                                target = enemies.get(i);
                            }
                        }
                    }catch (Exception e){
                        target = enemies.get(i);
                    }
                    break;
                }
                System.out.println(rangeCircle.get(z).getPositionX() + " | " + rangeCircle.get(z).getPositionY() + " | " + z);
            }

        }
        //collision = false;
    }

    public int getRange() {
        return range;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ArrayList<Point> getPunkter() {
        return punkter;
    }

    public boolean isCollision() {
        return collision;
    }

    public ArrayList<Point> getRangeCircle() {
        return rangeCircle;
    }

    public int[] getCenter() {
        return center;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }
}
