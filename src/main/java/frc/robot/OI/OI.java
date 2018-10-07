package frc.robot.OI;

import static frc.robot.OI.XBPovButton.DOWN;
import static frc.robot.OI.XBPovButton.DOWN_LEFT;
import static frc.robot.OI.XBPovButton.DOWN_RIGHT;
import static frc.robot.OI.XBPovButton.LEFT;
import static frc.robot.OI.XBPovButton.NONE;
import static frc.robot.OI.XBPovButton.RIGHT;
import static frc.robot.OI.XBPovButton.UP;
import static frc.robot.OI.XBPovButton.UP_LEFT;
import static frc.robot.OI.XBPovButton.UP_RIGHT;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Elevator.ElevatorSubsystem;
import frc.robot.Intake.IntakeSpin;

public class OI {
    public XboxController xboxcontroller;
    public JoystickButton ButtonA;
    public JoystickButton ButtonB;
    public JoystickButton ButtonX;
    public JoystickButton ButtonY;
    public JoystickButton ButtonRB;
    public JoystickButton ButtonLB;
    public JoystickButton ButtonRT;
    public JoystickButton ButtonLT;
    public Joystick intakestick;
    public static Joystick elevatorstick;

    public JoystickButton dpadUP;
    public JoystickButton dpadUP_RIGHT;
    public JoystickButton dpadRIGHT;
    public JoystickButton dpadDOWN_RIGHT;
    public JoystickButton dpadDOWN;
    public JoystickButton dpadDOWN_LEFT;
    public JoystickButton dpadLEFT;
    public JoystickButton dpadUP_LEFT;
    public JoystickButton dpadNONE;

    public JoystickButton Button1;
    public JoystickButton Button2;

    public OI(){
        xboxcontroller = new XboxController(1);
        intakestick = new Joystick(3);
        elevatorstick = new Joystick(2);

        ButtonA = new JoystickButton(xboxcontroller, 1);
        ButtonB = new JoystickButton(xboxcontroller, 2);
        ButtonX = new JoystickButton(xboxcontroller, 3);
        ButtonY = new JoystickButton(xboxcontroller, 4);
        ButtonRB = new JoystickButton(xboxcontroller, 6);
        ButtonLB = new JoystickButton(xboxcontroller, 5);
        ButtonRT = new JoystickButton(xboxcontroller, 7);
        ButtonLT = new JoystickButton(xboxcontroller, 8);

        dpadUP = new XBPovButton(xboxcontroller, UP);
        dpadUP_RIGHT = new XBPovButton(xboxcontroller, UP_RIGHT);
        dpadRIGHT = new XBPovButton(xboxcontroller, RIGHT);
        dpadDOWN_RIGHT = new XBPovButton(xboxcontroller, DOWN_RIGHT);
        dpadDOWN = new XBPovButton(xboxcontroller, DOWN);
        dpadDOWN_LEFT = new XBPovButton(xboxcontroller, DOWN_LEFT);
        dpadLEFT = new XBPovButton(xboxcontroller, LEFT);
        dpadUP_LEFT = new XBPovButton(xboxcontroller, UP_LEFT);
        dpadNONE = new XBPovButton(xboxcontroller, NONE);

        dpadLEFT.whenPressed(new RunCommand( () -> Drivetrain.shiftGear() ));
        ButtonY.whileHeld(new IntakeSpin(-0.7));
        ButtonY.whenReleased(new IntakeSpin(0));

        ButtonX.whileHeld(new IntakeSpin(0.7));
        ButtonX.whenReleased(new IntakeSpin(0));

        Button1 = new JoystickButton(intakestick, 1);
        Button2 = new JoystickButton(intakestick, 2);

        Button1.whileHeld(new IntakeSpin(-0.7));
        Button1.whenReleased(new IntakeSpin(0));

        Button2.whileHeld(new IntakeSpin(0.7));
        Button2.whenReleased(new IntakeSpin(0));
        
        dpadRIGHT.whenPressed(new RunCommand( () -> ElevatorSubsystem.overrideLimit() ));
    }

    public double throttleValue() {
        //Controllers y-axes are natively up-negative, down-positive
        return -xboxcontroller.getY(Hand.kLeft);
    }
    public double turnValue() {
        return xboxcontroller.getX(Hand.kRight);
        
    }
    public static double elevateValue(){ 
        return elevatorstick.getY();
    }

    public double intakeSpeed(){
        return intakestick.getY();
 
    }
}