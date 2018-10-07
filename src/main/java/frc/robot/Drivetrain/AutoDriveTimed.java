package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveTimed extends Command{
    private double timeout;
    private double speed = 0.4;

    public AutoDriveTimed(double timeout){
        requires(Robot.drivetrain);
        this.timeout = timeout;
    }

    protected void initialize(){
        System.out.println("Running timed straight driving at " + speed*100 + " percent for " + timeout +" seconds!");
        setTimeout(timeout);
    }

    protected void execute(){
        Drivetrain.drive(speed,speed);
    }

    protected void end(){
        Drivetrain.drive(0,0);
    }

    protected boolean isFinished(){
        return isTimedOut();
    }
}