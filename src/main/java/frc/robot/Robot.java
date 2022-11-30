// TROUBLESHOOTING PRACTICE INSTRUCTIONS
/** 
 * Look at the todo statements written at the top of each file (if there is one)
 * Keep working through each of the tasks until you have none left
 * Let a veteran know when you're done with your code :>
 * First person to correct all of the code wins (bragging rights i guess LOL)
 * 
 * It's generally recommended to work through the errors with the red lines because those are 
 * more critical errors, but there are a few errors that are related to logic that wouldn't
 * produce the results we want. 
 * 
 * You'll also be writing some code, though don't worry because they're relatively easy to write.
 * 
 * Check for any missing imports, conditional statement logic, typos, capitalization issues, 
 * difference between public and private variables, AKA the stuff we've gone over in past meetings
 * whenever we had an error.
 * 
 * Good luck and ask questions if you don't understand something!! Please don't just copy what 
 * someone else did just because it works. (That defeats the whole purpose of us spending time
 * to do this 💀)
 **/



/**
 * TODO: there are 2 errors in this file (look for the red squiggly lines) 
 * for these errors, you have to fix something in another file 
 * hint - why would limelightSubsystem not give an error but intake and drive subsystems do?
 **/

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private DriveSubsystem driveSubsystem = m_robotContainer.driveSubsystem;

  private IntakeSubsystem intakeSubsystem = m_robotContainer.intakeSubsystem;

  private LimelightSubsystem limelightSubsystem = m_robotContainer.limelightSubsystem;

  private final WPI_TalonFX backLeftMotor = RobotMap.backLeftMotor;

  private final WPI_TalonFX backRightMotor = RobotMap.backRightMotor; 

  private final WPI_TalonFX frontLeftMotor = RobotMap.frontLeftMotor;

  private final WPI_TalonFX frontRightMotor = RobotMap.frontRightMotor;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    limelightSubsystem.turn_LED_ON();
    limelightSubsystem.setVisonProcessor();
    // TODO: call setVisionProcessor() from limelightSubsystem here VV
    

    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(240, 180);
    camera.setFPS(8);

    driveSubsystem.resetEncoders();
    // TODO: call resetEncoders from driveSubsystem here VV

  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Left Back F500 Temp C" , backLeftMotor.getTemperature());
    SmartDashboard.putNumber("Right Back F500 Temp C", backRightMotor.getTemperature());
    
    SmartDashboard.putNumber("Back Left Drive MPS",backLeftMotor.getSelectedSensorPosition());

    SmartDashboard.putBoolean("WITHIN 1.7 TO 5 METERS?", limelightSubsystem.isWithinDistance());
    SmartDashboard.putNumber("LIMELIGHT OFFESET", limelightSubsystem.getLimelightX());
    SmartDashboard.putNumber("Horizontal Distance", limelightSubsystem.getHorizontalDistance());

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
