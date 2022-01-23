package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
// import frc.robot.commands.TankDrive;

/**
 * The drive base of the robot. Includes all drive train motor controllers as well as sensors such as gyros and encoders, and can use PID to set its motor speeds.
 */
public class DriveBase extends SubsystemBase {

    public WPI_TalonFX leftLeader; //leader = front
    public WPI_TalonFX rightLeader;
    public WPI_TalonFX leftFollower; //follower = back
    public WPI_TalonFX rightFollower;

    public WPI_TalonSRX leftMiddleMaster;
    public WPI_TalonSRX rightMiddleMaster;
    public WPI_VictorSPX leftFrontFollowerVictor;
    public WPI_VictorSPX leftRearFollowerVictor;
    public WPI_VictorSPX rightFrontFollowerVictor;
    public WPI_VictorSPX rightRearFollowerVictor;

    public WPI_TalonSRX leftFrontFollowerTalon;
    public WPI_TalonSRX leftRearFollowerTalon;
    public WPI_TalonSRX rightFrontFollowerTalon;
    public WPI_TalonSRX rightRearFollowerTalon;

    public ADXRS450_Gyro gyro;
    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    public DifferentialDrive cheesyDrive;
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;

    public double leftMotorTicks = 0;
    public double rightMotorTicks = 0;

    public DriveBase() {
        setSubsystem("DriveBase");

        if (RobotMap.botName == RobotMap.BotNames.COMPETITION) {
            //Practice/comp bot
            this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMaster);
            this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMaster);
            this.leftFrontFollowerVictor = new WPI_VictorSPX(RobotMap.leftFrontFollower);
            this.leftRearFollowerVictor = new WPI_VictorSPX(RobotMap.leftRearFollower);
            this.rightFrontFollowerVictor = new WPI_VictorSPX(RobotMap.rightFrontFollower);
            this.rightRearFollowerVictor = new WPI_VictorSPX(RobotMap.rightRearFollower);
    
            leftFrontFollowerVictor.setInverted(true);
            leftMiddleMaster.setInverted(true);
            leftRearFollowerVictor.setInverted(true);
            rightFrontFollowerVictor.setInverted(false);
            rightMiddleMaster.setInverted(false);
            rightRearFollowerVictor.setInverted(false);
    
