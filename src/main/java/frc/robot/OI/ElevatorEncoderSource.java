package frc.robot.OI;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

import static frc.robot.Drivetrain.Drivetrain.leftMotorB;


public class ElevatorEncoderSource implements PIDSource {
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
    }

    @Override
    public double pidGet() {
        return leftMotorB.getSelectedSensorPosition(0);
    }
}
