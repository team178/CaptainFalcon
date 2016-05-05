/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class Ultrasonic implements Component {

    private static final AnalogChannel ultrasonicFront = new AnalogChannel(1);

    public Ultrasonic() {
        ultrasonicFront.setAverageBits(3);
    }

    public static void pushSmartDash() {
        SmartDashboard.putNumber("Distance From Wall", getDistanceFromWall());
    }

    public static double getDistanceFromWall() {
        return ultrasonicFront.getAverageVoltage() * 1 / 0.813;
    }

    public void tickTeleop() {
        pushSmartDash();
    }

    public void tickAuto() {

    }
}
