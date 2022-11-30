// TODO: there are 2 errors in this file (look for the red squiggly lines)

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class IntakeSubsystem extends SubsystemBase {

  private static final WPI_TalonFX intakeMotor = RobotMap.intakeMotor;

  public IntakeSubsystem(){
    intakeMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void setIntakeSpeed(double intakeSpeed){
    intakeMotor.set(intakeSpeed);
  }

  public void stop(){
    setIntakeSpeed(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
