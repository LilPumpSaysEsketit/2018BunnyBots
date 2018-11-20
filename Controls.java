package frc.team5115.robot;
//Import things and stuff
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI.Port;

import static edu.wpi.first.wpilibj.Joystick.ButtonType.*;

public class Controls {

    public static boolean intakeOn = false; // On default, set the intake to be off

    public static Joystick controller = new Joystick(0); // Import the joystick

    public static boolean pressed = controller.getRawButtonPressed(5); // Set button 5 to be a boolean
    /*public static boolean IntakeButton() {
        return controller.getRawButtonPressed(5); //The number within the parenthesis is the button ID
    }*/

    public void setPressed(){
    if (pressed == true && intakeOn == false) { // If the button is pressed and the intake is off
        System.out.println("True"); // Print true
        intakeOn = true; // Set the intake to true
    }
    else if (pressed == true && intakeOn == true) { // If the button is pressed and the intake is on
        System.out.println("False"); // Print false
        intakeOn = false; // Set the intake to be off
    }
    }

}
