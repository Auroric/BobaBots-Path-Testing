package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Drivetrain.Drivetrain;
import jaci.pathfinder.Pathfinder;

public class PathFollower extends Command{

    private double kP = 0, kI = 0, kD = 0, kV = 1/2.872716583788768, kA = 0;

    Trajectory trajecLeft, trajecRight;
    EncoderFollower followerLeft, followerRight;

    private static double kWheelDiameter = 0.1524;
    private static double kWheelbase = 0.59817;

    public PathFollower(String pathName){

        requires(Robot.drivetrain);

        trajecLeft = Pathfinder.readFromCSV(new File("/home/lvuser/profiles/" + pathName + "_left.csv"));
        trajecRight = Pathfinder.readFromCSV(new File("/home/lvuser/profiles/" + pathName + "_right.csv"));
    }

    public PathFollower(Waypoint[] points){

        requires(Robot.drivetrain);

        Trajectory.Config pointsConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, 0.02, 2, 2, 15);
        Trajectory pointsTrajec = Pathfinder.generate(points, pointsConfig);

        TankModifier modifier = new TankModifier(pointsTrajec);
        modifier.modify(kWheelbase);

        trajecLeft = modifier.getLeftTrajectory();
        trajecRight = modifier.getRightTrajectory();
    }

    protected void initialize(){
        Drivetrain.resetGyro();
        Drivetrain.resetEncoders();

    }

    protected void execute() {

        followerLeft = new EncoderFollower(trajecLeft);
        followerRight = new EncoderFollower(trajecRight);

        followerLeft.configureEncoder(Drivetrain.leftMotorA.getSelectedSensorPosition(0),
                4517, kWheelDiameter);
        followerRight.configureEncoder(Drivetrain.rightMotorA.getSelectedSensorPosition(0),
                4517, kWheelDiameter);

        followerLeft.configurePIDVA(kP, kI, kD, kV, kA);
        followerRight.configurePIDVA(kP, kI, kD, kV, kA);

        double left = followerLeft.calculate(Drivetrain.leftMotorA.getSelectedSensorPosition(0));
        double right = followerRight.calculate(Drivetrain.rightMotorA.getSelectedSensorPosition(0));

        double gyroHeading = -Drivetrain.gyro.getAngle(); //Inverts gyro to make it left hand positive like Pathfinder
        double desiredHeading = Pathfinder.r2d(followerRight.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
        double turn = .8 * (-1.0/80.0) * angleDifference;

        double leftspeed = left+turn;
        double rightspeed = right-turn;

        Drivetrain.drive(leftspeed, rightspeed);
    }

    @Override
    protected boolean isFinished() { return followerLeft.isFinished() && followerRight.isFinished(); }

}