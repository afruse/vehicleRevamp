import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class User here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class User extends Vehicle
{
    /**
     * Act - do whatever the User wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public User(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
    }
    public void act()
    {
        drive(); 
        checkHitWalker();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    public boolean checkHitWalker () {
        Walker p = (Walker)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Walker.class);
        
        if (p != null){
            p.knockDown();
            return true;
        }
        return false;
    }
}
