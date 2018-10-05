package frc.robot.Elevator.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI.OI;
import frc.robot.Robot;

public class elevate extends Command {

    public elevate(){
        requires(Robot.elevator);
    }

    protected void execute(){

        double elevateAxis = OI.elevateValue(); //sets elevateAxis to joystick input

        SmartDashboard.putNumber("Elevator Joystick Input",elevateAxis);
        SmartDashboard.putBoolean("Elevator PID Controller Active", Robot.elevator.elevatorControl.isEnabled());

        if(Math.abs(elevateAxis) >= 0.1){

            /*if(elevator.elevatorControl.isEnabled()){ //if PIDController *is* enabled, disable it
                elevator.elevatorControl.disable();
            }*/
            Robot.elevator.elevate(elevateAxis);
            //TODO: Tune PID loop

        } else {

            /*if(!elevator.elevatorControl.isEnabled()){ //if PIDController is *not* enabled, enable it
                elevator.elevatorControl.enable();
            }*/
            Robot.elevator.elevate(-0.2);
            //Stall Percentage Old Intake w/o Cube: -0.2
            //REEE
            //TODO: Need to reverse elevator motors b/c sparks reading red when going up

        }

    }


    @Override
    protected boolean isFinished() {
        return false;
    }
}
