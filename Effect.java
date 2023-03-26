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
    protected GreenfootImage fadeIn(GreenfootImage image, int fadeInRate){
        image.setTransparency(image.getTransparency() + fadeInRate);
        return image;
    }
    protected GreenfootImage fadeOut(GreenfootImage image, int fadeOutRate){
        image.setTransparency(image.getTransparency() - fadeOutRate);
        return image;
    }
    public void act()
    {
        /*if(fade.millisElapsed() < 1){
            fadeIn = true;
        }
        if(fade.millisElapsed() > duration){
            fadeOut = true;
        }
        if(fadeIn){
            image = fadeIn(image, 100);
        }
        if(fadeOut){
            image = fadeOut(image, 100);
        }*/
        image.setTransparency(255);
        setImage(image);
    }
}
