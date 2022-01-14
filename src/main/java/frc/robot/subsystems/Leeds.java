package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.RobotMap;

/**
 * LED subsystem for all your lighty up needs
 */
public class Leeds extends SubsystemBase {

    private AddressableLED leeds;
    private AddressableLEDBuffer leedBuffer;

    /**
     * Constructor for LED subsystem
     */
    public Leeds() {
        setSubsystem("Leeds");

        // Create LED and buffer instance
        leeds = new AddressableLED(RobotMap.leeds);
        leedBuffer = new AddressableLEDBuffer(RobotMap.leedLength);
        leeds.setLength(leedBuffer.getLength());

        // Set data
        leeds.setData(leedBuffer);
        leeds.start();
    }

    public void animation(int tick) {
        for (int i = 0; i < leedBuffer.getLength(); i++) {
            if (tick % 3 == 0) {
                if (i % 3 == 0) {
                    leedBuffer.setRGB(i, 0, 183, 96); // 0 183 96 is Mech Tech Green
                } else {
                    leedBuffer.setRGB(i, 0, 112, 60); // 0 112 60 is Dark Green
                }
            } else if (tick % 3 == 1) {
                if (i % 3 == 1) {
                    leedBuffer.setRGB(i, 0, 183, 96);
                } else {
                    leedBuffer.setRGB(i, 0, 112, 60);
                }
            } else {
                if (i % 3 == 2) {
                    leedBuffer.setRGB(i, 0, 183, 96);
                } else {
                    leedBuffer.setRGB(i, 0, 112, 60);
                }
            }
        }

        leeds.setData(leedBuffer);
    }
}
