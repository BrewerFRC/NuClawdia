package org.usfirst.frc.team4564.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Bin, Jacket, Sieve
 */
public class DriveTrain extends RobotDrive {
	private final DriveTrain instance = this;
	private double previousMotorPower;
    
	//Hdrive drive motors
	static Talon frontL = new Talon(Constants.PWM_DRIVE_FL);
    static Talon rearL = new Talon(Constants.PWM_DRIVE_RL);
    static Talon frontR = new Talon(Constants.PWM_DRIVE_FR);
    static Talon rearR = new Talon(Constants.PWM_DRIVE_RR);

    // Encoder definitions
    private Encoder encoder = new Encoder(Constants.DIO_DRIVE_ENCODER_A, Constants.DIO_DRIVE_ENCODER_B, 
    		true, EncodingType.k1X);
    
    //Accelerometer definitions
    private Accelerometer accelerometer = new BuiltInAccelerometer();
    
    //PIDS (p, i, d, f, source, output)
    public PID velPID = new PID(Constants.p, Constants.i, Constants.d, new PID.PIDSource() {
		@Override
		public double pidGet() {
			SmartDashboard.putNumber("PIDFeedAngle", instance.getEncoder().getRate());
			return instance.getEncoder().getRate();
		}
    });

    //Drivetrain constructor
    public DriveTrain() {
        super(frontL, rearL, frontR, rearR);
        setInvertedMotor(RobotDrive.MotorType.kFrontLeft,true);
        setInvertedMotor(RobotDrive.MotorType.kRearLeft,true);
        setInvertedMotor(RobotDrive.MotorType.kFrontRight,true);
        setInvertedMotor(RobotDrive.MotorType.kRearRight,true);
        encoder.setDistancePerPulse(1/Constants.COUNTS_PER_INCH);
   }
    
    // Initialize drive train.  Robot must be still for gyro.
    public void init() {
    	encoder.reset();
    }

 
    // Set drive motors, mixing turns into slide motors
    public void drive(double drive, double turn) {
    	arcadeDrive(drive, turn);
    }
    
    public Encoder getEncoder() {
    	return this.encoder;
    }
    public Accelerometer getAccelerometer() {
    	return this.accelerometer;
    }
    public double getForwardAngle() {
    	return Math.toDegrees(Math.atan2(accelerometer.getZ(), accelerometer.getX()));
    }
    public PID getPID() {
    	return this.velPID;
    }
    public double getPIDOutput() {
    	double output = velPID.calculate() + previousMotorPower;
    	previousMotorPower = output;
    	return output;
    }
}

