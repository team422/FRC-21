package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/**
 * Changes the "gear" of the robot to drive fast or slow "slowmode"
 */
public class HoldFast extends CommandBase {

    public HoldFast() {
        setName("HoldFast");
        addRequirements(Subsystems.driveBase);
    }

    public void initialize() { // Checks to see if the robot is in fast mode
        RobotMap.setSpeedAndRotationCaps(RobotMap.fastSpeedCap, RobotMap.fastRotCap);
        RobotMap.isSpeedMode = true;
    }

    public void end() {
        RobotMap.setSpeedAndRotationCaps(RobotMap.slowSpeedCap, RobotMap.slowRotCap);
        RobotMap.isSpeedMode = false;
    }

    public boolean isFinished() { // and lo it is complete
        return true;
    }

}