package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
