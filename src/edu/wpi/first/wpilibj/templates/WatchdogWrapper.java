/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author Enforers
 */
public class WatchdogWrapper implements Component{
    private Watchdog w;
    public WatchdogWrapper(Watchdog w) {
        this.w=w;
    }
    
    public void tickTeleop() {
        w.feed();
    }
    public void tickAuto() {
        w.feed();
    }
    
}
