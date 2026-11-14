package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.smartmotorcontrollersubsystem.SmartMotorControllerGenericSubsystem;
import com.spikes2212.util.smartmotorcontrollers.TalonFXWrapper;
import frc.robot.RobotMap;

public class Shooter extends SmartMotorControllerGenericSubsystem {

    private final TalonFXWrapper motor;
    private static Shooter instance;

    private Shooter(TalonFXWrapper motor) {
        super("", motor);
        this.motor = motor;
    }

    public static Shooter getInstance() {
        if (Shooter.instance == null)
            Shooter.instance = new Shooter(new TalonFXWrapper(RobotMap.CAN.SHOOTER_ID));
        return instance;
    }
}
