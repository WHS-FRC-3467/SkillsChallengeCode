// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.VHopper;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunVHopper extends InstantCommand {
  Double m_speed;
  VHopperSubsystem m_vHopper;
  public RunVHopper(VHopperSubsystem VHopper, double speed) {
    m_vHopper = VHopper;
    m_speed = speed;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    m_vHopper.runVHopper(m_speed);
  }
  
}
