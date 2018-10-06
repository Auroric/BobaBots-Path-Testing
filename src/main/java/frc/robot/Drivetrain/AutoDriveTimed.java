package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveTimed extends Command{

    public AutoDriveTimed(){
        requires(Robot.drivetrain);

    }

    protected void initialize(){
        setTimeout(4.5);

    }

    protected void execute(){
        Drivetrain.drive(0.4,0.4);
    }

    protected void end(){
        Drivetrain.drive(0,0);
    }

    protected boolean isFinished(){
        return isTimedOut();
    }
}