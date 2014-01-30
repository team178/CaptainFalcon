/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private Watchdog watchdog;
    private Drivetrain drivetrain;
    Timer time;
    Sensors sense;
    Shooter shooter;
    Compressor compressor;
    DigitalInput compressorSwitch;
    Relay iceMotor;
        
    public void robotInit() {
        this.drivetrain = new Drivetrain();
        this.time = new Timer();
        sense = new Sensors(this.drivetrain);
        shooter = new Shooter();
        watchdog = Watchdog.getInstance();
        compressor = new Compressor();
        compressorSwitch = new DigitalInput(10);
        iceMotor = new Relay(4);
        iceMotor.set(Relay.Value.kForward);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        watchdog.feed();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try{
            //flashyLight.set(Relay.Value.kForward);
            drivetrain.drive();
            //drivetrain.kiddyDrive();
            //sense.encoder();
            watchdog.feed();
            sense.ultraSonic();
            //sense.flashLight();
            if(!compressorSwitch.get()){
                compressor.compressorOn();
            }else{
                compressor.compressorOff();
            }
            if(false/*WoT is true || manual override*/){
                shooter.shootThreeStage();
                shooter.shootTwoStage();
            }
        }catch(Exception e){
            System.out.print(e);
            watchdog.kill();
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }    
}