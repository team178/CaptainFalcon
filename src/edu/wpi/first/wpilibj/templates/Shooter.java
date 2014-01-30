/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Enforers
 */
public class Shooter {
    Relay low; 
    Relay medium;
    Relay high;
    Joystick auxStick;
    Timer time;
    boolean completion;
    int[] stage3;
    double initialSetup;
    int level;
    boolean timing;
    AnalogChannel analogPressure;
    double psiCount;
    boolean lock;
    double timeLow;
    double timeHigh;
    int arrayCount3;
    int arrayCount2;
    double analogPressureCoeffecient;
    int[] stage2;
    
    public Shooter(){
        analogPressureCoeffecient = .035;
        low = new Relay(1);
        medium = new Relay(2);
        high = new Relay(3);
        auxStick = new Joystick(2);
        time = new Timer();
        completion = false;
        initialSetup = 0;
        level = 0;
        timing = false;
        analogPressure = new AnalogChannel(2);
        stage3 = new int[3];
        stage2 = new int[2];
        arrayCount3 = 0;
        arrayCount2 = 0;
        stage3[arrayCount3] = 1;
        stage2[arrayCount2] = 1;
    }
    public void shootThreeStage(){
        if(auxStick.getRawButton(2) && !shooterLock()){
            if(stage3[arrayCount3] == 1){
                if(shooterTimer(0, 1000)){
                    low.set(Relay.Value.kForward);
                    stage3[arrayCount3] = 0;
                    arrayCount3++;
                    stage3[arrayCount3] = 1;
                }
            }else if(stage3[arrayCount3] == 1){
                if(shooterTimer(0, 1000)){
                    medium.set(Relay.Value.kForward);
                    stage3[arrayCount3] = 0;
                    arrayCount3++;
                    stage3[arrayCount3] = 1;
                }
            }else if(stage3[arrayCount3] == 1){
                if(shooterTimer(0, 1000)){
                    high.set(Relay.Value.kForward);
                    stage3[arrayCount3] = 0;
                    arrayCount3++;
                    stage3[arrayCount3] = 1;
                }
            }else if(stage3[arrayCount3] == 1){
                if(shooterTimer(0, 1000)){
                    low.set(Relay.Value.kOff);
                    medium.set(Relay.Value.kOff);
                    high.set(Relay.Value.kOff);
                    stage3[arrayCount3] = 0;
                    arrayCount3 = 0;
                    stage3[arrayCount3] = 1;
                }
            }
        }
    }
    
    public void shootTwoStage(){
        if(auxStick.getRawButton(2) && !shooterLock()){
            if(stage2[arrayCount2] == 1){
                if(shooterTimer(0, 1000)){
                    low.set(Relay.Value.kForward);
                    stage2[arrayCount2] = 0;
                    arrayCount2++;
                    stage2[arrayCount2] = 1;
                }
            }else if(stage2[arrayCount2] == 1){
                if(shooterTimer(0, 1000)){
                    medium.set(Relay.Value.kForward);
                    stage2[arrayCount2] = 0;
                    arrayCount2++;
                    stage2[arrayCount2] = 1;
                }
            }else if(stage2[arrayCount2] == 1){
                if(shooterTimer(0, 1000)){
                    high.set(Relay.Value.kForward);
                    stage2[arrayCount2] = 0;
                    arrayCount2++;
                    stage2[arrayCount2] = 1;
                }
            }else if(stage2[arrayCount2] == 1){
                if(shooterTimer(750, 1750)){
                    low.set(Relay.Value.kOff);
                    medium.set(Relay.Value.kOff);
                    high.set(Relay.Value.kOff);
                    stage2[arrayCount2] = 0;
                    arrayCount2 = 0;
                    stage2[arrayCount2] = 1;
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
        psiCount = analogPressure.getVoltage() * analogPressureCoeffecient;
        System.out.println(psiCount);
        if (auxStick.getRawButton(1)) {
            lock = false;
        }else if(psiCount > 80 /*||WoT is true*/){
            lock = true;
        }
        return lock;
    }
}