import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Effect extends Actor
{
    protected int duration;
    protected int fadeOutRate;
    protected int fadeInRate;
    protected boolean fadeIn = true;
    protected boolean fadeOut = false;
    protected GreenfootImage image;
    protected SimpleTimer fade = new SimpleTimer();
    public Effect(int duration, int fadeInRate, int fadeOutRate, GreenfootImage image){
        this.duration = duration;
        this.fadeOutRate = fadeOutRate;
        this.fadeInRate = fadeInRate;
        this.image = image;
        image.setTransparency(0);
        setImage(image);
        fade.mark();
    }
    //these methods don't work if called from subclasses
    protected void fadeIn(GreenfootImage image){
        //fades in with the given rate
        if(fadeIn && image.getTransparency() < 255){
            image.setTransparency(image.getTransparency() + fadeInRate);
        }
    }
    protected void fadeOut(GreenfootImage image){
        //determine when to start fading out
        if(fade.millisElapsed() > duration){
            fadeIn = false;
            fadeOut = true;
        }
        if(fadeOut && image.getTransparency() > 0){
            image.setTransparency(image.getTransparency() - fadeOutRate);
        }
        //all the effects on the traffic cleared and removed after faded away
        if(image.getTransparency() < 1){
            VehicleWorld.slowDown = 0;
            getWorld().removeObject(this);
        }
    }
    public void act()
    {
    }
}
