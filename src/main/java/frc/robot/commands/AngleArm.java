// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AngleArm extends CommandBase {
  /** Creates a new AngleArm. */
  private Arm arm;
  private double setpoint;
  private PIDController armPID = new PIDController(0, 0, 0);
  public AngleArm(double _setpoint, Arm _arm
  ) {
    armPID.setTolerance(.04);
    SmartDashboard.putData(armPID);
    arm=_arm;
    _setpoint = setpoint;
    addRequirements(_arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     arm.setArmMotor(armPID.calculate(arm.getArmPot(), setpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return armPID.atSetpoint();
  }
}
