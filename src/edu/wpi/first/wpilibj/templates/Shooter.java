/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Enforers
 */
public class Shooter {
    Solenoid low; 
    Solenoid medium;
    Solenoid high;
    Joystick auxStick;
    Timer time;
    DigitalInput highPressureSensor;
    boolean completion;
    boolean[] stage;
    double initialSetup;
    int level;
    boolean timing;
    AnalogChannel analogPressure;
    double psiCount;
    boolean lock;

        
    public Shooter(){
        low = new Solenoid(1);
        medium = new Solenoid(2);
        high = new Solenoid(3);
        auxStick = new Joystick(2);
        time = new Timer();
        highPressureSensor = new DigitalInput(10);
        completion = false;
        initialSetup = 0;
        level = 0;
        timing = false;
        analogPressure = new AnalogChannel(5);

    }
    public void shootThreeStage(){
        if(auxStick.getRawButton(2) && !shooterLock()){
            if(true){
            low.set(true);
            }
            if(true);
            medium.set(true);
            //TIMER
            high.set(true);
            //TIMER
            low.set(false);
            medium.set(false);
            high.set(false);
        }
    }
    
    public void shootTwoStage(){
        
    }
    
    public boolean shooterTimer(){
        if(completion == false){
            time.start();
            completion = true;
        }
        if(level <= 2){
            if(time.get()-initialSetup >= 1000 && time.get()-initialSetup <= 1050){
                
            }
        }
        return timing;
    }
    
    public boolean shooterLock(){
        System.out.println(psiCount);
        if (psiCount < 80) {
            lock = true;
        }else if(true){
            
        }
        return lock;
    }
}
