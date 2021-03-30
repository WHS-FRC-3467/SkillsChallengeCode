// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterHood;

import edu.wpi.first.wpilibj2.command.InstantCommand;

/** Add your docs here. */
public class DeployHood extends InstantCommand {
  /** Add your docs here. */
  ShooterHoodSubsystem m_hood;
  
  public DeployHood(ShooterHoodSubsystem shooterHoodSubsystem) {
    m_hood = shooterHoodSubsystem;
    addRequirements(m_hood);
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    m_hood.deployHood();
  }
}
