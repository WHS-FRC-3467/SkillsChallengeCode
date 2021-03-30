// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.CommandGroups;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Subsystems.BallTower.BallTowerSubsystem;
import frc.robot.Subsystems.BallTower.RunBallTower;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Intake.RunIntake;
import frc.robot.Subsystems.VHopper.RunVHopper;
import frc.robot.Subsystems.VHopper.VHopperSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ProcessBalls extends ParallelCommandGroup {
  /** Creates a new ProcessBalls. */

  public ProcessBalls(IntakeSubsystem intake, Double intakeSpeed, VHopperSubsystem vHopper, Double vHopperSpeed, BallTowerSubsystem ballTower, Double ballTowerSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addRequirements(intake, vHopper, ballTower);
    
    addCommands(
      new RunIntake(intake, intakeSpeed),
      new RunVHopper(vHopper, vHopperSpeed),
      new RunBallTower(ballTower, ballTowerSpeed)
    );
  }
}
