package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
    
    public WPI_TalonSRX leftFrontTalon;
    public WPI_TalonSRX rightFrontTalon;
    public WPI_TalonSRX leftBackTalon;
    public WPI_TalonSRX rightBackTalon;

    public ADXRS450_Gyro gyro;
    private SpeedControllerGroup leftFrontGroup;
    private SpeedControllerGroup leftBackGroup;
    private SpeedControllerGroup rightBackGroup;
    private SpeedControllerGroup rightFrontGroup;
    public DifferentialDrive cheesyDrive;
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;

    public double leftFrontMotorTicks = 0;
    public double rightFrontMotorTicks = 0;
    public double leftBackMotorTicks = 0;
    public double rightBackMotorTicks = 0;

    public DriveBase() {
        setSubsystem("DriveBase");

        if (RobotMap.botName == RobotMap.BotNames.MECANUM) {
            //2021 Offseason Mecanum Experiment
            this.leftFrontTalon = new WPI_TalonSRX(RobotMap.leftFront);
            this.leftBackTalon = new WPI_TalonSRX(RobotMap.leftBack);
            this.rightFrontTalon = new WPI_TalonSRX(RobotMap.rightFront);
            this.rightBackTalon = new WPI_TalonSRX(RobotMap.rightBack);

            this.leftFrontGroup = new SpeedControllerGroup(leftFrontTalon);
            this.rightFrontGroup = new SpeedControllerGroup(rightFrontTalon);
            this.leftBackGroup = new SpeedControllerGroup(leftBackTalon);
            this.rightBackGroup = new SpeedControllerGroup(rightBackTalon);
        }


        // this.gyro = new ADIS16470_IMU();
        this.gyro = new ADXRS450_Gyro(kGyroPort);
    }
    public void setMotors(double frontLeft, double frontRight, double backLeft, double backRight) {
        leftFrontGroup.set(-frontLeft);
        rightFrontGroup.set(frontRight);
        leftBackGroup.set(-backLeft);
        rightBackGroup.set(backRight);
        System.out.println(frontRight);
    }

    /**
     * Sets drive train motors to zero, effectively stopping the bot.
     */
    public void stopMotors() {
        leftFrontGroup.set(0);
        rightFrontGroup.set(0);
        leftBackGroup.set(0);
        rightBackGroup.set(0);
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
        leftFrontMotorTicks = leftFrontTalon.getSelectedSensorPosition(0);
        rightFrontMotorTicks = rightFrontTalon.getSelectedSensorPosition(0);
        leftBackMotorTicks = leftBackTalon.getSelectedSensorPosition(0);
        rightBackMotorTicks = rightBackTalon.getSelectedSensorPosition(0);
    }

    /**
     * Sets the gyro angle to zero.
     */
    public void zeroGyroAngle() {
        gyro.reset();
    }
}