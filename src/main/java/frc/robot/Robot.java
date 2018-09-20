package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.OI.OI;

public class Robot extends TimedRobot {

  public static OI oi;
  public static Drivetrain drivetrain;

  @Override
  public void robotInit() {
    drivetrain = Drivetrain.getInstance();
    oi = new OI();
  }

  @Override
  public void disabledInit() {
    Scheduler.getInstance().removeAll();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Motor Output", Drivetrain.leftMotorA.getMotorOutputPercent());
    SmartDashboard.putNumber("Right Motor Output", Drivetrain.rightMotorA.getMotorOutputPercent());

    SmartDashboard.putNumber("Raw Gyro Heading", Drivetrain.gyro.getAngle());
    SmartDashboard.putNumber("Right Encoder", Drivetrain.rightMotorA.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Left Encoder", Drivetrain.leftMotorA.getSelectedSensorPosition(0));

  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
