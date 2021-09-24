package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.Robot;

/**
 * uses adjustments to make the robot drive straight without deviation
 */

public class DriveStraight extends CommandBase {
    private double ticks;
    private boolean forward;
    private double speed;

    public DriveStraight(double inches){
        setName("DriveStraight");
        addRequirements(Robot.driveBase);
        this.ticks = RobotMap.convertToTicks(inches);
        if(inches>0) {
            this.forward = true;
        }
        this.speed = RobotMap.defaultSpeed;
    }

    public void initialize(){
        Robot.driveBase.zeroEncoderPosition();
        Robot.driveBase.zeroGyroAngle();
        System.out.println("starting drive straight");
    }

    public void execute(){
        //Find correction to represent how far off robot is from straight line
        double correction = Robot.driveBase.getGyroAngle();
        correction *= 0.05;
        correction += 1.0;

        if (forward) {
            Robot.driveBase.setMotors(-this.speed, -this.speed * correction);
        } else {
            Robot.driveBase.setMotors(this.speed * correction, this.speed);
        }
        System.out.println(this.ticks);
        System.out.println(Robot.driveBase.getLeftPosition());
        System.out.println(Robot.driveBase.getRightPosition());
    }

    public boolean isFinished() {
        double leftPosition = Robot.driveBase.getLeftPosition();
        double rightPosition = Robot.driveBase.getRightPosition();
        if (forward) {
            return (leftPosition > this.ticks) || (rightPosition > this.ticks);
        } else {
            return (leftPosition < this.ticks) || (rightPosition < this.ticks);
        }
    }

    public void end(boolean interrupted) {
        Robot.driveBase.setMotors(0,0);
    }
}