// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3467.robot2021.Subsystems.ShooterHood;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RetractHood extends InstantCommand {
  ShooterHoodSubsystem m_hood;
  
  public RetractHood(ShooterHoodSubsystem shooterHoodSubsystem) {
    super();
    m_hood = shooterHoodSubsystem;
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    m_hood.retractHood();
  }
}
