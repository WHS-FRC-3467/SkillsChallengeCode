// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.BallTower;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunBallTower extends InstantCommand {
    /** Creates a new RunBallTower. */
    BallTowerSubsystem m_ballTower;
    Double m_speed;
    public RunBallTower(BallTowerSubsystem ballTowerSubsystem, double speed) {
      m_speed = speed;
      m_ballTower = ballTowerSubsystem;
    }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ballTower.runBallTower(m_speed);
  }
}
