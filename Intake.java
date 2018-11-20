package frc.team5115.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI.Port;


import javax.naming.ldap.Control;

import static frc.team5115.robot.Controls.*;

public class Intake {

    /*public static TalonSRX intakeTalon;
    public static int intakeTalonID = 5; // Change the ID accordingly*/

    //Declare a spark in the case we use spark for intake
    public static Spark sparkIntake;



    public Intake() {

        sparkIntake = new Spark(5); //Let the spark ID 5

        /*intakeTalon = new TalonSRX(intakeTalonID);
        if (intakeOn == true) {
            intakeTalon.set(ControlMode.PercentOutput, .85);
        }
        else {
            intakeTalon.set(ControlMode.PercentOutput, 0);
        }*/

        if (intakeOn == true) {
            sparkIntake.set(1);
        }
        else if (intakeOn == false) {
            sparkIntake.set(0);
        }


    }


}
