/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Administrator
 */
public class Encoders implements Component{
	private static final Encoder encoderLeft = new Encoder(4, 5);
	private static final Encoder encoderRight = new Encoder(7, 8);
	
	public void tickTeleop() {
		SmartDashboard.putNumber("Left encoder", encoderLeft.getDistance());
		SmartDashboard.putNumber("Right encoder", encoderRight.getDistance());
	}

	public void tickAuto() {
	}
	
}
