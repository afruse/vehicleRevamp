import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * The boom effect when a vehicle collides with another for too long and causes an explosion
 */
public class Boom extends Effect
{
    public Boom(){
        super(1000, 51, 51, new GreenfootImage("explosion_Boom_2.png"));
        GreenfootSound explosion = new GreenfootSound("Grenade Explosion-SoundBible.com-2100581469.wav");
        explosion.play();
    }
    //to check what Vehicles the Boom effect is touch
    //will remove all that it's touching
    private void isTouching(){
        ArrayList<Vehicle>objects = new ArrayList<Vehicle>(getIntersectingObjects(Vehicle.class));
        for(Vehicle v : objects){
            getWorld().removeObject(v);
        }
    }
    public void act()
    {
        isTouching();
        //determine when to start fading out
        if(fade.millisElapsed() > duration){
            fadeIn = false;
            fadeOut = true;
        }
        //fades in with the given rate
        if(fadeIn && image.getTransparency() < 255){
            image.setTransparency(image.getTransparency() + fadeInRate);
        }
        //fades out with given rate
        if(fadeOut && image.getTransparency() > 0){
            image.setTransparency(image.getTransparency() - fadeOutRate);
        }
        //all the effects on the traffic cleared and removed after faded away
        if(image.getTransparency() < 1){
            VehicleWorld.slowDown = 0;
            getWorld().removeObject(this);
        }
        setImage(image);
    }
}
