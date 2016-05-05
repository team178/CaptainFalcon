/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Enforers
 */
public class Drivetrain implements Component {

    private final Joystick driveStick;
    private final Joystick adultStick;
    private static final Victor left = new Victor(1), right = new Victor(2);
    private static final double speedLimit = 1;
    private static double easeInterval = .5;
    private static double lastSpeed = 0;
    private final Timer driveTimer = new Timer();

    public Drivetrain(Joystick main) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.driveStick = main;
        this.adultStick = null;
        this.driveTimer.start();
    }
    
     public Drivetrain(Joystick main, Joystick aux) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.driveStick = main;
        this.adultStick = aux;
        this.driveTimer.start();
    }

    public void tickTeleop() {
        double yValue = -driveStick.getY();
        double twistValue = driveStick.getTwist();
        //this.drive(yValue, twistValue);
        this.kiddyDrive();
    }

    public void tickAuto() {
        if (Ultrasonic.getDistanceFromWall() > 1 && Robot.self.getAutonomousTimer().get() > 3.5)
            this.drive(-1.0, 0.04);
        else if (Ultrasonic.getDistanceFromWall() > .75 && Robot.self.getAutonomousTimer().get() > 3.5)
            this.drive(-.5, 0.02);
        else
            this.drive(0, 0);
    }

    public static double lerp(double a, double b, double f) {
        return (a * (1.0f - f)) + (b * f);
    }

    private static boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) < .1;
    }

    public void drive(double yValue, double twistValue) {
        if (!doubleEqual(yValue, lastSpeed)) {
            easeInterval = 0;
            lastSpeed = yValue;
        }
        if (easeInterval >= 1) {
            lastSpeed = yValue;
        } else {
            yValue = lerp(lastSpeed, yValue, easeInterval);
            easeInterval += this.driveTimer.get() / .5;
            this.driveTimer.reset();
        }
        left.set(speedLimit * -(-yValue - twistValue));
        right.set(speedLimit * (-yValue + twistValue));
    }
    
    public void kiddyDrive() {
        double mainY = -.6*driveStick.getY();
        double mainTwist = .8*driveStick.getTwist();
        double adultY = -adultStick.getY();
        double adultTwist = adultStick.getX();
        if (adultStick.getRawAxis(3) < .9) {
            this.drive(mainY, mainTwist);
        } else {
            this.drive(adultY, adultTwist);
        }
    }
}
