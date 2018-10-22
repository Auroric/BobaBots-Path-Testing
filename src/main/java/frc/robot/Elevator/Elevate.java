package frc.robot.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Drivetrain.DrivetrainSubsystem;
import frc.robot.OI.OI;

public class Elevate extends Command {

    private boolean overrideLimit = false;

    public Elevate(){
        requires(Robot.elevator);
    }

    protected void execute(){

        overrideLimit = ElevatorSubsystem.overrideLimit;

        double elevateAxis = OI.elevateValue(); //sets elevateAxis to joystick input

        SmartDashboard.putNumber("Elevator Joystick Input",elevateAxis);
        SmartDashboard.putBoolean("Elevator PID Controller Active", Robot.elevator.elevatorControl.isEnabled());

        SmartDashboard.putNumber("Elevator encoder", DrivetrainSubsystem.leftMotorB.getSelectedSensorPosition(0));



        if(Math.abs(elevateAxis) >= 0.1){


            /*if(elevator.elevatorControl.isEnabled()){ //if PIDController *is* enabled, disable it
                elevator.elevatorControl.disable();
            }*/

            if(!overrideLimit && elevateAxis < 0 && DrivetrainSubsystem.leftMotorB.getSelectedSensorPosition(0)-15000 > 0){
                elevateAxis = 0;
            }
            Robot.elevator.elevate(elevateAxis);

            
            //TODO: Tune PID loop

        } else {

            /*if(!elevator.elevatorControl.isEnabled()){ //if PIDController is *not* enabled, enable it
                elevator.elevatorControl.enable();
            }*/
            Robot.elevator.elevate(0);
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
