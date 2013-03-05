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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar shooterFrontWheel = RobotMap.shooterFrontWheel;
    CANJaguar shooterBackWheel = RobotMap.shooterBackWheel;
    CANJaguar shooterAngle = RobotMap.shooterAngleControl;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static final double ANGLESPEEDCONSTANT = 1.0;
    AnalogChannel shooterElevationAngleSensor = RobotMap.shooterAngleSensor;

    // Constants to define the minimum and maximum angles for the shooter.
    // TODO: These must be updated once we make the measurements
    // This is the minimum voltage from the sensor when the shooter is at
    // it's minimum angle
    
    // This is the minimum angle of the shooter.
    private final double MIN_ANGLE = 20;
    
    private final double MAX_VOLTAGE = 4.91;
    private final double MIN_VOLTAGE = 0.0;
    private final double POT_RANGE = 300;
    // The zero point of the POT is 150 on the prototype
    private final double MIN_POT_VALUE = 150;

    private final double MAX_ANGLE = 50;
    private final double VOLTAGE_RANGE = MAX_VOLTAGE - MIN_VOLTAGE;
    private final double ANGLE_RANGE = MAX_ANGLE - MIN_ANGLE;
    int m_count = 10;
    public double currentShooterAngle;
    public double shooterElevationVoltage;
    
    // Current Shooter Angle
    // returns the current shooter angle in radians
    public void shooterElevationAngle() {
        shooterElevationVoltage = RobotMap.shooterAngleSensor.getAverageVoltage();
        // clip the small negative values we get when the POT is at zero
        if (shooterElevationVoltage < 0.0) {shooterElevationVoltage = 0.0;}
        
        // WE DON'T TRY TO ADD MIN_ANGLE HERE BECAUSE WE DON'T KNOW WHERE
        // THE ZERO POINT OF THE POT ACTUALLY IS. So for now, show raw angle.
        //    - Jared, Feb 28
        currentShooterAngle = (POT_RANGE/VOLTAGE_RANGE)*shooterElevationVoltage -
                MIN_POT_VALUE;
        }
            
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void Shooter() {
        System.out.println("Shooter constructed.");
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runShooterAngle(double y) {
        try {
            shooterAngle.setX(y*ANGLESPEEDCONSTANT);
            if (--m_count==0)
            {
                m_count = 10;
                //Robot.debugOutNumber("Shooter energy", y*ANGLESPEEDCONSTANT);
                //Robot.debugOutNumber("Elevation angle", currentShooterAngle);
            }
        } catch (edu.wpi.first.wpilibj.can.CANTimeoutException ex) {
            System.out.println("Timeout Exception on shooterAngle.setX in runShooterAngle");
        }
    }
    

    
}

