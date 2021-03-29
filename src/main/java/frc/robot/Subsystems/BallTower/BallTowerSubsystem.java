// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.BallTower;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanConstants;

public class BallTowerSubsystem extends SubsystemBase {
  /** Creates a new BallTowerSubsystem. */
  private static TalonSRX m_ballTowerMotor = new TalonSRX(CanConstants.ballTowerMotor);

  public BallTowerSubsystem() {}

  //runs the ball tower motors
  public void runBallTower (Double speed){
    m_ballTowerMotor.set(ControlMode.PercentOutput, speed);
  }

  
}
