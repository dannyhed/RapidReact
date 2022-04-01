// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import java.beans.IntrospectionException;
import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private CANSparkMax shooter = new CANSparkMax(RM.shooterMap, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax intake = new CANSparkMax(RM.intake, CANSparkMaxLowLevel.MotorType.kBrushless);
  
  private final TalonSRX fl = new TalonSRX(RM.frontLeft);
  private final TalonSRX fr = new TalonSRX(RM.frontRight);
  private final TalonSRX bl = new TalonSRX(RM.backLeft);
  private final TalonSRX br = new TalonSRX(RM.backRight);

  private final Joystick joy = new Joystick(0);

  Compressor comp = new Compressor(RM.compressor, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid paddle = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RM.shootup, RM.shootdown);

  //private final DoubleSolenoid intakeL = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RM.intakeLup, RM.intakeLdown);
  //private final DoubleSolenoid intakeR = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RM.intakeRup, RM.intakeRdown);

  private final float SPD = 1f;
  private final float sideSPD = 0.5f;
<<<<<<< HEAD
  private final float shootSPD = 0.6f;
  private final float intakeSPD = 0.6f;
=======
  private final float shootSPD = 0.4f;
  private final float intakeSPD = 0.25f;

  private boolean intakeUp = true;
  private boolean intakeButton = false;
>>>>>>> 1de5afec4264145f3a5ebc9581567a7a32203e8f

  //private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    /*
    m_timer.reset();
    m_timer.start();
    */
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    /*
    if (m_timer.get() < 2.0) {
      m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
    */
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    fr.set(ControlMode.PercentOutput, SPD * (joy.getRawAxis(CM.forwardR) + sideSPD * joy.getRawAxis(CM.sidewaysR)));
    br.set(ControlMode.PercentOutput, SPD * (joy.getRawAxis(CM.forwardR) - sideSPD * joy.getRawAxis(CM.sidewaysR)));
    fl.set(ControlMode.PercentOutput, SPD * (-joy.getRawAxis(CM.forwardL) + sideSPD * joy.getRawAxis(CM.sidewaysL)));
    bl.set(ControlMode.PercentOutput, SPD * (-joy.getRawAxis(CM.forwardL) - sideSPD * joy.getRawAxis(CM.sidewaysL)));

    shooter.set(shootSPD * joy.getRawAxis(CM.shootSpin));
    
    intake.set(intakeSPD * joy.getRawAxis(CM.intakeSpin));

    /*if (joy.getRawButton(CM.intakePos) && intakeUp) {
      intakeL.set(Value.kForward);
      intakeR.set(Value.kForward);
      intakeButton = true;
    }
    if (joy.getRawButton(CM.intakePos) && !intakeUp){
      intakeL.set(Value.kReverse);
      intakeR.set(Value.kReverse);
      intakeButton = true;
    }
    if (intakeButton && !joy.getRawButton(CM.intakePos)) {
      intakeUp = false;
      intakeButton = false;
    }*/
    if (joy.getRawButton(6))
      paddle.set(Value.kReverse);
    else
      paddle.set(Value.kForward);
    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
