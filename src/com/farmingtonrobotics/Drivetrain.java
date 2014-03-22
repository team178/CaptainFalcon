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
    private static final Victor left = new Victor(1), right = new Victor(2);
    private static final double speedLimit = 1;
    private static double easeInterval = .5;
    private static double lastSpeed = 0;
    private static final Timer driveTimer = new Timer();

    public Drivetrain(Joystick main) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.driveStick = main;
        Drivetrain.driveTimer.start();
    }

    public void tickTeleop() {
        double yValue = -driveStick.getY();
        double twistValue = driveStick.getTwist();
        Drivetrain.drive(yValue, twistValue);
    }

    public void tickAuto() {
        if (Ultrasonic.getDistanceFromWall() > 2 && Robot.self.getAutonomousTimer().get() > 3.5)
            Drivetrain.drive(-.7, .08);
        else if (Ultrasonic.getDistanceFromWall() > 1 && Robot.self.getAutonomousTimer().get() > 3.5)
            Drivetrain.drive(-.525, .06);
        else if (Ultrasonic.getDistanceFromWall() < 1)
            Drivetrain.drive(.1, -.1991);
        else
            Drivetrain.drive(0, 0);
    }

    public static double lerp(double a, double b, double f) {
        return (a * (1.0f - f)) + (b * f);
    }

    private static boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) < .1;
    }

    public static void drive(double yValue, double twistValue) {
        if (!doubleEqual(yValue, lastSpeed)) {
            easeInterval = 0;
            lastSpeed = yValue;
        }
        if (easeInterval >= 1)
            lastSpeed = yValue;
        else {
            yValue = lerp(lastSpeed, yValue, easeInterval);
            easeInterval += Drivetrain.driveTimer.get() / .5;
            Drivetrain.driveTimer.reset();
        }
        left.set(speedLimit * -(-yValue - twistValue));
        right.set(speedLimit * (-yValue + twistValue));
    }
}
