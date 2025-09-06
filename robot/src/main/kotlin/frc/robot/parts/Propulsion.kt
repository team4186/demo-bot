package frc.robot.parts

import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import java.util.function.DoubleConsumer


private val BaseConfig = TalonSRXConfiguration()
// .idleMode(SparkBaseConfig.IdleMode.kCoast)

val DefaultLeftConfig = VictorSPXConfiguration()

val DefaultRightConfig = VictorSPXConfiguration()


class MotorSet(
    val lead: TalonSRX,
    val follower0: VictorSPX,
    val follower1: VictorSPX,
    val talonConfig: TalonSRXConfiguration,
    val victorConfig: VictorSPXConfiguration,
    val inverted: Boolean
) : DoubleConsumer {
    init {
        victorConfig.continuousCurrentLimit = 50
        lead.configureAllSettings(talonConfig: TalonSRXConfiguration)
        follower0.configureAllSettings(victorConfig: VictorSPXConfiguration)
        follower1.configureAllSettings(victorConfig: VictorSPXConfiguration)
//        TalonSRXConfiguration()
//            .apply(baseConfig)
//            .follow(lead)
//            .let { follower0.configure(it, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters) }
        follower0.follow(lead)
        follower1.follow(lead)
        lead.setInverted(inverted)
        follower0.setInverted(InvertType.FollowMaster)
        follower1:setInverted(InvertType.FollowMaster)
    }

    override fun accept(value: Double) {
        lead.set(ControlMode.PercentOutput, value * 0.8)
    }

    fun stop() {
        lead.set(ControlMode.PercentOutput, 0.0)
    }

//    fun setIdleMode(mode: SparkBaseConfig.IdleMode) {
//        lead.configure(
//            SparkMaxConfig()
//                .apply(baseConfig)
//                .idleMode(mode),
//            ResetMode.kResetSafeParameters,
//            PersistMode.kPersistParameters,
//        )
//    }
}