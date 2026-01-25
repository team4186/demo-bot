package frc.robot.actions

import kotlin.math.pow
import kotlin.math.withSign
import kotlin.math.absoluteValue

fun moveTurret(
    rotate: Double
): Double {
    return attentuated(rotate)
}


private fun attentuated(value: Double):Double {
    return 0.10 * value.absoluteValue.pow(2).withSign(value)
}