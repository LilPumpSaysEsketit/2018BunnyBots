package frc.team5115.robot.Subsystems;
//Import things and stuffs
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5115.robot.Controls;

public class Drivetrain {

    //The following paragraph initiates the talons for the drive train and gives them their IDS
    public static TalonSRX leftFrontTalon;
    public static TalonSRX leftBackTalon;
    public static TalonSRX rightFrontTalon;
    public static TalonSRX rightBackTalon;
    public static int leftFrontTalonID= 3;
    public static int leftBackTalonID= 1;
    public static int rightFrontTalonID= 4;
    public static int rightBackTalonID= 2;
    Command drive;

    // Initiates the NAVX object
    public static AHRS navx;
    public Drivetrain(){
        // Create talon objects from the classes and IDs
        leftFrontTalon = new TalonSRX(leftFrontTalonID);
        leftBackTalon = new TalonSRX(leftBackTalonID);
        rightFrontTalon = new TalonSRX(rightFrontTalonID);
        rightBackTalon = new TalonSRX(rightBackTalonID);
        // Set the navx to the MXP port
        navx = new AHRS(SPI.Port.kMXP);

        //Make the front talons copy the commands sent to the back talons
        leftFrontTalon.set(ControlMode.Follower, leftBackTalonID);
        rightFrontTalon.set(ControlMode.Follower, rightBackTalonID);

        //Let the talons listen to the quad encoders
        leftBackTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        rightBackTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);



    }

    public double leftDist() { return (leftBackTalon.getSelectedSensorPosition(0) / 1440)*(Math.PI * 6); }

    public double rightDist()
    {
        return (rightBackTalon.getSelectedSensorPosition(0) / 360)*(Math.PI * 6);
    }

    public void drive(double speed, double turn){//drive method is defined as speed and turn
        double leftspeed = speed + turn; //think of phase shifting
        double rightspeed = speed - turn;

        if(Math.abs(leftspeed) > 1){
            leftspeed = Math.signum(leftspeed);
        }
        if(Math.abs(rightspeed) > 1){
            rightspeed = Math.signum(rightspeed);

        }
//		System.out.println("left: " + leftspeed);
//		System.out.println("right: " + rightspeed);

        leftBackTalon.set(ControlMode.PercentOutput, -leftspeed);
        rightBackTalon.set(ControlMode.PercentOutput, rightspeed);
    }


    public double leftSpeed() {
        double leftspeed = -leftBackTalon.getSelectedSensorVelocity(0);
        return ((leftspeed * 6 * Math.PI * 10) / (1440 * 12));

    }

    public double rightSpeed() {
        double rightspeed = rightBackTalon.getSelectedSensorVelocity(0);
        return ((rightspeed * 6 * Math.PI * 10) / (1440 * 12));
    }

    public double distanceTraveled() {
        return rightDist();
    }

    public double averageSpeed() {
        return (leftSpeed() + rightSpeed()) / 2;
    }

    public double getPitch(){
        return navx.getPitch();
    }

    public double getRoll(){
        return navx.getRoll();
    }

    public double getYaw() {
        return navx.getYaw();
    }

    public double getTurnVelocity() {
        return navx.getRate();
    }
    public double forwarAccel(){
        return -navx.getRawAccelY(); //this way points forward on our robot
    }
    // This method resets the values given by the encoders to a default of 0
    public void resetEncoders() {
        leftBackTalon.setSelectedSensorPosition(0, 0, 5); //5 ms
        rightBackTalon.setSelectedSensorPosition(0, 0, 5);
    }
    public void resetGyro(){
        navx.reset();
    }

    public void drive () {
        double xValue = Controls.controller.getX();
        double yValue = Controls.controller.getY();
        double throt = (-(Controls.controller.getThrottle())+1)/2;

        leftBackTalon.set(ControlMode.PercentOutput, (yValue + xValue)*throt);
        rightBackTalon.set(ControlMode.PercentOutput, -(yValue - xValue)*throt);

    }




}
