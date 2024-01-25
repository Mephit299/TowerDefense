import java.awt.*;
import java.util.ArrayList;

public class Map {
    private ArrayList <Track> map = new ArrayList <Track>();
    private int hp = 50;


    public Map(){
        map.add(new Track(20,1000,200,600));
     //   map.add(new Track(1000, 500, 600, 20));
    }

    public Map(ArrayList<Track> map) {
        this.map = map;
    }

    public ArrayList<Enemy> move(ArrayList <Enemy> enemies){
        for (int i = 0; i< enemies.size(); i++){
            enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() + enemies.get(i).getSpeed());
            if (enemies.get(i).getDistanceTraveled() > map.get(enemies.get(i).getCurrentTrack()).getLength()) {
                enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() - map.get(enemies.get(i).getCurrentTrack()).getLength());
                enemies.get(i).setCurrentTrack(enemies.get(i).getCurrentTrack() + 1);
                if (enemies.get(i).getCurrentTrack() +1 > map.size()) {
                    hp -= enemies.get(i).getType();
                    enemies.remove(i);
                    i--;
                    System.out.println(hp);
                    continue;
                }
            }
          //  System.out.println(map.get(enemies.get(i).getCurrentTrack()).getAngle());
            if (map.get(enemies.get(i).getCurrentTrack()).getDx() >=0)
            enemies.get(i).setPositionX((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartX()+ enemies.get(i).getDistanceTraveled()* Math.cos(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
            else  enemies.get(i).setPositionX((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartX()- enemies.get(i).getDistanceTraveled()* Math.cos(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
            if (map.get(enemies.get(i).getCurrentTrack()).getDy() >=0)
            enemies.get(i).setPositionY((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartY()+ enemies.get(i).getDistanceTraveled()* Math.sin(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
            else enemies.get(i).setPositionY((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartY()- enemies.get(i).getDistanceTraveled()* Math.sin(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
    //        System.out.println(enemies.get(i).getPositionX());
      //      System.out.println(enemies.get(i).getPositionY());

        }
        return (enemies);
    }

    public void draw(Graphics g){
        for (int i = 0; i < map.size(); i++)
            map.get(i).draw(g);
    }
}
