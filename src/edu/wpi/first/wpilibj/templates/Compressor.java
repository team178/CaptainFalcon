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
public class Compressor implements Component{
    Relay compressor = new Relay(4, Relay.Direction.kForward);
    DigitalInput cutoff = new DigitalInput(6);

    public void tickTeleop() {
        compressor.set(cutoff.get()?Relay.Value.kOff:Relay.Value.kForward);
    }
    public void tickAuto() {
        compressor.set(cutoff.get()?Relay.Value.kOff:Relay.Value.kForward);
    }
}