/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class Ultrasonic implements Component{
    private static final AnalogChannel ultrasonicFront = new AnalogChannel(1);
    public final static double stolarzConstant = 1/0.628;

    public Ultrasonic() {
        ultrasonicFront.setAverageBits(2);
    }
    public static void pushSmartDash(){
        SmartDashboard.putBoolean("Distance From Wall", .8<= getDistanceFromWall() &&getDistanceFromWall()<=1.2);
    }
    public static double getDistanceFromWall(){
        return ultrasonicFront.getAverageVoltage() * stolarzConstant;
    }

    public  void tickTeleop() {
        pushSmartDash();
    }

    public void tickAuto() {
        
    }
}