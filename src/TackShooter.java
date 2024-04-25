import java.awt.*;
import java.util.ArrayList;

public class TackShooter extends Tower{


    // select tower with r key
    public TackShooter (){
        super(40,530,620,200);

    }

    public void draw (Graphics g){
        g.setColor(Color.pink);
        super.draw(g);
        g.setColor(Color.gray);
        g.fillArc(super.getPositionX()+5,super.getPositionY()+5,30,30,0,360);
    }

    public void enemyInRange(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles) {
        super.enemyInRange(enemies, projectiles);
            if (getTarget() != null){
                projectiles.add(new Projectile(getCenter()[0], getCenter()[0] + 10, getCenter()[1], getCenter()[1], 5 ));
                setTarget(null);
                System.out.println(projectiles.size());
            }

    }
}
