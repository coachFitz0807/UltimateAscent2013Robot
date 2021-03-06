/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

/**
 *
 * @author fitzpaj
 */
public class ShooterToPickupHeight extends Command {
    
    // TODO: Set this to the right value after the robot is built
    private final double ANGLE_FOR_FEEDER = 25;
    // Used to control the direction of the motor
    // The value of 1 will make the motor go full speed.
    // If we want to go slower, then set it to a value > 0 and < 1
    private int direction = 1;
    private final double FEED_ANGLE_THRESHOLD = 1;

    public ShooterToPickupHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if ( Robot.shooter.currentShooterAngle > ANGLE_FOR_FEEDER ) {
            direction = -1;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.runShooterAngle(direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // Check to see if we're close enough.
        return (direction * (ANGLE_FOR_FEEDER - Robot.shooter.currentShooterAngle)) < FEED_ANGLE_THRESHOLD;
    }

    // Called once after isFinished returns true
    protected void end() {
        // stop the shooter angle motor
        Robot.shooter.runShooterAngle(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
