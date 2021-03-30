// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Subsystems.BallTower.BallTowerSubsystem;
import frc.robot.Subsystems.BallTower.RunBallTower;
import frc.robot.Subsystems.CommandGroups.ProcessBalls;
import frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;
import frc.robot.Subsystems.DriveSubsystem.SplitArcadeDrive;
import frc.robot.Subsystems.Intake.DeployIntake;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Intake.RetractIntake;
import frc.robot.Subsystems.Intake.RunIntake;
import frc.robot.Subsystems.Shooter.RunShooter;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Subsystems.ShooterHood.DeployHood;
import frc.robot.Subsystems.ShooterHood.RetractHood;
import frc.robot.Subsystems.ShooterHood.ShooterHoodSubsystem;
import frc.robot.Subsystems.VHopper.RunVHopper;
import frc.robot.Subsystems.VHopper.VHopperSubsystem;
import frc.robot.control.XBoxControllerTrigger;
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
  private final VHopperSubsystem m_vHopper = new VHopperSubsystem();
  private final BallTowerSubsystem m_ballTower = new BallTowerSubsystem();
   private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final ShooterHoodSubsystem m_shooterHood = new ShooterHoodSubsystem();

  public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

        //Driver Controller
        //Split Arcade: forward/back leftY, right/left rightX
        m_robotDrive.setDefaultCommand(
          new SplitArcadeDrive(m_robotDrive, 
                              () -> m_driverController.getLeftY(),
                              () -> m_driverController.getRightX()));    
        new ProcessBalls(
          m_intake, m_operatorController.getLeftY(),/*Multiply by decimal if needed to reduce speed*/
          m_vHopper, m_operatorController.getLeftY(),/*Multiply by decimal if needed to reduce speed*/
          m_ballTower, m_operatorController.getLeftY()/*Multiply by decimal if needed to reduce speed*/
        );
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
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperLeft)
        .whenPressed(new DeployIntake(m_intake));

        //Retract intake
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperRight)
        .whenPressed(new RetractIntake(m_intake));
 
        //Opperator Controller
        //Intake Runs Forward
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kLeft)
        .whileActiveContinuous(new RunIntake(m_intake, 70.0));

        //Intake -Runs Reverse
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kRight)
        .whileActiveContinuous(new RunIntake(m_intake, -70.0));
        
        //Intake Runs Forward
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kLeft)
        .whileActiveContinuous(new RunVHopper(m_vHopper, 70.0));

        //Intake -Runs Reverse
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kRight)
        .whileActiveContinuous(new RunVHopper(m_vHopper, -70.0));

        
        //Intake Runs Forward
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kLeft)
        .whileActiveContinuous(new RunBallTower(m_ballTower, 10.0));

        //Intake -Runs Reverse
        new XBoxControllerTrigger(m_operatorController, XboxController.Hand.kRight)
        .whileActiveContinuous(new RunVHopper(m_vHopper, -10.0));

        //runs shooter at target velocity on shuffleboard
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
         .whileHeld(new RunShooter(m_shooter, ShooterConstants.kTestVelocity)); 

        //Deploys Hood
        new XboxControllerButton(m_operatorController, XboxController.Button.kX)
        .whenPressed(new DeployHood(m_shooterHood)); 

        //Retracts Hood
        new XboxControllerButton(m_operatorController, XboxController.Button.kY)
        .whenPressed(new RetractHood(m_shooterHood)); 


        
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
