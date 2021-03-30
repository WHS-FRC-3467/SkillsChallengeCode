// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.VHopper;

import edu.wpi.first.wpilibj2.command.CommandBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunVHopper extends CommandBase {
  Double m_speed;
  VHopperSubsystem m_vHopper;
  public RunVHopper(VHopperSubsystem VHopper, double speed) {
    m_vHopper = VHopper;
    m_speed = speed;
    addRequirements(m_vHopper);
  }


  // Called when the command is initially scheduled.
  @Override
  public void execute(){
    m_vHopper.runVHopper(m_speed);
  }

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
