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
    double timeLow;
    double timeHigh;
    int arrayCount;

        
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
        stage = new boolean[3];
        arrayCount = 0;
    }
    public void shootThreeStage(){
        if(auxStick.getRawButton(2) && !shooterLock()){
            if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    low.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    medium.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    high.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    low.set(false);
                    medium.set(false);
                    high.set(false);
                    arrayCount = 0;
                }
            }
        }
    }
    
    public void shootTwoStage(){
        if(auxStick.getRawButton(2) && !shooterLock()){
            if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    low.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    medium.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(0, 1000)){
                    high.set(true);
                    arrayCount++;
                }
            }else if(stage[arrayCount]){
                if(shooterTimer(750, 1750)){
                    low.set(false);
                    medium.set(false);
                    high.set(false);
                    arrayCount = 0;
                }
            }
        }
    }
    
    public boolean shooterTimer(double timeow, double timehigh){
        if(completion == false){
            time.start();
            completion = true;
        }
        if(level <= 2){
            if(time.get() >= timeLow && time.get() <= timeHigh){
                timing = true;
                
            }
        }
        return timing;
    }
    
    public boolean shooterLock(){
        System.out.println(psiCount);
        if (auxStick.getRawButton(1)) {
            lock = false;
        }else if(psiCount > 80 /*||WoT is true*/){
            lock = true;
        }
        return lock;
    }
}