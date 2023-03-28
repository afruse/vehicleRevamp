import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * High way safety patrol bus to cleanup up the highway and ensure minimal damage to crossers
 */
//private SimpleTimer time = new SimpleTimer();
public class Bus extends Vehicle
{
    private boolean stop = false;
    private SimpleTimer time = new SimpleTimer();
    private boolean canCheckChange = true;
    private boolean canChangeNow = false;
    private boolean smoothChange = false;
    private int changeLocation;
    private GreenfootSound busEngine;
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
        busEngine = new GreenfootSound("bus.wav");
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        boom();
        if(!busEngine.isPlaying()){
            busEngine.play();
        }
        giveWay();
        //drives if there aren't any crossers to pick up
        if(!stop){
            drive();
        }
        //if there is crossers to pick up it will stop for 1 second
        else if(stop && time.millisElapsed() > 1000){
            stop = false;
        }
        checkHitWalker();
        if (checkEdge()){
            busEngine.stop();
            getWorld().removeObject(this);
        }
        
    }
    public void giveWay(){
        //gets all the Ambulances in the area
        ArrayList<Ambulance>objects = new ArrayList<Ambulance>(getObjectsInRange(200, Ambulance.class));
        //if there is an Ambulance then the vehicle will move over to the right using code simular to the lane change
        if(objects.size() > 0){
            if((600-VehicleWorld.laneHeight*2) > getY()){
                VehicleWorld.change(getX(), getY());
                canChangeNow = true;
            }
            if(VehicleWorld.ChangeLaneNow() && canChangeNow){
                smoothChange = true;
                changeLocation = getY() + VehicleWorld.laneHeight + 5;
                canChangeNow = false;
            }
            else{
                canChangeNow = false;
            }
            if(smoothChange && getY() < changeLocation){
                setLocation(getX(), getY() + 3);
            }
            else if (smoothChange && getY() >= changeLocation){
                smoothChange = false;
            }
            //makes the speed lower to slow down for the ambulance
            maxSpeed = 1;
        }
    }
    public boolean checkHitWalker() {
        Crossers p = (Crossers)getOneIntersectingObject(Crossers.class);        
        if (p != null){
            //checks to see if it's picking up any Crossers
            getWorld().removeObject(p);
            //marks time at which it finds a crosser
            time.mark();
            //boolean to make the bus stop while picking up Crossers
            stop = true;
            return true;
        }
        return false;
    }
}
