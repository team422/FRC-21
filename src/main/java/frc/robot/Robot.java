package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.*;
import frc.robot.userinterface.UIMap;
import frc.robot.userinterface.UserInterface;
import frc.robot.subsystems.*;

/**
 * The main Robot class whence all things come.
 */
public class Robot extends TimedRobot {

    public Robot() {
        super(0.06);
    }

    public void robotInit() {
        RobotMap.setBot(RobotMap.BotNames.MECANUM);
        System.out.println("Initializing" + RobotMap.botName);

        //Choose from ControllerTank, ControllerTankFO (Field Oriented extras), JoystickTank, ControllerSplitArcade, ControllerSplitCurvature, and Wii
        UIMap.setDriveMode(UIMap.DriveMode.MecanumDrive);
        System.out.println("Initializing" + UIMap.driveMode);
    
        ShuffleboardControl.layoutShuffleboard();
    }

    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        ShuffleboardControl.printDataToShuffleboard();
    }

    public void disabledInit() {
        System.out.println("Disabled Initialized");
        CommandScheduler.getInstance().cancelAll();
        ShuffleboardControl.disabledPeriodic();
        Subsystems.driveBase.stopMotors();
    }

    public void disabledPeriodic() {}

    public void autonomousInit() {
        System.out.println("Autonomous Initalized");
        CommandScheduler.getInstance().cancelAll();

        // Schedule autonomous command to run
    }

    public void autonomousPeriodic() {}

    public void teleopInit() { 
        System.out.println("TeleOp Initalized");
        CommandScheduler.getInstance().cancelAll();
    }

    public void teleopPeriodic() {

        Subsystems.driveBase.setMotors(0.5, 0, 0, 0);

        if (UserInterface.driverController.LB.get()){
            new Port().schedule();
        }

        if (UserInterface.driverController.RB.get()){
            new Starboard().schedule();
        }

        if (UserInterface.driverController.getStickButton(Hand.kRight)) {
            new SteadyAhead().schedule();
        }

        if (UserInterface.driverController.getAButton()) {
            new SlowFast().schedule();
        }

        if (UserInterface.driverController.getXButton()) {
            new Reset().schedule();
        }

        if (UserInterface.driverController.getYButton()) {
            Subsystems.driveBase.zeroGyroAngle();
        }

        // watch this shit break as soon as we test it lmao.

    }
}
