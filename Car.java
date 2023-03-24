import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    private static boolean othersChanging = false;
    private boolean canCheckChange = true;
    private boolean canChangeNow = false;
    private boolean smoothChange = false;
    private int changeLocation;
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
    }

    public void act()
    {
        drive();
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if(ahead != null && !othersChanging && canCheckChange && (600-VehicleWorld.laneHeight*2) > getY()){
            VehicleWorld.change(getX(), getY());
            othersChanging = true;
            canCheckChange = false;
            canChangeNow = true;
        }
        if(VehicleWorld.ChangeLaneNow() && canChangeNow){
            smoothChange = true;
            changeLocation = getY() + VehicleWorld.laneHeight + 5;
            othersChanging = false;
            canChangeNow = false;
        }
        else{
            canChangeNow = false;
            othersChanging = false;
        }
        if(smoothChange && getY() < changeLocation){
            setLocation(getX(), getY() + 3);
        }
        else if (smoothChange && getY() >= changeLocation){
            smoothChange = false;
        }
        drive();
        checkHitWalker();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * When a Car hit's a Walker, it should knock it over
     */
    public boolean checkHitWalker () {
        Crossers p = (Crossers)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Crossers.class);
        
        if (p != null){
            p.knockDown();
            return true;
        }
        return false;
    }
}
