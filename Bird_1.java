import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird_1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird_1 extends Crossers
{
    public Bird_1(int direction){
        super(direction);
        GreenfootImage image = getImage();
        image.scale(26, 23);
    }
    public void act()
    {
        // If there is a v
        if (awake){
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
