package frc.team3467.robot2021.Subsystems.Shooter;

/**
 * Add your docs here.
 */
public interface SpeedControl
{
    public void updateGains(double kP, double kI, double kD, double kF);
    public int runVelocityPIDF(double targetVelocity);
    public int getError();
    public double getOutputPercent();
    public void stop();
}
