package org.usfirst.frc.team4564.robot;

public class Constants {
	// DIO Ports
	public static final int DIO_LIFT_BOTTOM = 0; // Lower Limit Switch
	public static final int DIO_LIFT_ENCODER_A = 2;  
	public static final int DIO_LIFT_ENCODER_B = 3;  
	public static final int DIO_DRIVE_ENCODER_A = 4;  
	public static final int DIO_DRIVE_ENCODER_B = 5;  

	//PWM Ports
	public static final int PWM_DRIVE_FR = 1;
	public static final int PWM_DRIVE_RR = 2;
	public static final int PWM_DRIVE_FL = 3;
	public static final int PWM_DRIVE_RL = 4;
	public static final int PWM_LIFT_MOTOR = 7;
	//Solenoids

	
	//velPID terms
	public static double p = 0.00075; //.00583
	public static double i = 0.0;
	public static double d = 0.0;
	public static final double target = 6;
	
	//MISCELLANEOUS
	public static final double REFRESH_RATE = 100;  //Refresh rate for main loop and all related subsystem updates
	public static final double COUNTS_PER_INCH = 38.3803;
	
} 