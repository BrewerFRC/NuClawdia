
package org.usfirst.frc.team4564.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	DriveTrain dt;
	Xbox joy = new Xbox(0);
	
    public Robot() {
    	dt = new DriveTrain();
    	dt.setSafetyEnabled(false);
    	dt.setExpiration(0.1);
    	
    }
    
    public void robotInit() {
    	dt.init();
    	SmartDashboard.putNumber("P", Constants.p);
    	SmartDashboard.putNumber("I", Constants.i);
    	SmartDashboard.putNumber("D", Constants.d);
    	dt.getPID().setTarget(Constants.target);
    	dt.getPID().setMinMax(-1.0, 1.0);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
    	dt.init();
    	dt.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            Timer.delay(0.005);		// wait for a motor update time
            SmartDashboard.putNumber("Encoder", dt.getEncoder().get());
            SmartDashboard.putNumber("Velocity", dt.getEncoder().getRate());
            SmartDashboard.putNumber("AccelX", dt.getAccelerometer().getX());
            SmartDashboard.putNumber("AccelY", dt.getAccelerometer().getY());
            SmartDashboard.putNumber("AccelZ", dt.getAccelerometer().getZ());
            SmartDashboard.putNumber("Angle", dt.getForwardAngle());
            Constants.p = SmartDashboard.getNumber("P");
            Constants.i = SmartDashboard.getNumber("I");
            Constants.d = SmartDashboard.getNumber("D");
            dt.getPID().setPID(Constants.p, Constants.i, Constants.d);
            if (joy.getRawButton(3)) {
            	double output = dt.getPIDOutput();
            	SmartDashboard.putNumber("PIDOutput", output);
            	dt.drive(output, 0.0);
            }
            else {
            	dt.drive(joy.leftY(), joy.leftX()); // drive with arcade style (use right stick)
            }
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
