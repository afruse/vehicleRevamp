import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Street racer
 * still respoects ambulances
 * originally meant to be user controlled car but not enough time for that
 */
public class User extends Vehicle
{
    //private static boolean othersChanging = false;
    private boolean canCheckChange = true;
    private boolean canChangeNow = false;
    private boolean smoothChange = false;
    private GreenfootSound vroom;
    private GreenfootSound exorcist;
    private GreenfootSound squee;
    private int changeLocation;
    public User(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 5.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
        vroom = new GreenfootSound("vroom_exorcist.wav");
        exorcist = new GreenfootSound("exorcist_8.wav");
        squee = new GreenfootSound("exorcist_squee.wav");
    }

    public void act()
    {
        boom();
        //plays engine sound if not already playing to loop
        if(!exorcist.isPlaying()){
            exorcist.play();
        }
        if(!squee.isPlaying()){
            //squee.play();
        }
        drive();
        //calls the laneChange spawner
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if(ahead != null && /*!othersChanging &&*/ canCheckChange && (600-VehicleWorld.laneHeight*2) > getY()){
            VehicleWorld.change(getX(), getY());
            canCheckChange = false;
            canChangeNow = true;
        }
        //gets whether or not lane is free to change to
        //if it is free then the x value to change to will be set and boolean to start changing will be set to true
        if(VehicleWorld.ChangeLaneNow() && canChangeNow){
            smoothChange = true;
            changeLocation = getY() + VehicleWorld.laneHeight + 5;
            canChangeNow = false;
        }
        else{
            canChangeNow = false;
        }
        //to change smoothly free lane
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
            exorcist.stop();
            squee.stop();
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
