// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;
import frc.robot.Subsystems.DriveSubsystem.SplitArcadeDrive;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Intake.ProcessBalls;
import frc.robot.Subsystems.Shooter.RunBallTower;
import frc.robot.Subsystems.Shooter.RunShooter;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.control.XBoxControllerDPad;
import frc.robot.control.XboxController;
import frc.robot.control.XboxControllerButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();

  public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Driver Controller
    // Split Arcade: forward/back leftY, right/left rightX
    m_robotDrive.setDefaultCommand(
        new SplitArcadeDrive(m_robotDrive, 
                            () -> m_driverController.getLeftY(), 
                            () -> m_driverController.getRightX()));

    m_intake.setDefaultCommand(new ProcessBalls(m_intake, 
                                                () -> m_operatorController.getLeftY()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {  

        //Driver Controller
        //Deploy intake
        new XboxControllerButton(m_driverController, XboxController.Button.kBumperLeft)
        .whileActiveContinuous(new InstantCommand(m_intake::deployIntake, m_intake));

        //Retract intake
        new XboxControllerButton(m_driverController, XboxController.Button.kBumperRight)
        .whileActiveContinuous(new InstantCommand(m_intake::retractIntake, m_intake));
        
        //Opperator Controller

        //green velocity
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
        .whileHeld(new RunShooter(m_shooter, ShooterConstants.kGreenVelocity)); 
        // yellow velocity
        new XboxControllerButton(m_operatorController, XboxController.Button.kY)
        .whileHeld(new RunShooter(m_shooter, ShooterConstants.kYellowVelocity)); 
        //blue velocity
        new XboxControllerButton(m_operatorController, XboxController.Button.kX)
        .whileHeld(new RunShooter(m_shooter, ShooterConstants.kBlueVelocity)); 
        //red
        new XboxControllerButton(m_operatorController, XboxController.Button.kB)
        .whileHeld(new RunShooter(m_shooter, ShooterConstants.kRedVelocity)); 
        
        //run ball tower up
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperLeft)
        .whileHeld(new RunBallTower(m_shooter, 50.0)); 
        
        //run ball tower down
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperRight)
        .whileHeld(new RunBallTower(m_shooter, -50.0)); 
        
        // hood out
	    	new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadUp)
        .whileActiveContinuous(new InstantCommand(m_shooter::deployHood, m_shooter)); 
        
        //hood in
	    	new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadDown)
        .whileActiveContinuous(new InstantCommand(m_shooter::retractHood, m_shooter)); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
