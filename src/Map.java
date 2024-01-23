import java.util.ArrayList;

public class Map {
    private int length = 0;
    private ArrayList <Track> map = new ArrayList <Track>();


    public Map(){
        map.add(new Track(0,1000,200,200));
    }

    public Map(ArrayList<Track> map) {
        this.map = map;
        for (int i =0; i < map.size(); i++)
            length += map.get(i).getLength();
    }

    public ArrayList<Enemy> move(ArrayList <Enemy> enemies){
        for (int i = 0; i< enemies.size(); i++){
            enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() + enemies.get(i).getSpeed());
            if (enemies.get(i).getDistanceTraveled() > map.get(enemies.get(i).getCurrentTrack()).getLength()) {
                enemies.get(i).setDistanceTraveled(enemies.get(i).getDistanceTraveled() - map.get(enemies.get(i).getCurrentTrack()).getLength());
                enemies.get(i).setCurrentTrack(enemies.get(i).getCurrentTrack() + 1);
            }
            System.out.println(map.get(enemies.get(i).getCurrentTrack()).getStartY()); //det här fungerar inte
            System.out.println(map.get(enemies.get(i).getCurrentTrack()).getStartX()+ enemies.get(i).getDistanceTraveled()* Math.sin(map.get(enemies.get(i).getCurrentTrack()).getAngle()));
            enemies.get(i).setPositionX((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartX()+ enemies.get(i).getDistanceTraveled()* Math.cos(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
            enemies.get(i).setPositionY((int) Math.round( map.get(enemies.get(i).getCurrentTrack()).getStartY()+ enemies.get(i).getDistanceTraveled()* Math.sin(map.get(enemies.get(i).getCurrentTrack()).getAngle())));
    //        System.out.println(enemies.get(i).getPositionX());
      //      System.out.println(enemies.get(i).getPositionY());

        }
        return (enemies);
    }
}
