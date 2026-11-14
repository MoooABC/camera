package frc.robot.commands;

import com.spikes2212.command.genericsubsystem.commands.smartmotorcontrollergenericsubsystem.MoveSmartMotorControllerGenericSubsystem;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.UnifiedControlMode;
import frc.robot.subsystems.Shooter;

import java.util.function.Supplier;

public class SmartShoot extends MoveSmartMotorControllerGenericSubsystem {

    private final static RootNamespace namespace = new RootNamespace("smart shoot");
    private final static PIDSettings pidSettings = namespace.addPIDNamespace("pid", PIDSettings.EMPTY_PID_SETTINGS);
    private final static FeedForwardSettings feedForwardSettings = namespace.addFeedForwardNamespace("feed forward", FeedForwardSettings.EMPTY_FF_SETTINGS);

    public SmartShoot(Shooter shooter, Supplier<Double> setpoint) {
        super(shooter, pidSettings, feedForwardSettings, UnifiedControlMode.VELOCITY, setpoint, true);
    }

    public static void updateNamespace() {
        namespace.update();
    }
}
