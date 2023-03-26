import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    //private static boolean othersChanging = false;
    private boolean canCheckChange = true;
    private boolean canChangeNow = false;
    private boolean smoothChange = false;
    private GreenfootSound vroom;
    private GreenfootSound fourK;
    private int changeLocation;
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
        vroom = new GreenfootSound("vroom.wav");
        fourK = new GreenfootSound("4k.wav");
    }

    public void act()
    {
        if(!fourK.isPlaying()){
            fourK.play();
        }
        drive();
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if(ahead != null && /*!othersChanging &&*/ canCheckChange && (600-VehicleWorld.laneHeight*2) > getY()){
            VehicleWorld.change(getX(), getY());
            /*othersChanging = true;*/
            canCheckChange = false;
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
            vroom.play();
            setLocation(getX(), getY() + 3);
        }
        else if (smoothChange && getY() >= changeLocation){
            smoothChange = false;
        }
        giveWay();
        drive();
        checkHitWalker();
        if (checkEdge()){
            getWorld().removeObject(this);
            fourK.stop();
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
