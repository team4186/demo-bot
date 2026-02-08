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

// previous pid values
//         0.0065,
//        0.0,
//        0.00375)

//Not Working \/\/\/\/\/
fun alignTurretCameraOnly(
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

fun alignTurret(
    angleOffset: Double,
    pidFar: PIDController,
    pidClose: PIDController,
    currentLocation: Double
): Double {
    val diff: Double = angleOffset - currentLocation
    var result: Double

    if (Math.abs(diff) > 45) {
        result = pidFar.calculate(currentLocation, (diff) )
    } else {
        result = pidClose.calculate(currentLocation, (diff) )
    }

    return Math.max(-0.4, Math.min(0.4, result ))
}


private fun attentuated(value: Double):Double {
    return 0.10 * value.absoluteValue.pow(2).withSign(value)
}