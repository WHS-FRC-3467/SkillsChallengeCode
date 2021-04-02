// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.MultiSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.BallTower.BallTowerSubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Subsystems.ShooterHood.ShooterHoodSubsystem;

public class ShootBall extends CommandBase {
  /** Creates a new ShootBall. */
  BallTowerSubsystem m_ballTower;
  ShooterSubsystem m_shooter; 
  ShooterHoodSubsystem m_hood;
  Boolean m_useHood; 
  Double m_shooterSpeed;
  public ShootBall(BallTowerSubsystem ballTower, ShooterSubsystem shooter, ShooterHoodSubsystem hood, Boolean useHood, Double shooterSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
     m_ballTower = ballTower;
     m_shooter = shooter; 
     m_hood = hood;
     m_useHood = useHood; 
     m_shooterSpeed = shooterSpeed;
     addRequirements(m_ballTower, m_shooter, m_hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_useHood = true){
      if (m_hood.isHoodDeployed() != true){
        m_hood.deployHood();
      }
    }
    else{
      if (m_hood.isHoodDeployed()){
        m_hood.retractHood();
      }
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.runShooter(m_shooterSpeed);
    m_ballTower.runBallTower(10.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.runShooter(0.0);
    m_ballTower.runBallTower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
