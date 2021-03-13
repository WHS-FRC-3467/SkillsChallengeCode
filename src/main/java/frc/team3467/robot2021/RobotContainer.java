// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3467.robot2021;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team3467.robot2021.Constants.OIConstants;
import frc.team3467.robot2021.Subsystems.BallTower.BallTowerSubsystem;
import frc.team3467.robot2021.Subsystems.BallTower.RunBallTower;
import frc.team3467.robot2021.Subsystems.DriveSubsystem.DriveSubsystem;
import frc.team3467.robot2021.Subsystems.DriveSubsystem.SplitArcadeDrive;
import frc.team3467.robot2021.Subsystems.Intake.DeployIntake;
import frc.team3467.robot2021.Subsystems.Intake.IntakeSubsystem;
import frc.team3467.robot2021.Subsystems.Intake.RetractIntake;
import frc.team3467.robot2021.Subsystems.Intake.RunIntake;
import frc.team3467.robot2021.Subsystems.Shooter.RunShooter;
import frc.team3467.robot2021.Subsystems.Shooter.ShooterSubsystem;
import frc.team3467.robot2021.Subsystems.ShooterHood.DeployHood;
import frc.team3467.robot2021.Subsystems.ShooterHood.RetractHood;
import frc.team3467.robot2021.Subsystems.ShooterHood.ShooterHoodSubsystem;
import frc.team3467.robot2021.Subsystems.VHopper.RunVHopper;
import frc.team3467.robot2021.Subsystems.VHopper.VHopperSubsystem;
import frc.team3467.robot2021.control.XBoxControllerTrigger;
import frc.team3467.robot2021.control.XboxController;
import frc.team3467.robot2021.control.XboxControllerButton;

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

    m_robotDrive.setDefaultCommand(

        //Driver Controller
        //Split Arcade: forward/back leftY, right/left rightX
        new SplitArcadeDrive(m_robotDrive, 
                            () -> m_driverController.getLeftY(),
                            () -> m_driverController.getRightX()));

        //Operator Controller
        //Runs Vhopper in/out Left Y
        new RunVHopper(m_vHopper, 
                      () -> m_operatorController.getLeftY());

        //Runs Ball Tower up left Trigger Reverse for easy opperation
        new RunBallTower(m_ballTower,
                        () -> m_operatorController.getLeftTrigger());

        //Runs Ball tower up/down right Y for testing/unjaming
        new RunBallTower(m_ballTower, 
                        () -> m_operatorController.getLeftY());

        
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
        new XboxControllerButton(m_driverController, XboxController.Button.kA)
        .whenPressed(new DeployIntake(m_intake));

        //Retract intake
        new XboxControllerButton(m_driverController, XboxController.Button.kB)
        .whenPressed(new RetractIntake(m_intake));

        //Intake Runs Forward
        new XBoxControllerTrigger(m_driverController, XboxController.Hand.kLeft)
        .whileActiveContinuous(new RunIntake(m_intake, 70.0));

        //Intake Runs Reverse
        new XBoxControllerTrigger(m_driverController, XboxController.Hand.kRight)
        .whileActiveContinuous(new RunIntake(m_intake, -70.0));

        //Opperator Controller
        //runs shooter at target velocity on shuffleboard
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
        .whileHeld(new RunShooter(m_shooter, SmartDashboard.getNumber("TargetVelocity", 1000))); 

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
