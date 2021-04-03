// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Subsystems.BallTower.BallTowerSubsystem;
import frc.robot.Subsystems.BallTower.RunBallTower;
import frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;
import frc.robot.Subsystems.DriveSubsystem.SplitArcadeDrive;
import frc.robot.Subsystems.Intake.DeployIntake;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Intake.ProcessBalls;
import frc.robot.Subsystems.Intake.RetractIntake;
import frc.robot.Subsystems.Shooter.DeployHood;
import frc.robot.Subsystems.Shooter.RetractHood;
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
  private final BallTowerSubsystem m_ballTower = new BallTowerSubsystem();
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
                                                () -> m_operatorController.getLeftTrigger()));
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
        .whenPressed(new DeployIntake(m_intake));

        //Retract intake
        new XboxControllerButton(m_driverController, XboxController.Button.kBumperRight)
        .whenPressed(new RetractIntake(m_intake));
 
        //Opperator Controller

        //runs shooter at test velocity
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
        .whileHeld(new RunShooter(m_shooter, ShooterConstants.kTestVelocity)); 
        
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperRight)
        .whenPressed(new RunBallTower(m_ballTower, 70.0));
        
	    	new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadUp)
        .whileActiveOnce(new DeployHood(m_shooter)); 
        
	    	new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadUp)
        .whileActiveOnce(new RetractHood(m_shooter)); 
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
