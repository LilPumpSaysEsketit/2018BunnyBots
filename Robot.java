package frc.team5115.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.team5115.robot.Subsystems.*;


public class Robot extends TimedRobot {

    public static Drivetrain drivetrain;


    @Override
    public void robotInit() {


        drivetrain = new Drivetrain();


    }

    @Override
    public void disabledInit() { }
    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }
    @Override
    public void teleopPeriodic() {
        drivetrain.drive();
    }

    @Override
    public void testPeriodic() { }
}