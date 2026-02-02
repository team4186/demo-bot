package frc.robot

import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import frc.robot.actions.manualDrive
import frc.robot.actions.moveTurret
import frc.robot.Components.Turret
import frc.robot.LimelightRunner
import frc.robot.actions.alignTurret

class Robot : TimedRobot() {
    private val joystick0 = Joystick(0)
    private val joystick1 = Joystick(1)
//    private val drive = DifferentialDrive(
//        Components.Propulsion.LeftMotorSet,
//        Components.Propulsion.RightMotorSet,
//    )

    private var limelight: LimelightRunner = LimelightRunner()
    private val turret = Turret.turretMotor
    private val alignPID: PIDController = PIDController(
        0.0065,
        0.0,
        0.00375)

    private val autonomousChooser = SendableChooser<Command>()

    override fun robotInit() {
        HAL.report(FRCNetComm.tResourceType.kResourceType_Language, FRCNetComm.tInstances.kLanguage_Kotlin)

        enableLiveWindowInTest(true)

//        with(autonomousChooser) {
//            setDefaultOption("Nothing", null)
//            SmartDashboard.putData("Autonomous Mode", this)
//        }
    }

    override fun robotPeriodic() {
//        CommandScheduler.getInstance().run()
        limelight.periodic()
    }

    override fun autonomousInit() {
//        autonomousChooser.selected?.schedule()
    }

    override fun autonomousPeriodic() {
    }

    override fun autonomousExit() {
//        CommandScheduler.getInstance().cancelAll()
    }

    override fun teleopInit() {
    }

    override fun teleopPeriodic() {
//        manualDrive(
//            forward = joystick0.y,
//            turn = joystick0.twist,
//            drive = { forward, turn -> drive.arcadeDrive(forward, turn, false) }
//        )

        turret.accept(
            moveTurret(joystick1.twist)
        )
    }

    override fun teleopExit() {
//        CommandScheduler.getInstance().cancelAll()
    }

    override fun testInit() {
    }

    override fun testPeriodic() {
        val xOffset: Double = SmartDashboard.getNumber("tx", 0.0)

        val result: Double = alignTurret(
            xOffset,
            alignPID
        )

        SmartDashboard.putNumber("Align results", result)

        turret.accept(
            // moveTurret(joystick1.twist)
            result
        )
    }
    override fun close() {
        super.close()
        limelight.close()
    }
}
