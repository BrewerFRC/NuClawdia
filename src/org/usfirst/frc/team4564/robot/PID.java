package org.usfirst.frc.team4564.robot;

import java.util.Calendar;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PID {
	private double p;
	private double i;
	private double d;
	private double min;
	private double max;
	private PIDSource source;
	
	private double outputValue;
	private double target;
	private double sumError;
	private long time;
	private double error;
	
	public PID(double p, double i, double d, PID.PIDSource source) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.source = source;
	}
	
	public double calculate() {
		SmartDashboard.putBoolean("Calculating", true);
		long curTime = Calendar.getInstance().getTimeInMillis();
		long deltaTime = curTime - time;
		time = curTime;
		//Integral calc
		double error = source.pidGet() - target;
		        sumError += error * deltaTime;
		        double I = sumError;
		        //Derivative calc
		        double D = (error - this.error) / deltaTime;
		        this.error = error;
		        //Output calc
		        outputValue = p*error + i*I + d*D;
		        outputValue = Math.max(Math.min(outputValue, max), min);
		        SmartDashboard.putNumber("PIDRawOutput", outputValue);
		        //Remember error and time for next iteration
		        return outputValue;
	}
	
	public void setPID(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
	}
	
	public void setTarget(double target) {
		this.target = target;
	}
	
	public void setMinMax(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public static abstract class PIDSource {
		public abstract double pidGet();
	}
	public static abstract class PIDOutput {
		public abstract void pidWrite(double a);
	}
}
