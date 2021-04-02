// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.MultiSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.VHopper.VHopperSubsystem;

public class ProcessBalls extends CommandBase {
  /** Creates a new ProcessBalls. */
  IntakeSubsystem m_intake;
  VHopperSubsystem m_vHopper;
  DoubleSupplier m_speed;
  public ProcessBalls(VHopperSubsystem vHopper, IntakeSubsystem intake, DoubleSupplier speed) {
    intake = m_intake;
    vHopper = m_vHopper;
    speed = m_speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake,m_vHopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vHopper.runVHopper(m_speed.getAsDouble());
    m_intake.driveIntake(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vHopper.runVHopper(0.0);
    m_intake.driveIntake(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
