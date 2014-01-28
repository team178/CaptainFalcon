/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Enforers
 */
public class Compressor {
    Relay compressor;
    
    public Compressor(){
        compressor = new Relay(3, Relay.Direction.kForward);
    }
    
    public void compressorOn(){
        compressor.set(Relay.Value.kForward);
    }
    
    public void compressorOff(){
        compressor.set(Relay.Value.kOff);
    }
    
}
