/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class Compressor implements Component {

    Relay compressor = new Relay(4, Relay.Direction.kForward);
    DigitalInput cutoff = new DigitalInput(13);
    AnalogChannel analogPressure = new AnalogChannel(3);
    private static double pressure;

    public Compressor() {
        analogPressure.setAverageBits(4);
    }
    
    public void tickTeleop() {
        compressor.set(cutoff.get() ? Relay.Value.kOff : Relay.Value.kForward);
        pressure = (analogPressure.getAverageVoltage() - .854) * 40.9276;
        SmartDashboard.putNumber("Pressure", pressure);

        SmartDashboard.putBoolean("Ready to Fire", pressure > 80);
    }

    public void tickAuto() {
        compressor.set(cutoff.get() ? Relay.Value.kOff : Relay.Value.kForward);
    }
    
    public static double getPressure(){
        return pressure;
    }
}
