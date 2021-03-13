package frc.team3467.robot2021.Subsystems.Intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntake extends InstantCommand {
  IntakeSubsystem m_intake;
  public ToggleIntake(final IntakeSubsystem intakeSubsys) {
    m_intake = intakeSubsys;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    //Activating will cause the intake to switch positions
    if (m_intake.isIntakeDeployed()){
      m_intake.retractIntake();
      m_intake.driveIntake(0.0);
    } else {
      m_intake.deployIntake();
    }
  }

}
