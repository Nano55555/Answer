/**
 * TODO: there are 2 errors in this file (look for the red squiggly lines)
 * hint - it looks like there's a more errors than there actually are
 **/

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.LimelightConstants;

public class LimelightSubsystem extends CommandBase {
  public static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public static NetworkTableEntry tx = limelightTable.getEntry("tx");
  public static NetworkTableEntry ty = limelightTable.getEntry("ty");

  public static double limelight_x = (Double) tx.getDouble(0.0);
  public static double limelight_y = (Double) ty.getDouble(0.0);

  public LimelightSubsystem() {
    limelightTable.getEntry("ledMode").setNumber(Constants.LimelightConstants.FORCE_OFF);
    limelightTable.getEntry("camMode").setNumber(Constants.LimelightConstants.DRIVER_CAMERA); 
  }

  public static void getLimelightData(){
    limelight_x = (Double) limelightTable.getEntry("tx").getDouble(0.0);
    limelight_y = (Double) limelightTable.getEntry("ty").getDouble(0.0);
  }

  public static double getLimelightX(){
    return (Double) limelightTable.getEntry("tx").getDouble(0.0);
  }

  public static double getLimelightY(){
    return (Double) limelightTable.getEntry("ty").getDouble(0.0);
  }

  public static void turn_LED_ON(){
    limelightTable.getEntry("ledMode").setNumber(LimelightConstants.FORCE_ON);
  }

  public static void turn_LED_OFF(){
    limelightTable.getEntry("ledMode").setNumber(LimelightConstants.FORCE_OFF);
    // TODO: write turn_LED_OFF (hint - look at turn_LED_ON() above!!)

  }

  public static void setVisonProcessor(){
    limelightTable.getEntry("camMode").setNumber(LimelightConstants.VISON_PROCESSOR);
    turn_LED_ON();
  }

  public static void setDriverCamera(){
    limelightTable.getEntry("driverCamera").setNumber(LimelightConstants.DRIVER_CAMERA);
    /** 
     * 
     * TODO: write setDriverCamera (hint - look at setVisionProcessor() above!!)
     * What are the two things that we do in the setVisionProcessor?
     * You need to use a method that wasn't prewritten for you at the start
     **/

  }

  public static double getHorizontalDistance(){
    double targetOffsetAngle_Vertical = getLimelightY();
    double limelightMountAngleDegrees = 30;
    double limelightHeight = 1.0922;
    double goalHeight = 2.6416;
    double angleToGoal = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoal * (Math.PI / 180.0);
    double distance = (goalHeight - limelightHeight) / Math.tan(angleToGoalRadians);
    return distance; 
  }

  public static boolean isWithinDistance(){
    // checks if the bot is within 1.7 and 5 meters from the goal
    /** 
     * TODO: there's a logic issue here, read the comment above and look at the return statement
     * why would this not return what we want?
     **/
    return (getHorizontalDistance() <= 5 && getHorizontalDistance() >= 1.7);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
