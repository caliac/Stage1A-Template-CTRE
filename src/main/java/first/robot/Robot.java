/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package first.robot;

import org.wpilib.framework.OpModeRobot;
import com.ctre.phoenix6.hardware.TalonFX;

/**
 * The methods in this class are called automatically as described in the OpModeRobot documentation.
 * OpMode classes anywhere in the package (or sub-packages) where this class is located are
 * automatically registered to display in the Driver Station. If you change the name of this class
 * or the package after creating this project, you must also update the Main.java file in the
 * project.
 */
public class Robot extends OpModeRobot {

  private final TalonFX leftLeader = new TalonFX(0, new CANBus(CANPort.CAN_S0));
  private final TalonFX leftFollower = new TalonFX(1, newCANBus(CANPort.CAN_S0));
  private final TalonFX rightLeader = new TalonFX(2, new CANBus(CANPort.CAN_S0));
  private final TalonFX rightFollower = new TalonFX(3, new CANBus(CANPort.CAN_S0));

  public final DifferentialDrive drivetrain = 
    new DifferentialDrive(leftLeader::setThrottle, rightLeader::setThrottle);

  private final OnboardIMU(MountOrientation.FLAT);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {

    TalonFXConfiguration leftConfig = new TalonFXConfiguration(); //creates config
    leftConfig.MotorOutput.withInverted(InvertedValue.Clockwise_Positive); //sets left configuration
    leftLeader.getConfigurator().apply(leftConfig); //applies left config
    leftFollower.getConfigurator().apply(leftConfig); //applies left config

    leftFollower.setControl(new Follower(leftLeader.getDeviceID(), MotorAlignmentValue.Aligned)); //sets as follower


    TalonFXConfiguration rightConfig = new TalonFXConfiguration(); //creates config
    rightConfig.MotorOutput.withInverted(InvertedValue.CounterClockwise_Positive); //sets right configuration
    rightLeader.getConfigurator().apply(rightConfig); //applies right config
    rightFollower.getConfigurator().apply(rightConfig); //applies right config

    rightFollower.setControl(new Follower(rightLeader.getDeviceID(), MotorAlignmentValue.Aligned)); //sets as follower

  }

  @Override
  public void simulationPeriodic() {}
}
