package frc.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    private static Intake instance = null;
    public static Intake getInstance(){
        if (instance == null) instance = new Intake();
        return instance;
    }

    private static TalonSRX intakeMotorA = new TalonSRX(0);
    private static final int kTimeout = 10;

    private static DoubleSolenoid liftSolenoid = new DoubleSolenoid(0, 0, 0);
    private static DoubleSolenoid clampSolenoid = new DoubleSolenoid(0, 0, 0);

    private Intake(){
        intakeMotorA.configPeakCurrentLimit(40, kTimeout);
        intakeMotorA.configPeakCurrentDuration(500, kTimeout);
        intakeMotorA.configContinuousCurrentLimit(35, kTimeout);
    }

    /* Manipulator intaking controls */
    public static void spinMotors(double speed){
        intakeMotorA.set(ControlMode.PercentOutput, speed);
    }

    public static void switchIntakeClamp(){
        if(clampSolenoid.get() == Value.kForward){
            releaseIntake();
        } else {
            clampIntake();
        }
    }

    public static void clampIntake(){
        clampSolenoid.set(Value.kForward);
    }

    public static void releaseIntake(){
        clampSolenoid.set(Value.kForward);
    }

    /* Manipulator height controls */
    public static void setIntakeHeight(DoubleSolenoid.Value position){
        liftSolenoid.set(position);
    }
    
    public static void switchIntakeHeight(){
        if(liftSolenoid.get() == Value.kForward){
            lowerIntake();
        } else {
            raiseIntake();
        }
    }

    public static void raiseIntake(){
        liftSolenoid.set(Value.kForward);
    }

    public static void lowerIntake(){
        liftSolenoid.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}