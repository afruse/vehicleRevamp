import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Since this is a highway these pedestrians are meant to be crazy people thus their weird jittery walk
 */
public class Pedestrian extends Crossers
{
    public Pedestrian(int direction) {
        super(direction);
    }

    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (awake){
            //code to get the pedestrians to move in weird way
            if(Greenfoot.getRandomNumber(3) == 0){
                move(-1);
            }
            else{
                move(Greenfoot.getRandomNumber(1));
            }
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (int)(speed*direction));
            }
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > 550){
                getWorld().removeObject(this);
            }
        }
    }
    
}
