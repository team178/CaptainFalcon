package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class IntakeSystem implements Component{
    Victor motorTop;
    private final Joystick auxStick;
    DoubleSolenoid intake;
    public IntakeSystem(Joystick aux){
        intake = new DoubleSolenoid(1, 2);
        motorTop = new Victor(3);
        this.auxStick = aux;
    }

    public void tickTeleop() {
        if(auxStick.getRawButton(4))
            intake.set(DoubleSolenoid.Value.kForward);
        if(auxStick.getRawButton(5))
            intake.set(DoubleSolenoid.Value.kOff);
        if(auxStick.getRawButton(6))
            motorTop.set(1);
        else if(auxStick.getRawButton(7))
            motorTop.set(-1);
        else
            motorTop.set(0);
        }
    public void tickAuto() {
    }
    
}