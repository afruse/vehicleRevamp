import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Since this is a bird it can fly over vehicles if given enough time to react
 * meaning as long as it isn't hit head on it wont get knocked down
 */
public class Bird extends Crossers
{
    public Bird(int direction){
        super(direction);
        GreenfootImage image = getImage();
        image.scale(26, 23);
    }
    public void addedToWorld(World w){
        if(direction == -1){
            setRotation(180);
        }
    }
    public void act()
    {
        // If there is a v
        if (awake){
            /*if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (int)(speed*direction));
            }*/
            setLocation (getX(), getY() + (int)(speed*direction));
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > 550){
                getWorld().removeObject(this);
            }
        }
    }
}