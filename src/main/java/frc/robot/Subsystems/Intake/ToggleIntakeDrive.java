package frc.robot.Subsystems.Intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntakeDrive extends InstantCommand {
  IntakeSubsystem m_intake;
  public ToggleIntakeDrive(final IntakeSubsystem intakeSubsys) {
    m_intake = intakeSubsys;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    if (m_intake.isIntakeDeployed()){
      m_intake.retractIntake();
      m_intake.driveIntake(0.0);

    } else {
      m_intake.deployIntake();
      m_intake.driveIntake(1.0);
    }
  }

}