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
    //Victor leftFront, rightFront, leftBack, rightBack;
    Victor right, left;
    Victor encoderMotor;
    double speedLimit = 0.75;

    public Drivetrain() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        /*leftFront = new Victor(6);
        rightFront = new Victor(4);
        leftBack = new Victor(1);
        rightBack = new Victor(3);
        this.encoderMotor = new Victor(7);
        this.driveStick = new Joystick(1);
        this.kidStick = new Joystick(2);*/
        left = new Victor(1);
        right = new Victor(2);
    }   

    public void drive(){
        double yValue = driveStick.getY();
        double twistValue = .8*driveStick.getTwist();
            left.set( speedLimit * ( yValue + twistValue ) );
            right.set( speedLimit * ( yValue - twistValue ) );
            encoderMotor.set(driveStick.getY());
    }
    public void kiddyDrive(){
        double xValue = driveStick.getX();
        double yValue = driveStick.getY();
        double twistValue = .8*driveStick.getTwist();
        double kidX = kidStick.getX() * .3;
        double kidY = kidStick.getY() * .3;
        double kidTwist = kidStick.getTwist() * .4;
        if(driveStick.getTrigger()){
            left.set( speedLimit * ( yValue + twistValue ) );
            right.set( speedLimit * ( yValue - twistValue ) );
            //encoderMotor.set(driveStick.getY());
        }else{
            left.set( speedLimit * ( kidY + kidTwist ) );
            right.set( speedLimit * ( kidY - kidTwist ) );
        }
    }
}