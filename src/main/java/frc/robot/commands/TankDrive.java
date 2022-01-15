package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Uses joystick values to drive the bot in teleop.
 */
public class TankDrive extends CommandBase {

    public TankDrive() {
        setName("TankDrive");
        addRequirements(Subsystems.driveBase);
    }

    public void execute() {
        Subsystems.driveBase.cheesyDrive.tankDrive(RobotMap.getSpeedCap()*UserInterface.driverController.getLeftJoystickY(), -RobotMap.getSpeedCap()*UserInterface.driverController.getRightJoystickY());
    }
}