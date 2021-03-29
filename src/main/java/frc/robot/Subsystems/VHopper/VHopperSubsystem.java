// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.VHopper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanConstants;

public class VHopperSubsystem extends SubsystemBase {
  /** Creates a new VHopperSubsystem. */
  private static TalonSRX m_vHopper1 = new TalonSRX(CanConstants.vHopperMotor1);
  private static TalonSRX m_vHopper2 = new TalonSRX(CanConstants.vHopperMotor2);

  public VHopperSubsystem() {
    m_vHopper2.setInverted(true);
    m_vHopper2.follow(m_vHopper1);
  }
  //Runs Vhopper motors
  public void runVHopper(Double speed) {
    m_vHopper1.set(ControlMode.PercentOutput, speed);
  }


}
