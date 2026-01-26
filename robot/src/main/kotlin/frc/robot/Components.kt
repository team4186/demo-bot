package frc.robot

import frc.robot.parts.MotorSet
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import frc.robot.parts.BaseConfigs
import frc.robot.parts.LeftRightConfigs
import frc.robot.parts.TurretConfig
import frc.robot.parts.TurretMotor

/**
 * The [Components] singleton can be used to configure and hold reference to hardware parts
 * used by the [Robot].
 *
 * The only gain here is organizational, as it avoids cluttering in the [Robot] class scope.
 */
object Components {


    object Turret {
        val turretMotor = TurretMotor(
            lead = SparkMax(41, SparkLowLevel.MotorType.kBrushless),
            baseConfig = TurretConfig
        )
    }

//    object Propulsion {
//        val LeftMotorSet = LeftRightConfigs.get("DefaultLeftConfig")?.let {
//            BaseConfigs.get("BaseConfig")?.let { it1 ->
//                MotorSet(
//                    lead = TalonSRX(11),
//                    follower0 = VictorSPX(10),
//                    follower1 = VictorSPX(12),
//                    talonConfig = it1,
//                    victorConfig = it,
//                    inverted = true
//                )
//            }
//        }
//        val RightMotorSet = LeftRightConfigs.get("DefaultRightConfig")?.let {
//            BaseConfigs.get("BaseConfig")?.let { it1 ->
//                MotorSet(
//                    lead = TalonSRX(7),
//                    follower0 = VictorSPX(3),
//                    follower1 = VictorSPX(4),
//                    talonConfig = it1,
//                    victorConfig = it,
//                    inverted = false
//                )
//            }
//        }
//    }
}