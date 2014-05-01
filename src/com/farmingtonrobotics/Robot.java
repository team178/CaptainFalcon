/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.Watchdog;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public static Robot self;
    private boolean autoSafeToFire;
    private final Joystick main = new Joystick(1);
    private final Joystick aux = new Joystick(2);
    private Timer autonomousTimer;
    private final Component[] components = {
        new Ultrasonic(),
        new Compressor(),
        new Drivetrain(main),
        new IntakeSystem(aux),
        new WatchdogWrapper(Watchdog.getInstance()),
        new Shooter(aux),
        new LittleFinger(),
        new RingFinger(aux)
    };

    public Robot() {
        self = this; //ignore the warning
    }

    public void teleopInit() {
        System.out.println("TELEOPINIT CALLED");
        IntakeSystem.setRingLockedWhenPossible(false);
        Shooter.retract();
        RingFinger.setLockRingFingerWhenPossible(false);
    }

    public void autonomousInit() {
        this.autonomousTimer = new Timer();
        this.autonomousTimer.start();
        this.autoSafeToFire = false;
        RingFinger.setLockRingFingerWhenPossible(false);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        try {
            for (int i = 0; i < components.length; ++i)
                components[i].tickAuto();
        } catch (Exception e) {
            e.printStackTrace();
            Watchdog.getInstance().kill();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try {
            for (int i = 0; i < components.length; ++i)
                components[i].tickTeleop();
        } catch (Exception e) {
            e.printStackTrace();
            Watchdog.getInstance().kill();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }

    public Timer getAutonomousTimer() {
        if (this.isAutonomous())
            return autonomousTimer;
        throw new IllegalStateException("Tried to get timer outside of Autonomous");
    }

    public boolean isSafeToFire() {
        if (this.isAutonomous())
            return autoSafeToFire;
        throw new IllegalStateException("Tried to get safety outside of Autonomous");
    }

    public void setSafeToFire() {
        if (this.isAutonomous())
            autoSafeToFire = true;
        else
            throw new IllegalStateException("Tried to set safety outside of Autonomous");
    }
}
