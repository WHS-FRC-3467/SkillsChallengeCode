// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
	//Creates a compressor object
	public static Compressor scorpionCompressor;
	
	// Pneumatics is a singleton
	private static Pneumatics instance = new Pneumatics();

	public static Pneumatics getInstance() {
		return Pneumatics.instance;
	}

	/*
	 * Pneumatics Class Constructor
	 *
	 * The singleton instance is created statically with
	 * the instance static member variable.
	 */
	protected Pneumatics() {
		//Initializes the compressor
		scorpionCompressor = new Compressor();

		scorpionCompressor.setClosedLoopControl(true);	
	}
	
}
