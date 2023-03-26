import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Bus subclass
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
        if(!busEngine.isPlaying()){
            busEngine.play();
        }
        giveWay();
        if(!stop){
            drive();
        }
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
        List<Ambulance>objects = getObjectsInRange(400, Ambulance.class);
        if(objects.size() > 0){
            if((600-VehicleWorld.laneHeight*2) > getY()){
                VehicleWorld.change(getX(), getY());
                /*othersChanging = true;*/
                canChangeNow = true;
            }
            if(VehicleWorld.ChangeLaneNow() && canChangeNow){
                smoothChange = true;
                changeLocation = getY() + VehicleWorld.laneHeight + 5;
                /*othersChanging = false;*/
                canChangeNow = false;
            }
            else{
                canChangeNow = false;
                //othersChanging = false;
            }
            if(smoothChange && getY() < changeLocation){
                setLocation(getX(), getY() + 3);
            }
            else if (smoothChange && getY() >= changeLocation){
                smoothChange = false;
            }
            maxSpeed = 1;
        }
    }
    public boolean checkHitWalker() {
        Crossers p = (Crossers)getOneIntersectingObject(Crossers.class);        
        if (p != null){
            getWorld().removeObject(p);
            time.mark();
            stop = true;
            return true;
        }
        return false;
    }
}
