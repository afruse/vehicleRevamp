import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaneChange here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaneChange extends Actor
{
    private static GreenfootImage image;
    private static boolean visible;
    public int height;
    public int width;
    public LaneChange (int laneHeight)
    {
        this.height = (int)(laneHeight * 0.75);
        width = 250;
        // set this to true to see the Spawners - might help with understanding of how this works:
        visible = true;
        image = new GreenfootImage (width, height);
        if(visible){
            image.setColor(VehicleSpawner.TRANSPARENT_RED);
            image.fillRect(0, 0, width - 1, height - 1);
        }
        setImage(image);
        
    }
    public boolean touching(){
        return this.isTouching (Car.class) || this.isTouching (Bus.class) || this.isTouching (Ambulance.class) || this.isTouching (User.class);
    }
    public void act()
    {
        Actor actor = getOneIntersectingObject(Actor.class);
        if (actor != null) {
            getWorld().removeObject(this);
            //if you want to remove an object from an actor class you have to use getWorld().removeObject;
        }
    }
}
