import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Effect extends Actor
{
    private SimpleTimer fade = new SimpleTimer();
    public Effect(int duration){
        fade.mark();
    }
    protected GreenfootImage fadeIn(GreenfootImage image, int fadeInRate){
        image.setTransparency(0 + fadeInRate);
        return image;
    }
    public void act()
    {
        // Add your action code here.
    }
}
