package org.usfirst.frc.team1803.robot.subsystems;

import org.usfirst.frc.team1803.robot.RobotMap;
import org.usfirst.frc.team1803.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//These are the four motors that make up the drivetrain subsystem.
	//We will group them up later.
	Spark leftFront;
	Spark leftBack;
	Spark rightFront;
	Spark rightBack;
	
	//Each SpeedControllerGroup will hold two motors. We have two
	//groups for the left and right side.
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
	//This connects the two SpeedControllerGroups together, creating one union
	//drivetrain with four motors.
	public DifferentialDrive drivetrain;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
    	//Set the motor variables to the Spark (A type of Speed controller) class.
    	//The input comes from the RobotMap class and contains the port of the controllers.
    	//The ports can be found on the RoboRIO.
        leftFront = new Spark(RobotMap.leftFrontDrivetrainMotor);
    	leftBack = new Spark(RobotMap.leftBackDrivetrainMotor);
    	rightFront = new Spark(RobotMap.rightFrontDrivetrainMotor);
    	rightBack = new Spark(RobotMap.rightBackDrivetrainMotor);
    	
    	//Connect each side of motors together.
    	left = new SpeedControllerGroup(leftFront, leftBack);
    	right = new SpeedControllerGroup(rightFront, rightBack);
    	
    	//Connect everything together.
    	drivetrain = new DifferentialDrive(left, right);
    	
    	//Who cares about safety? (In all seriousness: This is so the robot won't shut
    	//itself down if there is too much power being taken in.)
    	leftFront.setSafetyEnabled(false);
    	leftBack.setSafetyEnabled(false);
    	rightFront.setSafetyEnabled(false);
    	rightBack.setSafetyEnabled(false);
    	drivetrain.setSafetyEnabled(false);
    	
    	//Set the command that runs automatically.
    	//The DriveCommand gets the input for movement.
    	setDefaultCommand(new DriveCommand());
    }
}

