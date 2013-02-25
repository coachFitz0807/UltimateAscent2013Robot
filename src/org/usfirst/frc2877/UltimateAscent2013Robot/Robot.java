// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Acquisition acquisition;
    public static Climb climb;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Drive drive;
    public ShooterElevationControl shooterElevationControl;
    public AcquisitionScrewControl acquisitionScrewControl;
    public static int m_count=10;
    public static int m_total_ticks = 0;
    public static boolean m_shooter_enable = false;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        acquisition = new Acquisition();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drive = new Drive();

        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // We need to initialize the disk positions
        Acquisition.diskPositions[3] = true;
        Acquisition.diskPositions[2] = true;
        Acquisition.diskPositions[1] = true;
        Acquisition.diskPositions[3] = false;
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
     }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        System.out.println("teleopInit");
        if (autonomousCommand != null) autonomousCommand.cancel();
        // start the drive command.  This will remain active during teleop
        drive.start();
        // create and start the shooterElevationControl command 
       
        shooterElevationControl = new ShooterElevationControl();
        shooterElevationControl.start();

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // Check to see where the disks are
        Robot.acquisition.refreshValues();
        // check the shooter elevation angle
        Robot.shooter.shooterElevationAngle();
       
        try {
            if (m_shooter_enable)
            {
                RobotMap.shooterFrontWheel.setX(-1.0);
                RobotMap.shooterBackWheel.setX(-0.5);
            }
        } catch (Exception ex) {
            System.out.println("Shooter motors speed set failed");
        }
        m_total_ticks++;
        Scheduler.getInstance().run();
        if (--m_count==0)
        {
            m_count = 10;
            //SmartDashboard.putNumber("Ticks", m_total_ticks);
            //Robot.debugOut("Shooter enabled: ", m_shooter_enable ? "true" : "false");
            Robot.debugOutNumber("Pot Avg Voltage ", RobotMap.shooterAngleSensor.getAverageVoltage());
            //Robot.debugOut("Limit switch", RobotMap.acquisitionRotaryLimitSwitch.get() ? "true" : "false");
           // Robot.debugOutNumber("analog switch Test1 ", RobotMap.analogSwitchTest1.getAverageVoltage());
           // Robot.debugOutNumber("analog switch Test2 ", RobotMap.analogSwitchTest2.getAverageVoltage());
         }
        
       }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public static boolean getRotaryLimitSwitch()
    {
        boolean limHit = (RobotMap.rotaryLimitSwitchAnalog.getVoltage() < 4.0);
        if (RobotMap.acquisitionRotaryLimitSwitch.get() == true) {
            limHit = true;
        }
        debugOutNumber("Rotary limit analog", RobotMap.rotaryLimitSwitchAnalog.getVoltage());
        debugOutBoolean("Rotory limit switch", limHit);
        return limHit;
    }
    
    public static void debugOut(String label, String value)
    {
        System.out.println(label + " " + value);
        SmartDashboard.putString(label, value);
    }
    
    public static void debugOutBoolean(String label, boolean value)
    {
        System.out.println(label + " " + (value ? "true" : "false"));
        SmartDashboard.putBoolean(label, value);
    }

    public static void debugOutNumber(String label, double number)
    {
        System.out.println(label + " " + number);
        SmartDashboard.putNumber(label, number);
    }
}
