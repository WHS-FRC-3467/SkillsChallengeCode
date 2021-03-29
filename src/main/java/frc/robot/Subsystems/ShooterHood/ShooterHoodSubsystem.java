// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterHood;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterHoodSubsystem extends SubsystemBase {
  /** Creates a new ShooterHood. */
  private DoubleSolenoid m_hood = new DoubleSolenoid(Constants.PneumaticConstants.kHoodDeploy, Constants.PneumaticConstants.kHoodRetract);
  public ShooterHoodSubsystem() {}
  
  //Puts hood into use position
  public void deployHood()
  {
      m_hood.set(Value.kForward);
  }
  //Puts hood into store position
  public void retractHood() 
  {
      m_hood.set(Value.kReverse);
  }

}
