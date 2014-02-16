/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Enforers
 */
public class Shooter implements Component{
    private Joystick aux;
    Relay low;
    Relay medium;
    Relay high;
    

    public Shooter(Joystick aux) {
        this.aux = aux;
        low = new Relay(1);
        medium = new Relay(2);
        high = new Relay(3);
    }
    
    public void tickTeleop() {
        if(aux.getRawButton(1)){
            extend();
        }else{
            retract();
        }
            
    }
    public void tickAuto() {
        
    }

    public void retract() {
        low.set(Relay.Value.kOff);
        medium.set(Relay.Value.kOff);
        high.set(Relay.Value.kOff);
    }

    public void extend() {
        low.set(Relay.Value.kForward);
        medium.set(Relay.Value.kForward);
        high.set(Relay.Value.kForward);
    }
    
}
