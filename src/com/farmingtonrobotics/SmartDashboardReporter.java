/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Enforers
 */
public class SmartDashboardReporter implements Component{
    
    public void tickTeleop() {
        tick();
    }

    public void tickAuto() {
        tick();
    }

    private void tick() {
        SmartDashboard.putNumber("Voltage",DriverStation.getInstance().getBatteryVoltage());
    }
    
}
