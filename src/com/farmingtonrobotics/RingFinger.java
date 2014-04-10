/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Enforers
 */
public class RingFinger implements Component {

    private static boolean donutIntent = false;
    private final static Timer runTime = new Timer();
    private static final DoubleSolenoid solenoid = new DoubleSolenoid(4,3);
    private final Joystick aux;

    RingFinger(Joystick aux) {
        runTime.start();
        this.aux = aux;
    }

    public void tickTeleop() {
        if ((runTime.get() > 1 && donutIntent && IntakeSystem.isODown()) || aux.getRawAxis(3) < -0.9)
            solenoid.set(DoubleSolenoid.Value.kForward);
        else if (!donutIntent)
            solenoid.set(DoubleSolenoid.Value.kReverse);
        if (aux.getRawAxis(3) > 0.9)
            setDonutIntent(false);
        /*
         for every tick in teleop:

         if (the timer > 1 and intent is true) or Axis 3 on Aux controller is <-0.9 (which means the right trigger is pressed) put the finger down
         else if intent is false put the finger up

         */
    }

    public void tickAuto() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public static void setDonutIntent(boolean oldDonutIntent) {
        if (oldDonutIntent != RingFinger.donutIntent) {
            RingFinger.donutIntent = oldDonutIntent;
            runTime.reset();
        }
    }
}
