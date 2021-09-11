package frc.robot.userinterface;

import frc.robot.RobotMap;

/**
 * Contains instances of all UI elements.
 */
public class UserInterface {

    /** xbox controllers kept to maintain consistency and because i'm lazy and don't want to rewrite everything 
     * possibility for deletion
    */

    /**
     * <p>The driver controller (black).</p>
     * Used to control the drive base alone in teleop.
     */
    public static final RumbleXboxController driverController = new RumbleXboxController(RobotMap.driverXboxController);
    /**
     * <p>The operator controller (geen).</p>
     * Used to control all subsystems except the drive base in teleop.
     */
    public static final RumbleXboxController operatorController = new RumbleXboxController(RobotMap.operatorXboxController);


    public static final WiimoteController wiimoteController = new WiimoteController(RobotMap.wiimoteController);
}