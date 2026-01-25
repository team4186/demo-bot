package frc.robot.parts

import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import java.util.function.DoubleConsumer


val TurretConfig = SparkMaxConfig()
    .smartCurrentLimit(50)
    .idleMode(SparkBaseConfig.IdleMode.kBrake)

class TurretMotor(
    val lead: SparkMax,
    val baseConfig: SparkBaseConfig
) : DoubleConsumer {
    init {
        lead.configure(baseConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }


    override fun accept(value: Double) {
        lead.set(value)
    }


    fun stop() {
        lead.stopMotor()
    }
}


