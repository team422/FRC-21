package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * uses adjustments to make the robot drive straight without deviation
 */

// Link: https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html#constructing-a-pidcontroller
//  example: https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html#pid-control-through-pidsubsystems-and-pidcommands
// https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html#pid-control-through-pidsubsystems-and-pidcommands
public class DriveStraight extends CommandBase {
    private double ticks;
    private boolean forward;
    private double speed;

    public DriveStraight(double inches, double speed){
        setName("DriveStraight");
        addRequirements(Subsystems.driveBase);
        this.ticks = RobotMap.convertToTicks(inches);
        if(inches>0) {
            this.forward = true;
        }
        this.speed = speed;
        // double kP, kI, kD;
        // PIDController our_pid = new PIDController(kP, kI, kD);
    }

    public void initialize(){
        Subsystems.driveBase.zeroEncoderPosition();
        Subsystems.driveBase.zeroGyroAngle();
        System.out.println("starting drive straight");
    }

    public void execute(){
        //Find correction to represent how far off robot is from straight line
        double correction = Subsystems.driveBase.getGyroAngle();
        correction *= 0.05;
        correction += 1.0;

        if (forward) {
            Subsystems.driveBase.setMotors(-this.speed, -this.speed * correction);
        } else {
            Subsystems.driveBase.setMotors(this.speed * correction, this.speed);
        }
        System.out.println(this.ticks);
        System.out.println(Subsystems.driveBase.getLeftPosition());
        System.out.println(Subsystems.driveBase.getRightPosition());
    }

    public boolean isFinished() {
        double leftPosition = Subsystems.driveBase.getLeftPosition();
        double rightPosition = Subsystems.driveBase.getRightPosition();
        if (forward) {
            return (leftPosition > this.ticks) || (rightPosition > this.ticks);
        } else {
            return (leftPosition < this.ticks) || (rightPosition < this.ticks);
        }
    }

    public void end(boolean interrupted) {
        Subsystems.driveBase.setMotors(0,0);
    }
}
