/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Enforers
 */
public class Compressor {
    Relay compressor;
    AnalogChannel analogPressure;
    public Compressor(){
        compressor = new Relay(3, Relay.Direction.kForward);
        analogPressure = new AnalogChannel (5);
    }
    
    public void compressorOn(){
        compressor.set(Relay.Value.kForward);
    }
    
    public void compressorOff(){
        compressor.set(Relay.Value.kOff);
    }
    
    public void Analog() {
        double psiCompressorCount;
        psiCompressorCount = analogPressure.getVoltage ();
        System.out.println(psiCompressorCount);
        if (psiCompressorCount <50) {
            compressorOn();
        }
    }
    
}
