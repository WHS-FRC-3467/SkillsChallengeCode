// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.BallTower;

import edu.wpi.first.wpilibj2.command.CommandBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunBallTower extends CommandBase {
    /** Creates a new RunBallTower. */
    BallTowerSubsystem m_ballTower;
    Double m_speed;
    public RunBallTower(BallTowerSubsystem ballTowerSubsystem, Double speed) {
      m_speed = speed;
      m_ballTower = ballTowerSubsystem;
      addRequirements(m_ballTower);
    }
  

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
      m_ballTower.runBallTower(m_speed);
  }
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
