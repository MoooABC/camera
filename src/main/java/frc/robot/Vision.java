package frc.robot;

import com.spikes2212.dashboard.RootNamespace;
import limelight.Limelight;
import limelight.networktables.LimelightResults;
import limelight.networktables.LimelightSettings.LEDMode;

import java.util.Optional;

public class Vision {

    private final Limelight limelight;
    private final RootNamespace namespace = new RootNamespace(VisionConstants.NAMESPACE_NAME);

    public Vision() {
        this.limelight = new Limelight(VisionConstants.CAMERA_NAME);
        limelight.getSettings()
                .withLimelightLEDMode(LEDMode.PipelineControl)
                .save();

        configureNamespace();
    }

    public void configureNamespace() {
        namespace.putNumber("Horizontal Offset", this::getHorizontalOffset);
        namespace.putNumber("Vertical Offset", this::getVerticalOffset);
        namespace.putNumber("Distance from target", this::getDistance);
        namespace.putBoolean("target", () -> limelight.getData().targetData.getTargetStatus());
        namespace.putNumber("temperature", this::getTemperature);
    }

    public double getHorizontalOffset() {
        if (limelight.getData().targetData.getTargetStatus())
            return limelight.getData().targetData.getHorizontalOffset();
        return -1;
    }

    public double getTemperature() {
        double[] arr = limelight.getNTTable().getEntry("hw").getDoubleArray(new double[0]);
        return arr.length > 0 ? arr[0] : -1;
    }

    public double getVerticalOffset() {
        if (limelight.getData().targetData.getTargetStatus())
            return limelight.getData().targetData.getVerticalOffset();
        return -1;
    }

    public void updateNamespace() {
        namespace.update();
    }

    public double getDistance() {
        return 196.0;
        //double ty = getVerticalOffset();
        //return (VisionConstants.TAG_FROM_FLOOR- VisionConstants.CAMERA_FROM_FLOOR) / Math.tan(VisionConstants.CAMERA_ANGLE+ty);
    }
}
