import java.awt.*;
import java.util.ArrayList;

public class Map {
    private ArrayList <Track> map = new ArrayList <Track>();
    private int hp = 50;


    public Map(){
        //testa lodrätt och vågrätt.
        map.add(new Track(20,1000,600,600));
        map.add(new Track(1000, 1000, 600,800 ));
        map.add(new Track(1000,500,800,800));
        map.add(new Track(500,500,800,400));

        //testa diagonaler
       /* map.add(new Track(500, 700, 400, 200));
        map.add(new Track(700,900,200,400));
        map.add(new Track(900,700,400,600));
        map.add(new Track(700,500,600,400)); */
    }

    public Map(ArrayList<Track> map) {
        this.map = map;
    }

    public ArrayList<Enemy> move(ArrayList <Enemy> enemies){
        for (int i = 0; i< enemies.size(); i++){
            //flytta på fienden
            enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() + enemies.get(i).getSpeed());
            //ändra track om det är slut på nuvarande track
            if (enemies.get(i).getDistanceTraveled() > map.get(enemies.get(i).getCurrentTrack()).getLength()) {
                enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() - map.get(enemies.get(i).getCurrentTrack()).getLength());
                enemies.get(i).setCurrentTrack(enemies.get(i).getCurrentTrack() + 1);
                //om det inte finns mer track i mappen tar spelaren skada och fienden försvinner.
                if (enemies.get(i).getCurrentTrack() +1 > map.size()) {
                    hp -= enemies.get(i).getType();
                    enemies.remove(i);
                    i--;
                    System.out.println(hp);
                    continue;
                }
            }
          //  System.out.println(map.get(enemies.get(i).getCurrentTrack()).getAngle());

            //flytta fienden i x led
            if (map.get(enemies.get(i).getCurrentTrack()).getDx() >=0)
            enemies.get(i).setPositionX((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartX()+ enemies.get(i).getDistanceTraveled()* Math.cos(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
            else  enemies.get(i).setPositionX((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartX()- enemies.get(i).getDistanceTraveled()* Math.cos(map.get(enemies.get(i).getCurrentTrack()).getAngle())));

            // flytta fienden i y led
            if (map.get(enemies.get(i).getCurrentTrack()).getDy() <=0)
            enemies.get(i).setPositionY((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartY()- enemies.get(i).getDistanceTraveled()* Math.sin(Math.abs(map.get(enemies.get(i).getCurrentTrack()).getAngle()))));
            else enemies.get(i).setPositionY((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartY()+ enemies.get(i).getDistanceTraveled()* Math.sin(Math.abs(map.get(enemies.get(i).getCurrentTrack()).getAngle()))));

    //        System.out.println(enemies.get(i).getPositionX());
      //      System.out.println(enemies.get(i).getPositionY());
           // System.out.println(map.get(enemies.get(i).getCurrentTrack()).getAngle() + " angel");
        }
        return (enemies);
    }

    public void draw(Graphics g){
        for (int i = 0; i < map.size(); i++)
            map.get(i).draw(g);
    }
    public boolean mapTowerCollision(ArrayList <Point> points){
        int xMin;
        int xMax;
        int yMin;
        int yMax;
        for (int i = 0; i < map.size(); i++) {
            for (int x = 0; x < points.size(); x++) {
                if (points.get(x).getPositionX() > map.get(i).getxMin() // x punkt längst till vänster
                        && points.get(x).getPositionX() < map.get(i).getxMax() // x punkt längst till höger
                        && points.get(x).getPositionY() > map.get(i).getyMin()// y punkt längst ner
                        && points.get(x).getPositionY() < map.get(i).getyMax()) {  // y punkt högst upp
                    return true;
                }
            }
        }
        return false;
    }
}
