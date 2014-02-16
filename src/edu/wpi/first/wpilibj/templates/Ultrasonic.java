/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class Ultrasonic implements Component{
    //Victor encoderMotor;
    //Joystick driveStick;
    Encoder testDistance;
    RobotTemplate templateImports;
    static AnalogChannel ultrasonicFront;
    final static double coeffecient = 1/0.628;
    Relay flashyLight;

    public Ultrasonic() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //encoderMotor = new Victor(5);
        //driveStick = new Joystick(1);
//        this.testDistance = new Encoder(4, 5);
//        testDistance.setDistancePerPulse(.0625 * Math.PI);
//        testDistance.setMaxPeriod(.2);
//        testDistance.start();
//        this.joystickContinuity = joystickContinuity;
//        this.templateImports = templateImports;
        ultrasonicFront = new AnalogChannel(1);
//        flashyLight = new Relay(3, Relay.Direction.kBoth);
    }
    
//    public void encoder(){
//        System.out.println( testDistance.getDistance() + " Distance");
//        System.out.println( testDistance.getRate() + " Rate");
//        System.out.println( testDistance.getDistance() * .0625 * Math.PI + " Real Distance?");
//        System.out.println( testDistance.getDistance() * .0625 * Math.PI / 12 + " Feet?");
//        System.out.println( testDistance.getDistance() / 12 + " Feet");
//        if(joystickContinuity.driveStick.getRawButton(10)){
//            testDistance.reset();
//        }
//    }
    public static void pushSmartDash(){
        SmartDashboard.putNumber("Distance From Wall", getDistanceFromWall());
        //SmartDashboard.putBoolean("Shoot Distance", getDistanceFromWall() >= 9 && getDistanceFromWall() <= 11);
    }
    public static double getDistanceFromWall(){
        return ultrasonicFront.getVoltage() * coeffecient;
    }

    public  void tickTeleop() {
        pushSmartDash();
    }

    public void tickAuto() {
        
    }
}