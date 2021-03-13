// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3467.robot2021.Subsystems.VHopper;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunVHopper extends CommandBase {
  /** Creates a new RunVHopper. */
  VHopperSubsystem m_vHopper;
  DoubleSupplier m_speed;
  public RunVHopper(VHopperSubsystem vHopperSubsystem, DoubleSupplier speed) {
  m_speed = speed;
  m_vHopper = vHopperSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vHopper.runVHopper(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vHopper.runVHopper(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
