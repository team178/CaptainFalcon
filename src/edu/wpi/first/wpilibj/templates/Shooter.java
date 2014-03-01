/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class Shooter implements Component{
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
        if(aux.getRawButton(1))
            System.out.println("oRing down: " + IntakeSystem.isODown());
        if( //i'm so sorry
                aux.getRawButton(1)&&(
                    IntakeSystem.isODown()||
                    (aux.getRawButton(7)&&aux.getRawButton(8))
                )
        ){
            extend();
            IntakeSystem.setRingIntent(false);
        }else{
            retract();
        }   
    }

    public void tickAuto() {
        System.out.println(IntakeSystem.isODown());
        System.out.println(Ultrasonic.getDistanceFromWall());
        System.out.println(!shotsFired);
        System.out.println(RobotTemplate.self.isSafeToFire());
        System.out.println();
        if(
                IntakeSystem.isODown() &&
                Ultrasonic.getDistanceFromWall() <= 1.05 &&
                !shotsFired &&
                RobotTemplate.self.isSafeToFire() &&
                RobotTemplate.self.getAutonomousTimer().get() > 7
        ){
            extend();
            System.out.println("BANG");
            shotsFired = true;
            lastFired = RobotTemplate.self.getAutonomousTimer().get();
        } else if( RobotTemplate.self.getAutonomousTimer().get() > lastFired + .4 ){
            retract();
            IntakeSystem.setRingIntent(false);
        }
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