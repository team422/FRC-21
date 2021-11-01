
package frc.robot.commands.drivemodes;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

/**
 * Uses joystick values to drive the bot in teleop.
 */

public class MecanumDrive extends CommandBase {

    private double xstrafe = UserInterface.driverController.getLeftJoystickX()*RobotMap.corection; //1 is a place holder for now we need to find this value by testing because the strafing movements will be slower than the driving movements
    private double ystrafe = UserInterface.driverController.getLeftJoystickY();
    private double rot = UserInterface.driverController.getRightJoystickX(); //rotation axis which should be able to be combined with our other stuff
    private double updatedFrontLeftPower = 0;
    private double updatedFrontRightPower = 0;
    private double updatedBackLeftPower = 0;
    private double updatedBackRightPower = 0;
    private static final double maxChange = 0.5; //maxChange is acceleration

    public MecanumDrive() {
        setName("MecanumDrive");
        addRequirements(Subsystems.driveBase);
    }

    public void execute() {
        double frontrightpower;
        double frontleftpower;
        double backrightpower;
        double backleftpower;
        double cap = 1;

        if (UserInterface.driverController.getLeftJoystickX() > 0.1||UserInterface.driverController.getLeftJoystickX()<0.1){
            xstrafe = -UserInterface.driverController.getLeftJoystickX()*RobotMap.corection;
        } else {
            xstrafe = 0;
        }

        if (UserInterface.driverController.getLeftJoystickY() > 0.1||UserInterface.driverController.getLeftJoystickY()<0.1){
            ystrafe = UserInterface.driverController.getLeftJoystickY();
        } else {
            ystrafe = 0;
        }

        if (UserInterface.driverController.getRightJoystickX() > 0.1||UserInterface.driverController.getRightJoystickX()<0.1){
            -rot = UserInterface.driverController.getRightJoystickX();
        } else {
            rot = 0;
        }

        if (Math.abs(xstrafe+UserInterface.driverController.getLeftJoystickY())>1||Math.abs(ystrafe-xstrafe)>1) {
            cap = 1/(Math.max(Math.abs(xstrafe + ystrafe), Math.abs(ystrafe -xstrafe)));
        }

        frontleftpower = (((ystrafe + xstrafe)*cap)+rot);
        frontrightpower = ((ystrafe - xstrafe)*cap)-rot;
        backleftpower = (((ystrafe - xstrafe)*cap)+rot);
        backrightpower = ((ystrafe + xstrafe)*cap)-rot;

        double frontLeftSpeedDifference = frontleftpower - updatedFrontLeftPower;
        if (frontLeftSpeedDifference > maxChange) {
            frontleftpower = updatedFrontLeftPower + maxChange;
        } else if (frontLeftSpeedDifference < -maxChange) {
            frontleftpower = updatedFrontLeftPower - maxChange;
        }
        
        double frontRightSpeedDifference = frontrightpower - updatedFrontRightPower;
        if (frontRightSpeedDifference > maxChange) {
            frontrightpower = updatedFrontRightPower + maxChange;
        } else if (frontRightSpeedDifference < -maxChange) {
            frontrightpower = updatedFrontRightPower - maxChange;
        }

        double backLeftSpeedDifference = backleftpower - updatedFrontLeftPower;
        if (backLeftSpeedDifference > maxChange) {
            backleftpower = updatedBackLeftPower + maxChange;
        } else if (backLeftSpeedDifference < -maxChange) {
            backleftpower = updatedBackLeftPower - maxChange;
        }
        
        double backRightSpeedDifference = backrightpower - updatedFrontRightPower;
        if (backRightSpeedDifference > maxChange) {
            backrightpower = updatedBackRightPower + maxChange;
        } else if (backRightSpeedDifference < -maxChange) {
            backrightpower = updatedBackRightPower - maxChange;
        }

        updatedFrontLeftPower = frontleftpower;
        updatedFrontRightPower = frontrightpower;
        updatedBackLeftPower = backleftpower;
        updatedBackRightPower = backrightpower;



        Subsystems.driveBase.setMotors(frontleftpower*RobotMap.getSpeedCap(), frontrightpower*RobotMap.getSpeedCap(), backleftpower*RobotMap.getSpeedCap(), backrightpower*RobotMap.getSpeedCap());
        // Subsystems.driveBase.setMotors(frontleftpower, frontrightpower, backleftpower, backrightpower);
        /*
        This is highly experimental!!! always have your finger on the disable button in case something goes wrong. If there is too much resistant check the values using print statements. If anything feels wrong then disable imediatly
        */
    }

}