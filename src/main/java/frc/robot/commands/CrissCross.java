// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;
// import frc.robot.RobotMap;
// import frc.robot.subsystems.*;

// /**
//  * Makes the robot go in a circle (experimental and doesn't work)
//  */
// public class CrissCross extends CommandBase {

//     private double speed = 0.6;
//     private double angle = 180;
    
//     public CrissCross() {
//         setName("CrissCross");
//         addRequirements(Subsystems.driveBase);
//     }

//     public void initialize() {
//         Subsystems.driveBase.zeroGyroAngle();
//     }

//     public void execute() {
//         if (Subsystems.driveBase.gyro.getAngle()<180) {
//             Subsystem
//         }
//     }

//     public boolean isFinished() {
//         if (angle>0) {
//             return (Subsystems.driveBase.getGyroAngle() > this.angle);
//         } else {
//             return (Subsystems.driveBase.getGyroAngle() < this.angle);
//         }
//     }
    
//     /**
//      * Computes and sets the speeds of both sides of the drivebase
//      */}
//     }
// }
