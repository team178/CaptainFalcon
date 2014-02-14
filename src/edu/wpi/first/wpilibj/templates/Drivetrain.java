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
public class Drivetrain {
    Joystick driveStick, kidStick;
    Victor right, left;
    double speedLimit = 0.75;

    public Drivetrain() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.driveStick = new Joystick(1);
        this.kidStick = new Joystick(3);
        left = new Victor(1);
        right = new Victor(2);
    }   

    public void drive(){
        double yValue = driveStick.getY();
        double twistValue = .8*driveStick.getTwist();
            left.set( speedLimit * ( yValue + twistValue ) );
            right.set( speedLimit * ( yValue - twistValue ) );
    }
}