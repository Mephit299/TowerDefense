import java.util.ArrayList;

public class Point {
    private int positionX;
    private int positionY;
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    public Point(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public ArrayList <Point> generateCirclePoints(int n, ArrayList <Point> punkter, int diameter, int x, int y){ // Skapa en n-hörning med n punkter.
        punkter.removeAll(punkter);
        for (int i = 0; i < n; i++){
            punkter.add(new Point((int) (x + diameter/2 + diameter/2 * Math.cos(i*Math.PI/(n/2))), (int) (y + diameter/2 - diameter/2* Math.sin(i*Math.PI/(n/2)))));
            //System.out.println(punkter.get(i).positionX);
            //System.out.println(punkter.get(i).positionY);
        }
        return punkter;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
