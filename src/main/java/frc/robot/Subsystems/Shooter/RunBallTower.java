// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunBallTower extends CommandBase {
  /** Creates a new RunBallTower. */
  Double m_speed;
  ShooterSubsystem m_shooter;
  public RunBallTower(ShooterSubsystem shooterSubsys, double speed)
  {
      m_shooter = shooterSubsys;
      m_speed = speed;
      addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.runBallTower(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.runBallTower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
