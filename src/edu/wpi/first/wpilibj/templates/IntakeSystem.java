package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class IntakeSystem {
    Solenoid extend;
    Solenoid retract;
    Victor motorTop;
    Joystick joystick;
    boolean intakeValue;
    public IntakeSystem(){
        extend = new Solenoid(1);
        //solenoid to rextend the WoT
        retract = new Solenoid(2);
        //solenoid to retract the Wot
        motorTop = new Victor(3);
        joystick = new Joystick(1);
    }
    public void intake(){
        if (joystick. getRawButton(1) == true) {
            //if button #1 is pressed
            extend.set(true);
            retract.set(false);
            //WoT extends
            if (joystick.getRawButton(2) == true) {
                //if button #2 is pressed
                extend.set(false);
                retract.set(true);
                //WoT retracts
            }
            if (joystick.getRawButton(3) == true) {
                //if button #3 is pressed
                motorTop.set(0.5);
                // motor that runs wheels starts
            } 
        }
         if (joystick.getRawButton(4) == true) {
             //if button #4 is pressed
             motorTop.set(0);
             //motor that runs wheels stops
         }
    }
    public boolean intakeValue() {
        intakeValue = extend.get();
        return intakeValue;
    }
}