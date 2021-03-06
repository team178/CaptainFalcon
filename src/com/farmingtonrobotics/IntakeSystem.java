package com.farmingtonrobotics;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class IntakeSystem implements Component {

    private final Talon motorTop = new Talon(3);
    private final Joystick auxStick;
    private final static DoubleSolenoid intake = new DoubleSolenoid(1, 2);
    private static final DigitalInput oDown = new DigitalInput(14);
    private static boolean ringIntent = false;
    private static final Timer intakeTimer = new Timer();
    public static boolean immediateServo;

    public IntakeSystem(Joystick aux) {
        this.auxStick = aux;
        intakeTimer.start();
    }

    public void tickTeleop() {
        servoTick();
        if (auxStick.getRawButton(2)) {
            immediateServo = true;
            setRingLockedWhenPossible(false);
            RingFinger.setLockRingFingerWhenPossible(false);
        } else
            immediateServo = false;
        if (auxStick.getRawButton(3))
            intake.set(DoubleSolenoid.Value.kForward);
        if (auxStick.getRawButton(4))
            intakeBall();
        else if (auxStick.getRawButton(6))
            motorTop.set(1);
        else if (auxStick.getRawButton(5))
            motorTop.set(-1);
        else
            motorTop.set(0);
    }

    public void intakeBall() {
        intake.set(DoubleSolenoid.Value.kReverse);
        motorTop.set(0.5);
        IntakeSystem.setRingLockedWhenPossible(true);
        RingFinger.setLockRingFingerWhenPossible(true);
    }

    public void tickAuto() {
        servoTick();
        final double get = Robot.self.getAutonomousTimer().get();
        if (get < 2) {
            intake.set(DoubleSolenoid.Value.kForward);
            motorTop.set(1);
        } else if (get < 5)
            intakeBall();
        else {
            intake.set(DoubleSolenoid.Value.kForward);
            motorTop.set(0);
            Robot.self.setSafeToFire();
        }
    }

    private static void servoTick() {
        if (ringIntent && isODown())
            LittleFinger.setServoIntent(true);
        else if (!ringIntent && (intakeTimer.get() > 2 || immediateServo))
            LittleFinger.setServoIntent(false);
    }

    public static boolean isRingIntent() {
        return ringIntent;
    }

    public static void setRingLockedWhenPossible(boolean aRingIntent) {
        ringIntent = aRingIntent;
        if (!aRingIntent)
            intakeTimer.reset();
        IntakeSystem.servoTick();
    }

    public static boolean isODown() {
        return oDown.get();
    }
}
