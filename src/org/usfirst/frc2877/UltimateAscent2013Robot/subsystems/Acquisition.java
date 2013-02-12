// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.Encoder.PIDSourceParameter;
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Acquisition extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Relay acquisitionRoller = RobotMap.acquisitionAcquisitionRoller;
    CANJaguar acquisitionScrewlift = RobotMap.acquisitionAcquisitionScrewlift;
    Encoder acquisitionScrewEncoder = RobotMap.acquisitionAcquisitionScrewEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    int numIrSensors = 4;
    
    public AnalogChannel[] infraRedSensor = new AnalogChannel[numIrSensors];
    public boolean[] slotFull = new boolean[numIrSensors];
    double voltageThreshold = 2.0;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Acquisition() {
        for (int i = 0; i < infraRedSensor.length; i++) {
            infraRedSensor[i] = new AnalogChannel(i);
        }
        
    }
    public void checkSlots() {
        for (int i = 0; i < infraRedSensor.length; i++) {
            slotFull[i] = (infraRedSensor[i].getVoltage() > voltageThreshold);
        }
        
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

