// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3467.robot2021.Subsystems.BallTower;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunBallTower extends CommandBase {
  /** Creates a new RunBallTower. */
  BallTowerSubsystem m_ballTower;
  DoubleSupplier m_speed;
  public RunBallTower(BallTowerSubsystem ballTowerSubsystem, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_speed = speed;
    m_ballTower = ballTowerSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ballTower.runBallTower(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballTower.runBallTower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