            this.leftSide = new SpeedControllerGroup(leftMiddleMaster, leftFrontFollowerVictor, leftRearFollowerVictor);
            this.rightSide = new SpeedControllerGroup(rightMiddleMaster, rightFrontFollowerVictor, rightRearFollowerVictor);
    
        } else if (RobotMap.botName == RobotMap.BotNames.TOASTER) {
            //Toaster
            this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMaster);
            this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMaster);            
            this.leftFrontFollowerTalon = new WPI_TalonSRX(RobotMap.leftFrontFollower);
            this.leftRearFollowerTalon = new WPI_TalonSRX(RobotMap.leftRearFollower);
            this.rightFrontFollowerTalon = new WPI_TalonSRX(RobotMap.rightFrontFollower);
            this.rightRearFollowerTalon = new WPI_TalonSRX(RobotMap.rightRearFollower);

            leftFrontFollowerTalon.setInverted(true);
            leftMiddleMaster.setInverted(true);
            leftRearFollowerTalon.setInverted(true);
            leftMiddleMaster.setInverted(true);

            this.leftSide = new SpeedControllerGroup(leftMiddleMaster, leftFrontFollowerTalon, leftRearFollowerTalon);
            this.rightSide = new SpeedControllerGroup(rightMiddleMaster, rightFrontFollowerTalon, rightRearFollowerTalon);
        } else if (RobotMap.botName == RobotMap.BotNames.AXIDRIVE){ 
            //Axiom's drivebase, for ease of drive practice and testing
            this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMaster);
            this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMaster);            
            this.leftFrontFollowerVictor = new WPI_VictorSPX(RobotMap.leftFrontFollower);
            this.leftRearFollowerVictor = new WPI_VictorSPX(RobotMap.leftRearFollower);
            this.rightFrontFollowerVictor = new WPI_VictorSPX(RobotMap.rightFrontFollower);
            this.rightRearFollowerVictor = new WPI_VictorSPX(RobotMap.rightRearFollower);

            leftMiddleMaster.setInverted(true);
            leftFrontFollowerVictor.setInverted(true);
            leftRearFollowerVictor.setInverted(true);
    
            this.leftSide = new SpeedControllerGroup(leftMiddleMaster, leftFrontFollowerVictor, leftRearFollowerVictor);
            this.rightSide = new SpeedControllerGroup(rightMiddleMaster, rightFrontFollowerVictor, rightRearFollowerVictor);
    
        } else if (RobotMap.botName == RobotMap.BotNames.PBOT20){
            //2020's PBOT (42D2)
            this.leftMiddleMaster = new WPI_TalonSRX(RobotMap.leftMiddleMaster);
            this.rightMiddleMaster = new WPI_TalonSRX(RobotMap.rightMiddleMaster); 
            this.leftFrontFollowerVictor = new WPI_VictorSPX(RobotMap.leftFrontFollower);
            this.leftRearFollowerVictor = new WPI_VictorSPX(RobotMap.leftRearFollower);
            this.rightFrontFollowerVictor = new WPI_VictorSPX(RobotMap.rightFrontFollower);
            this.rightRearFollowerVictor = new WPI_VictorSPX(RobotMap.rightRearFollower);

            leftFrontFollowerVictor.setInverted(true);
            leftMiddleMaster.setInverted(true);
            leftRearFollowerVictor.setInverted(true);

            this.leftSide = new SpeedControllerGroup(leftMiddleMaster, leftFrontFollowerVictor, leftRearFollowerVictor);
            this.rightSide = new SpeedControllerGroup(rightMiddleMaster, rightFrontFollowerVictor, rightRearFollowerVictor);

        } else if (RobotMap.botName == RobotMap.BotNames.FALCON) {
            //falcon drivebase (promised but nonexistent as of 10/29/21)
            this.leftLeader = new WPI_TalonFX(RobotMap.leftLeader);
            this.rightLeader = new WPI_TalonFX(RobotMap.rightLeader);
            this.leftFollower = new WPI_TalonFX(RobotMap.leftFollower);
            this.rightFollower = new WPI_TalonFX(RobotMap.rightFollower);

            this.leftSide = new SpeedControllerGroup(leftLeader, leftFollower);
            this.rightSide = new SpeedControllerGroup(rightLeader, rightFollower);
        } else if (RobotMap.botName == RobotMap.BotNames.ANTIMECANUM) {
            //this is mecanum without mecanum wheels
            this.leftLeader = new WPI_TalonFX(RobotMap.leftLeader);
            this.rightLeader = new WPI_TalonFX(RobotMap.rightLeader);
            this.leftFollower = new WPI_TalonFX(RobotMap.leftFollower);
            this.rightFollower = new WPI_TalonFX(RobotMap.rightFollower);

            this.leftSide = new SpeedControllerGroup(leftLeader, leftFollower);
            this.rightSide = new SpeedControllerGroup(rightLeader, rightFollower);
        }


        // this.gyro = new ADIS16470_IMU();
        this.gyro = new ADXRS450_Gyro(kGyroPort);

        if (RobotMap.botName == RobotMap.BotNames.FALCON||RobotMap.botName == RobotMap.botName.ANTIMECANUM) {
            leftMotorTicks = leftLeader.getSelectedSensorPosition(0);
            rightMotorTicks =rightLeader.getSelectedSensorPosition(0);
        }
        else {
            leftMotorTicks = leftMiddleMaster.getSelectedSensorPosition(0);
            rightMotorTicks = rightMiddleMaster.getSelectedSensorPosition(0);
        }

       

        this.cheesyDrive = new DifferentialDrive(leftSide, rightSide);
    }

    /**
     * Sets drive train motors.
     * @param left Left side motors' velocity (-1 to 1)
     * @param right Right side motors' velocity (-1 to 1)
     */
    public void setMotors(double left, double right) {
        leftSide.set(left);
        rightSide.set(right);
    }

    /**
     * Sets the left side drive train motors
     * @param left Left side motors' velocity (-1 to 1)
     */
    public void setLeftMotors(double left) {
        leftSide.set(left);
    }

    /**
     * Sets the right side drive train motors
     * @param right Right side motors' velocity (-1 to 1)
     */
    public void setRightMotors(double right) {
        rightSide.set(right);
    }

    /**
     * Sets drive train motors to zero, effectively stopping the bot.
     */
    public void stopMotors() {
        leftSide.set(0);
        rightSide.set(0);
    }

    /**
     * @return Left side position in ticks. If statement exists because Falcon uses a different motor as leader than 
     * the other drivebases 
     */
    public double getLeftPosition() {
        if (RobotMap.botName == RobotMap.BotNames.FALCON||RobotMap.botName == RobotMap.botName.ANTIMECANUM) {
            return leftLeader.getSelectedSensorPosition(0) - leftMotorTicks;
        }
        else {
         return leftMiddleMaster.getSelectedSensorPosition(0) - leftMotorTicks;
        }
    }

    /**
     * @return Right side position in ticks.
     */
    public double getRightPosition() {
        if (RobotMap.botName == RobotMap.BotNames.FALCON||RobotMap.botName == RobotMap.botName.ANTIMECANUM) {
            return rightLeader.getSelectedSensorPosition(0) - rightMotorTicks;
        }
        else {
         return rightMiddleMaster.getSelectedSensorPosition(0) - rightMotorTicks;
        }
    }

    /**
     * @return Angle at which the robot is positioned in degrees
     */
    public double getGyroAngle() {
        return gyro.getAngle();
    }

    /**
     * Resets the reference point used to calculate distance traveled. Does not physically change the encoder value.
     */
    public void zeroEncoderPosition() {
        if (RobotMap.botName == RobotMap.BotNames.FALCON||RobotMap.botName == RobotMap.botName.ANTIMECANUM) {
            leftMotorTicks = leftLeader.getSelectedSensorPosition(0);
            rightMotorTicks = rightLeader.getSelectedSensorPosition(0);
        }
        else {
        leftMotorTicks = leftMiddleMaster.getSelectedSensorPosition(0);
        rightMotorTicks = rightMiddleMaster.getSelectedSensorPosition(0);
        }
    }

    /**
     * Sets the gyro angle to zero.
     */
    public void zeroGyroAngle() {
        gyro.reset();
    }
}
