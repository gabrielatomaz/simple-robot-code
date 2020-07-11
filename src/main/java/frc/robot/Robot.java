package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private SpeedControllerGroup motorsLeft;
  private SpeedCOntrollerGroup motorsRight;
  private DifferentialDrive driver;

  private VictorSPX shooter;

  private Joystick pilot;
  private Joystick operator;

  private Timer timer;

  @Override
  public void robotInit() {
    pilot = new Joystick(0);
    operator = new Joystick(1);

    motorsLeft = new SpeedControllerGroup(new Talon(0), new Talon(1));
    motorsRight = new SpeedCOntrollerGroup(new Talon(2), new Talon(3));

    driver = new DifferentialDrive(motorsLeft, motorsRight);

    shooter = new VictorSPX(4);

    timer = new Timer();
  }

  @Override
  public void robotPeriodic() { }
 
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }
 
  @Override
  public void autonomousPeriodic() {
    System.out.println(timer.get());
    if (timer.get() < 5) {
      driver.arcadeDrive(0.3, 0);
    } else {
      driver.arcadeDrive(0, 0);
    }
  }

  
  @Override
  public void teleopInit() { }

  @Override
  public void teleopPeriodic() {
    driver.arcadeDrive(pilot.getY(), pilot.getRawAxis(4));

    if (operator.getRawButton(3)) {
      shooter.set(0.5);
    } else {
      shooter.set(0);
    }
  }
 
  @Override
  public void disabledInit() { }

  @Override
  public void disabledPeriodic() { }

  @Override
  public void testInit() { }
  
  @Override
  public void testPeriodic() { }
}
