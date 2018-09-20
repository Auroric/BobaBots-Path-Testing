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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Drivetrain.Drivetrain;

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

    public JoystickButton dpadUP;
    public JoystickButton dpadUP_RIGHT;
    public JoystickButton dpadRIGHT;
    public JoystickButton dpadDOWN_RIGHT;
    public JoystickButton dpadDOWN;
    public JoystickButton dpadDOWN_LEFT;
    public JoystickButton dpadLEFT;
    public JoystickButton dpadUP_LEFT;
    public JoystickButton dpadNONE;

    public OI(){
        xboxcontroller = new XboxController(1);
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

    }

    public double throttleValue() {
        return xboxcontroller.getY(Hand.kLeft);
    }
    public double turnValue() {
        return xboxcontroller.getX(Hand.kRight);
    }
}