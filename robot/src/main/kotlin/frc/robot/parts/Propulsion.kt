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
import com.ctre.phoenix.motorcontrol.InvertType
import com.ctre.phoenix.motorcontrol.ControlMode
import com.revrobotics.config.BaseConfig
import java.util.function.DoubleConsumer


val BaseConfigs = hashMapOf(
    "BaseConfig" to TalonSRXConfiguration()
)

val LeftRightConfigs = hashMapOf(
    "DefaultLeftConfig" to VictorSPXConfiguration(),
    "DefaultRightConfig" to VictorSPXConfiguration()
)

class MotorSet(
    val lead: TalonSRX,
    val follower0: VictorSPX,
    val follower1: VictorSPX,
    val talonConfig: TalonSRXConfiguration,
    val victorConfig: VictorSPXConfiguration,
    val inverted: Boolean
) : DoubleConsumer {
    init {
        lead.configAllSettings(talonConfig)
        follower0.configAllSettings(victorConfig)
        follower1.configAllSettings(victorConfig)
//        TalonSRXConfiguration()
//            .apply(baseConfig)
//            .follow(lead)
//            .let { follower0.configure(it, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters) }
        follower0.follow(lead)
        follower1.follow(lead)
        lead.setInverted(inverted)
        follower0.setInverted(InvertType.FollowMaster)
        follower1.setInverted(InvertType.FollowMaster)
    }

    override fun accept(value: Double) {
        lead.set(ControlMode.PercentOutput, value)
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