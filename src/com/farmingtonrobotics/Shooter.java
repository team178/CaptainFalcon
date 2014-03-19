/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Enforers
 */
public class Shooter implements Component {

    private final Joystick aux;
    private final static Relay low = new Relay(1);
    private final static Relay medium = new Relay(2);
    private final static Relay high = new Relay(3);
    private boolean shotsFired = false;
    private double lastFired;

    public Shooter(Joystick aux) {
        this.aux = aux;
    }

    public void tickTeleop() {
        shotsFired = false;
        if(aux.getRawButton(1)||(aux.getRawButton(7) && aux.getRawButton(8)))
            logFiring();
        if ( //i'm so sorry
                (aux.getRawButton(1) && IntakeSystem.isODown() && Compressor.getPressure() >= 70)
                || (aux.getRawButton(7) && aux.getRawButton(8))) {
            extend();
            IntakeSystem.setRingIntent(false);
            
        } else
            retract();
    }

    public void tickAuto() {
        logFiring();
        if (IntakeSystem.isODown()
                && Ultrasonic.getDistanceFromWall() <= 1.05
                && !shotsFired
                && Robot.self.isSafeToFire()
                && Robot.self.getAutonomousTimer().get() > 7) {
            extend();
            System.out.println("BANG");
            shotsFired = true;
            lastFired = Robot.self.getAutonomousTimer().get();
        } else if (Robot.self.getAutonomousTimer().get() > lastFired + .4) {
            retract();
            IntakeSystem.setRingIntent(false);
        }
    }

    public void logFiring() {
        System.out.println("Is the O Ring down? "+IntakeSystem.isODown());
        System.out.println("Units from wall: "+Ultrasonic.getDistanceFromWall());
        System.out.println("Have I fired yet? "+!shotsFired);
        if (Robot.self.isAutonomous())
            System.out.println("Am I at the firing part of the Autonomous sequence? "+Robot.self.isSafeToFire());
        System.out.println("PSI: "+Compressor.getPressure());
        System.out.println();
    }

    public static void retract() {
        low.set(Relay.Value.kOff);
        medium.set(Relay.Value.kOff);
        high.set(Relay.Value.kOff);
    }

    public static void extend() {
        low.set(Relay.Value.kForward);
        medium.set(Relay.Value.kForward);
        high.set(Relay.Value.kForward);
    }
}
