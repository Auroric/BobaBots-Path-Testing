package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Drivetrain.AutoDriveTimed;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Elevator.ElevatorSubsystem;
import frc.robot.Intake.IntakeSubsystem;
import frc.robot.OI.OI;

public class Robot extends TimedRobot {

  public static OI oi;
  public static Drivetrain drivetrain;
  public static IntakeSubsystem intake;
  public static ElevatorSubsystem elevator;

  UsbCamera camera;
  public String gameData;

  @Override
  public void robotInit() {
    intake = IntakeSubsystem.getInstance();
    elevator = ElevatorSubsystem.getInstance();
    drivetrain = Drivetrain.getInstance();
    oi = new OI();

    camera = CameraServer.getInstance().startAutomaticCapture(0);

    camera.setResolution(320, 240);
    camera.setFPS(15);

  }

  @Override
  public void disabledInit() {
    Drivetrain.resetEncoders();
    Drivetrain.resetGyro();
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
      //new AutoDriveTimed().start();

      new PathFollower("Straight15ft").start();

      gameData = DriverStation.getInstance().getGameSpecificMessage();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit(){
    Drivetrain.setBrakeMode();

  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }

  public void disabledPeriodic(){
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    SmartDashboard.putString("gameData", gameData);
  }
}
