import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lightning here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        if(image.getTransparency() < 20){
            VehicleWorld.slowDown = 0;
        }
        setImage(image);
    }
}
