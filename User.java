import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class User extends Vehicle
{
    private static double first = 2.5;
    private static double second = 2.1;
    private static double third = 1.7;
    private static double fourth = 1.3;
    private static double fifth = 0.9;
    private static double gear = fifth;
    private double rpm;
    private double wheelSpeed;
    public double Rpm(){
        return wheelSpeed / gear;
    }
    public void addedToWorld(World w){
        setLocation(400, 360);
    }
    public User(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
    }
    public void act()
    {
        if(Greenfoot.isKeyDown("A") && gear == first){
            this.gear = second;
        }
        else if(Greenfoot.isKeyDown("A") && gear == second){
            this.gear = third;
        }
        else if(Greenfoot.isKeyDown("A") && gear == third){
            this.gear = fourth;
        }
        else if(Greenfoot.isKeyDown("A") && gear == fourth){
            this.gear = fifth;
        }
        else if(Greenfoot.isKeyDown("Z") && gear == fifth){
            this.gear = fourth;
        }
        else if(Greenfoot.isKeyDown("Z") && gear == fourth){
            this.gear = third;
        }
        else if(Greenfoot.isKeyDown("Z") && gear == third){
            this.gear = second;
        }
        else if(Greenfoot.isKeyDown("Z") && gear == second){
            this.gear = first;
        }
        checkHitWalker();
        rpm = Rpm();
        if(rpm > 1000){
            wheelSpeed -= 10;
        }
    }
    public void drive(){
        if(Greenfoot.isKeyDown("up")){
            if(gear == first){
                wheelSpeed += 100;
            }
            else if(gear == second){
                wheelSpeed += 90;
            }
            else if(gear == third){
                wheelSpeed += 80;
            }
            else if(gear == fourth){
                wheelSpeed += 70;
            }
            else if(gear == fifth){
                wheelSpeed += 60;
            }

        }
    }
    public double getWheelSpeed(){
        return wheelSpeed * .07;
    }
    public void brake(){
        if(Greenfoot.isKeyDown("down")){
            wheelSpeed -= 100;
        }
    }
    public boolean checkHitWalker () {
        Crossers p = (Crossers)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Crossers.class);
        
        if (p != null){
            p.knockDown();
            return true;
        }
        return false;
    }
}
