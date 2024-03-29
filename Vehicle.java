import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected GreenfootSound honk;
    protected SimpleTimer timer;
    protected boolean time = true;
    protected abstract boolean checkHitWalker();

    public Vehicle (VehicleSpawner origin) {
        this.origin = origin;
        moving = true;
        honk = new GreenfootSound("Horn Honk-SoundBible.com-1162546405.wav");
        if (origin.facesRightward()){
            direction = 1;
            
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
        // sets the timer
        timer = new SimpleTimer();
    }
    
    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    /**
     * A method used by all Vehicles to check if they are at the edge.
     * 
     * Note that this World is set to unbounded (The World's super class is (int, int, int, FALSE) which means
     * that objects should not be stopped from leaving the World. However, this introduces a challenge as there
     * is the potential for objects to disappear off-screen but still be fully acting and thus wasting resources
     * and affecting the simulation even though they are not visible.
     */
    protected boolean checkEdge() {
        if (direction == 1)
        { // if moving right, check 200 pixels to the right (above max X)
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } 
        else 
        { // if moving left, check 200 pixels to the left (negative values)
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if(ahead == null && (maxSpeed - VehicleWorld.slowDown) > 0){
            speed = maxSpeed - VehicleWorld.slowDown;
        }
        // added extra if statement and conditions to make sure cars don't go backwards
        else if (ahead == null && (maxSpeed - VehicleWorld.slowDown) < 0)
        {
            speed = maxSpeed;
        } else {
            //when slower car in front honk sounds play
            honk.play();
            speed = ahead.getSpeed() - 1;
        }
        //starts a counter can only run if statement once
        if(ahead != null && time){
            timer.mark();
            time = false;
        }
        move ((speed) * direction);
    }
    //to check if Vehicle has been colliding for more than .8 seconds
    //if colliding for that long Vehicle will explode
    public void boom(){
        if(time == false && timer.millisElapsed() > 800 && this.isTouching(Vehicle.class)){
            getWorld().addObject(new Boom(), getX(), getY());
        }
    }
    //forces all Vehicles to have giveWay() method
    //all vehicle move to the rightside of the map if ambulances come by
    protected abstract void giveWay();
    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed(){
        return speed;
    }
}
