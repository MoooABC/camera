package frc.robot.commands;

import com.spikes2212.command.genericsubsystem.commands.smartmotorcontrollergenericsubsystem.MoveSmartMotorControllerGenericSubsystem;
import com.spikes2212.control.FeedForwardController;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.UnifiedControlMode;
import frc.robot.subsystems.Shooter;

import java.util.function.Supplier;

public class SmartShoot extends MoveSmartMotorControllerGenericSubsystem {

    private final static RootNamespace namespace = new RootNamespace("smart shoot");
    private final static PIDSettings pidSettings = namespace.addPIDNamespace("pid",
            new PIDSettings(0, 0, 0, 0, 0, 0));
    private final static FeedForwardSettings feedForwardSettings = namespace.addFeedForwardNamespace("feed forward",
            new FeedForwardSettings(0, 0, 0, FeedForwardController.ControlMode.LINEAR_VELOCITY));

    public SmartShoot(Shooter shooter, Supplier<Double> setpoint) {
        super(shooter, pidSettings, feedForwardSettings, UnifiedControlMode.VELOCITY, setpoint, true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public static void updateNamespace() {
        namespace.update();
    }
}
