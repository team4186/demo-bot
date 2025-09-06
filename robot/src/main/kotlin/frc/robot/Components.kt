package frc.robot

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import frc.robot.parts.Propulsion.BaseConfig
import frc.robot.parts.Propulsion.DefaultLeftConfig
import frc.robot.parts.Propulsion.DefaultRightConfig
import frc.robot.parts.MotorSet
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
/**
 * The [Components] singleton can be used to configure and hold reference to hardware parts
 * used by the [Robot].
 *
 * The only gain here is organizational, as it avoids cluttering in the [Robot] class scope.
 */
object Components {
    object Propulsion {
        val LeftMotorSet = MotorSet(
            lead = TalonSRX(11),
            follower0 = VictorSPX(10),
            follower1 = VictorSPX(12),
            talonConfig = BaseConfig,
            victorConfig = DefaultLeftConfig,
            inverted = true
        )
        val RightMotorSet = MotorSet(
            lead = TalonSRX(7),
            follower0 = VictorSPX(3),
            follower1 = VictorSPX(4),
            talonConfig = BaseConfig,
            victorConfig = DefaultRightConfig,
            inverted = false
        )
    }
}