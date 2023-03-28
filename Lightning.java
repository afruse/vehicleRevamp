import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Lightning and thunder effect that would randomly strike throughout the simulation
 */
public class Lightning extends Effect
{
    public Lightning(){
        super(2000, 85, 15, new GreenfootImage("lightning.png"));
        GreenfootSound thunder = new GreenfootSound("Thunder-Mike_Koenig-315681025.wav");
        thunder.play();
        //fade.mark();
    }
    public void act()
    {
        //same as Boom, couldn't get methods to work despite being common code
        if(fade.millisElapsed() > duration){
            fadeIn = false;
            fadeOut = true;
        }
        if(fadeIn && image.getTransparency() < 255){
            image.setTransparency(image.getTransparency() + fadeInRate);
        }
        if(fadeOut && image.getTransparency() > 0){
            image.setTransparency(image.getTransparency() - fadeOutRate);
        }
        if(image.getTransparency() < 1){
            VehicleWorld.slowDown = 0;
            getWorld().removeObject(this);
        }
        setImage(image);
    }
}
