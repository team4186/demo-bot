package frc.robot.actions

import edu.wpi.first.math.controller.PIDController
import kotlin.math.pow
import kotlin.math.withSign
import kotlin.math.absoluteValue

fun moveTurret(
    rotate: Double
): Double {
    return attentuated(rotate)
}


fun alignTurret(
    angleOffset: Double,
    pid: PIDController
): Double {
    var result = pid.calculate(angleOffset, 0.0)
    val sign = if (result >= 0) 1 else -1
    val thres: Double = 0.2
    if (result > thres || result < -thres) {
        result = 0.2 * sign
    }

    return result * -1
}


private fun attentuated(value: Double):Double {
    return 0.10 * value.absoluteValue.pow(2).withSign(value)
}