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

import org.ejml.simple.AutomaticSimpleMatrixConvert;

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

<<<<<<< HEAD
  private final float SPD = 0.5f;
  private final float sideSPD = 1.3f;
  private final float shootSPD = 0.45f;
  private final float intakeSPD = 0.2f;
  private final float intakeSPDb = 0.4f;
  private final float intakeGentle = 2.0f;
  private final float speedDist = 0.9f;

  private final float SPD = 0.5f;
  private final float sideSPD = 0.5f;
  private final float shootSPD = 0.45f;
  private final float intakeSPD = 0.25f;
  private final float SPD = 0.6f;
  private final float sideSPD = 1.2f;
  private final float shootSPD = 0.45f;
  private final float intakeSPD = 0.15f;
  private final float revSpeed = 0.6f;

  //Autonomous timing
  private final float autoBack = 1.0f;
  private final float autoJerk = 0.25f;
  private final float jerkSPD = 0.8f;
  private final float autoFwd = 1.5f;
  private final float autoRest = 0.25f;
  private final float autoRev = 1.0f;
  private final float autoPaddle = 0.5f;
  private final float autoSPD = 0.4f;
  private final float autoDelay = 5.0f;

  private boolean intakeUp = true;
  private boolean intakeButton = false;

  
  private final Timer timer = new Timer();
  private final Timer intakeTimer = new Timer();
  private final Timer shootTimer = new Timer();

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
    timer.reset();
    timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (timer.get() < autoDelay) {}
    else if (timer.get() < autoDelay + autoRev) {
      shooter.set(shootSPD);
    }
    else if (timer.get() < autoDelay + autoRev + autoPaddle) {
      paddle.set(Value.kReverse);
    }
    else if (timer.get() < autoDelay + autoRev + autoPaddle + autoJerk) {
      fr.set(ControlMode.PercentOutput, jerkSPD);
      br.set(ControlMode.PercentOutput, jerkSPD);
      fl.set(ControlMode.PercentOutput, -jerkSPD);
      bl.set(ControlMode.PercentOutput, -jerkSPD);

      shooter.set(0);
      paddle.set(Value.kForward);
    }
    else if (timer.get() < autoDelay + autoRev + autoPaddle + autoJerk + autoBack) {
      fr.set(ControlMode.PercentOutput, autoSPD);
      br.set(ControlMode.PercentOutput, autoSPD);
      fl.set(ControlMode.PercentOutput, -autoSPD);
      bl.set(ControlMode.PercentOutput, -autoSPD);

    }
    else {
      fr.set(ControlMode.PercentOutput, 0);
      br.set(ControlMode.PercentOutput, 0);
      fl.set(ControlMode.PercentOutput, 0);
      bl.set(ControlMode.PercentOutput, 0);
    }
    /* 
    if (timer.get() < autoDelay) {

    }
    else if (timer.get() < autoBack + autoDelay) {
      fr.set(ControlMode.PercentOutput, autoSPD);
      br.set(ControlMode.PercentOutput, autoSPD);
      fl.set(ControlMode.PercentOutput, -autoSPD);
      bl.set(ControlMode.PercentOutput, -autoSPD);
    } 
    else if (timer.get() < (autoBack + autoRest + autoDelay)){
      fr.set(ControlMode.PercentOutput, 0);
      br.set(ControlMode.PercentOutput, 0);
      fl.set(ControlMode.PercentOutput, 0);
      bl.set(ControlMode.PercentOutput, 0);
    }
    else if (timer.get() < (autoBack + autoRest + autoFwd + autoDelay)){
      fr.set(ControlMode.PercentOutput, -autoSPD);
      br.set(ControlMode.PercentOutput, -autoSPD);
      fl.set(ControlMode.PercentOutput, autoSPD);
      bl.set(ControlMode.PercentOutput, autoSPD);
    }
    else if (timer.get() < (autoBack + autoRest + autoFwd + autoRev + autoDelay)) {
      fr.set(ControlMode.PercentOutput, 0);
      br.set(ControlMode.PercentOutput, 0);
      fl.set(ControlMode.PercentOutput, 0);
      bl.set(ControlMode.PercentOutput, 0);
      shooter.set(revSpeed);
    }
    else if (timer.get() < (autoBack + autoRest + autoFwd + autoRev + autoPaddle + autoDelay)){
      paddle.set(Value.kReverse);
    }
    else {
      shooter.set(0);
      paddle.set(Value.kForward);
    }*/
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    intakeTimer.reset();
    intakeTimer.start();
    shootTimer.reset();
    shootTimer.start();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    fr.set(ControlMode.PercentOutput, SPD * (joy.getRawAxis(CM.forwardR) + sideSPD * joy.getRawAxis(CM.sidewaysR)));
    br.set(ControlMode.PercentOutput, speedDist * SPD * (joy.getRawAxis(CM.forwardR) - sideSPD * joy.getRawAxis(CM.sidewaysR)));
    fl.set(ControlMode.PercentOutput, SPD * (-joy.getRawAxis(CM.forwardL) + sideSPD * joy.getRawAxis(CM.sidewaysL)));
    bl.set(ControlMode.PercentOutput, speedDist * SPD * (-joy.getRawAxis(CM.forwardL) - sideSPD * joy.getRawAxis(CM.sidewaysL)));


    if (joy.getRawAxis(CM.shootSpin) == 0) {
      shootTimer.reset();
      shootTimer.start();
      if (joy.getRawButton(CM.shootPaddle)) {
        paddle.set(Value.kReverse);
      }
      else
        paddle.set(Value.kForward);
    }
    else if (shootTimer.get() < autoRev){    
      shooter.set(shootSPD * joy.getRawAxis(CM.shootSpin));
      /*if (joy.getRawButton(CM.shootPaddle)) {
        paddle.set(Value.kReverse);
      }
      else
        paddle.set(Value.kForward);*/
    }
    else {
      if (!joy.getRawButton(CM.shootPaddle)) {
        paddle.set(Value.kReverse);
      }
      else paddle.set(Value.kForward);
      shooter.set(shootSPD * joy.getRawAxis(CM.shootSpin));
    }
    
    //intake.set(intakeSPD * joy.getRawAxis(CM.intakeSpin));

    //////////////// TOGGLE INTAKE /////////////////
    if (joy.getRawButton(CM.intakePos) && intakeUp && !intakeButton) {
      intakeTimer.reset();
      intakeTimer.start();
      intakeButton = true;
      intakeUp = false;
    }
    if (joy.getRawButton(CM.intakePos) && !intakeUp && !intakeButton){
      intakeTimer.reset();
      intakeTimer.start();
      intake.set(0);
      intakeButton = true;
      intakeUp = true;
    }
    if (intakeButton && !joy.getRawButton(CM.intakePos)) {
      intakeButton = false;
    }
    if (intakeTimer.get() < intakeGentle) {
      if (intakeUp) {
        intake.set(intakeSPDb * 1 / (intakeGentle / intakeTimer.get()) * joy.getRawAxis(CM.intakeSpin));
      }
      if (!intakeUp) {
        intake.set(intakeSPD * 1 / (intakeGentle / intakeTimer.get()));
      }
    }
    else {
      if (intakeUp) {
        intake.set(intakeSPDb * joy.getRawAxis(CM.intakeSpin));
      }
      if (!intakeUp) {
        intake.set(intakeSPD);
      }
    }
    if (joy.getRawAxis(CM.intakeSpin) == 0 && intakeUp) {
      intakeTimer.reset();
      intakeTimer.start();
    }
    ////////////////////////////////////////////////
    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
