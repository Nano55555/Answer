/**
 * TODO: there are 3 errors in this file (look for the red squiggly lines)
 * hint - it looks like there's a lot more errors than there actually are
 **/

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
  private static final WPI_TalonFX backLeftMotor = RobotMap.backLeftMotor;
  private static final WPI_TalonFX backRightMotor = RobotMap.backRightMotor;
  private static final WPI_TalonFX frontLeftMotor = RobotMap.frontLeftMotor;
  private static final WPI_TalonFX frontRightMotor = RobotMap.frontRightMotor;

  private static final double IN_TO_M = .0254;
  private static final int MOTOR_ENCODER_CODES_PER_REV = 2048;
  private static final double DIAMETER_INCHES = 5.0;
  private static final double WHEEL_DIAMETER = DIAMETER_INCHES * IN_TO_M;
  private static final double WHEEL_CIRCUMFRENCE = WHEEL_DIAMETER * Math.PI;
  private static final double GEAR_RATIO = 12.75;
  private static final double TICKS_PER_METER = (MOTOR_ENCODER_CODES_PER_REV * GEAR_RATIO) / WHEEL_CIRCUMFRENCE;
  private static final double METERS_PER_TICKS = 1 / TICKS_PER_METER;

  public DriveSubsystem() {
    frontLeftMotor.set(ControlMode.Follower, backLeftMotor.getDeviceID());
    frontRightMotor.set(ControlMode.Follower, backRightMotor.getDeviceID());

    frontLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
    frontRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
    backLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
    backRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);

    frontLeftMotor.configNominalOutputForward(0, 10);
    frontLeftMotor.configNominalOutputReverse(0, 10);
    frontLeftMotor.configPeakOutputForward(1, 10);
    frontLeftMotor.configPeakOutputReverse(-1, 10);
    frontLeftMotor.configNeutralDeadband(0.001, 10);

    frontRightMotor.configNominalOutputForward(0, 10);
    frontRightMotor.configNominalOutputReverse(0, 10);
    frontRightMotor.configPeakOutputForward(1, 10);
    frontRightMotor.configPeakOutputReverse(-1, 10);
    frontRightMotor.configNeutralDeadband(0.001, 10);

    backLeftMotor.configNominalOutputForward(0, 10);
    backLeftMotor.configNominalOutputReverse(0, 10);
    backLeftMotor.configPeakOutputForward(1, 10);
    backLeftMotor.configPeakOutputReverse(-1, 10);
    backLeftMotor.configNeutralDeadband(0.001, 10);

    backRightMotor.configNominalOutputForward(0, 10);
    backRightMotor.configNominalOutputReverse(0, 10);
    backRightMotor.configPeakOutputForward(1, 10);
    backRightMotor.configPeakOutputReverse(-1, 10);
    backRightMotor.configNeutralDeadband(0.001, 10);

    // Sets how much error is allowed
    frontLeftMotor.configAllowableClosedloopError(0, 0, 10);
    backLeftMotor.configAllowableClosedloopError(0, 0, 10);
    frontRightMotor.configAllowableClosedloopError(0, 0, 10);
    backRightMotor.configAllowableClosedloopError(0, 0, 10);

    frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    backLeftMotor.setNeutralMode(NeutralMode.Coast);
    frontRightMotor.setNeutralMode(NeutralMode.Coast);
    backRightMotor.setNeutralMode(NeutralMode.Coast);

    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);
    backLeftMotor.setInverted(true);
    backRightMotor.setInverted(false);
  } 
  
  public double getbackLeftMotorEncoder(){
    return backLeftMotor.getSelectedSensorPosition();
  }
  public double getbackFrontMotorEncoder(){
    return backRightMotor.getSelectedSensorPosition();
  }
  public double getfrontLeftMotorEncoder(){
    return frontLeftMotor.getSelectedSensorPosition();
  }
  public double getfrontRightMotorEncoder(){
    return frontRightMotor.getSelectedSensorPosition();
  }

  /** 
   * TODO: write code to get the encoder counts for all 4 motors VV
   * (the first one is already done for back left motor)
   **/ 



  public void resetEncoders(){
    frontLeftMotor.setSelectedSensorPosition(0);
    frontRightMotor.setSelectedSensorPosition(0);
    backLeftMotor.setSelectedSensorPosition(0);
    backRightMotor.setSelectedSensorPosition(0);
  }

  public static void drive(double throttle, double rotate){
    backLeftMotor.set(throttle + rotate);
    backRightMotor.set(throttle - rotate);
  }

  public void stop(){
    drive(0,0);
  }

  @Override
  public void periodic() {

  }
}