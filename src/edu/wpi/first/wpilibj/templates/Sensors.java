/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Enforers
 */
public class Sensors{
    //Victor encoderMotor;
    //Joystick driveStick;
    Encoder testDistance;
    RobotTemplate templateImports;
    Drivetrain joystickContinuity;
    AnalogChannel ultrasonic;
    final double coeffecient = 106;
    Relay flashyLight;

    public Sensors(Drivetrain joystickContinuity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //encoderMotor = new Victor(5);
        //driveStick = new Joystick(1);
        this.testDistance = new Encoder(4, 5);
        testDistance.setDistancePerPulse(.0625 * Math.PI);
        testDistance.setMaxPeriod(.2);
        testDistance.start();
        this.joystickContinuity = joystickContinuity;
        this.templateImports = templateImports;
        ultrasonic = new AnalogChannel(1);
        flashyLight = new Relay(3, Relay.Direction.kBoth);
    }
    
    public void encoder(){
        System.out.println( testDistance.getDistance() + " Distance");
        System.out.println( testDistance.getRate() + " Rate");
        System.out.println( testDistance.getDistance() * .0625 * Math.PI + " Real Distance?");
        System.out.println( testDistance.getDistance() * .0625 * Math.PI / 12 + " Feet?");
        System.out.println( testDistance.getDistance() / 12 + " Feet");
        if(joystickContinuity.driveStick.getRawButton(10)){
            testDistance.reset();
        }
    }
    public void ultraSonic(){
        System.out.println("Distance From Wall: " + ultrasonic.getVoltage() * coeffecient);
        if((ultrasonic.getVoltage() * coeffecient / 12.0) <= 5.0){
            flashyLight.set(Relay.Value.kForward);
        }else{
            flashyLight.set(Relay.Value.kOff);
        }
    }
}