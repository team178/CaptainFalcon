/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Enforers
 */
public class Drivetrain implements Component{
    private Joystick driveStick;
    private static final Victor left = new Victor(1), right = new Victor(2);
    private static final double speedLimit = 0.75;

    public Drivetrain(Joystick main) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.driveStick = main;
    }   

    public void tickTeleop(){
        double yValue = -driveStick.getY();
        double twistValue = .8*driveStick.getTwist();
        Drivetrain.drive(yValue, twistValue);
    }
    public void tickAuto() {
        if(Ultrasonic.getDistanceFromWall() <= 10)
            Drivetrain.drive(0, 0);
        else    
            Drivetrain.drive(.2, 0);
    }

    public static void drive(double yValue, double twistValue) {
        left.set( speedLimit * -( yValue - twistValue ) );
        right.set( speedLimit * ( yValue + twistValue ) );
    }
}