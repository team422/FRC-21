package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * Turns the robot
 */
public class Turn extends PIDCommand {

  private double degrees;
  // private double speed;
  static private double kP = 2;
  static private double kI = 0;
  static private double kD = 0;
 // private double original_speed;
  private boolean isCorrecting = false;

  /**
   * Turns the bot a set number of degrees.
   * @param Degrees The number of degrees to turn - negative to the left, positive to the right.
   * @param Speed The speed at which to turn (0 to 1). Speeds over x are not recommended for maximal accuracy.
   */

    public Turn(double degrees, double speed) {
        //commandBase.setName("Turn");
        //addRequirements(Subsystems.driveBase);
        // this.degrees = degrees;
        // this.speed = speed;
        // original_speed = speed;
        // controller copied from link 3 above from full example
        super(
            new PIDController(kP, kI, kD),
            // Set the final output
            Subsystems.driveBase::getGyroAngle,
            degrees,
            output -> Subsystems.driveBase.setMotors(output,-output),
            Subsystems.driveBase);

        getController().enableContinuousInput(-180,180);
        getController()
            .setTolerance(0.1, 0.1);
    }
//     public void initialize() {
//       System.out.println("Starting turn!");
//       Subsystems.driveBase.zeroGyroAngle();
//       Subsystems.driveBase.zeroEncoderPosition();
//   }

/*
  public void execute() {
      if ((degrees > 0) && !isCorrecting) {
          // Turning to the right
          Subsystems.driveBase.setMotors(-speed, speed);
      } else if ((degrees < 0) && !isCorrecting) {
          // Turning to the left
          Subsystems.driveBase.setMotors(speed, -speed);
      } else if (degrees > 0) {
          // Turned to the right, but correcting to the left
          Subsystems.driveBase.setMotors(speed / 1.2, -speed / 1.2);
      } else {
          // Turned to the left, but correcting to the right
          Subsystems.driveBase.setMotors(-speed / 1.2, speed / 1.2);
      }
  }
*/


//int CurrentDegree = 0;
//int LastDegree = 0;
  public boolean isFinished() {
      return getController().atSetpoint();
    // formula solution for turning
    //   double angle = Subsystems.driveBase.getGyroAngle();
    //   CurrentDegree = (int)angle;
    //   if (CurrentDegree != LastDegree) {
    //     speed = speed * (1-(CurrentDegree/degrees));
    //     LastDegree = CurrentDegree;
    //     return false;
    //   }
    //   if (angle > degrees) {
    //       System.out.println(angle);
    //       return true;
    //   }
    //   else {
    //       return false;
    //   }

      /*
      if (angle > degrees) {
          return true;
      }
      else {
        return false;
      }
      */
      
      /*
      int factor = 1;
      if (angle > factor * 0.90 * degrees) {
        return true;
        }
        else if (angle > factor * 0.8 * degrees) {
            speed = 1/32 * original_speed;
            return false;
        }
        else if (angle > factor * 0.7 * degrees) {
            speed = 1/16 * original_speed;
            return false;
        }
        else if (angle > factor * 0.6 * degrees) {
            speed = 1/8 * original_speed;
            return false;
        }
        else if (angle > 0.5 * degrees) {
            speed = 1/4 * original_speed;
            return false;
        }
        else if (angle > factor * 0.5 * degrees) {
            speed = 1/2 * original_speed;
            return false;
        }
        return false;
    }
    */
    
      /*
      if (degrees > 0) {
          // Turning to the right
          if (!isCorrecting) {
              if (angle > degrees) {
                  isCorrecting = true;
              }
              return false;
          }
          return (angle < degrees);
      } else {
          // Turning to the left
          if (!isCorrecting) {
              if (angle < degrees) {
                  isCorrecting = true;
              }
              return false;
          }
          return (angle > degrees);
          */

      }  }