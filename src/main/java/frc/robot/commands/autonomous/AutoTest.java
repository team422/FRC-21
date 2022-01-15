package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

public class AutoTest extends SequentialCommandGroup {
    
    public AutoTest(){
        addCommands(new DriveStraight(50, 0.95));
    }
 
}