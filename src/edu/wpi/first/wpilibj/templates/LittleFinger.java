/**
 * @author gnomes heheh (team 178)
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class LittleFinger implements Component {

    private static boolean servoIntent = false;
    private final static Timer runTime = new Timer();
    private static final Servo oServo = new Servo(4);

    public LittleFinger() {
        runTime.start();
    }

    public void tickTeleop() {
        tick();
    }

    public void tickAuto() {
        tick();
    }

    private void tick() {
        if (runTime.get() < 2) {
            if(servoIntent)
                oServo.set(0.1);
            else
                oServo.set(1.0);
        } else {
            oServo.set(0.5);
        }
    }

    public static void setServoIntent(boolean newServoIntent) {

        if (newServoIntent != LittleFinger.servoIntent) {
            LittleFinger.servoIntent = newServoIntent;
            runTime.reset();
        }
    }

    public static void overrideRelease() {
        oServo.set(1.0);
    }
    
}