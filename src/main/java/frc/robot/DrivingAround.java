package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DrivingAround extends SequentialCommandGroup {
    
    public DrivingAround() {
        /*
        Code goes below. Use the following blocks:
         - Turn(degrees)
         - DriveStraith(inches)
        */
        addCommands(new Turn(90));
        addCommands(new DriveStraight(12));
    }
}
