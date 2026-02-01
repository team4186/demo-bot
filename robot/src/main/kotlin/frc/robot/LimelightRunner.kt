package frc.robot

import edu.wpi.first.networktables.DoublePublisher
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.networktables.DoubleSubscriber
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import java.lang.Math.tan

//fun getXOffset(): Double {
//    // tx
//    return botPoseTargetSpace.get(0)
//}
//
//class LimelightRunner () {
//    private val tableTag: NetworkTable? = null
//    private val botPoseTargetSpace: DoubleArray
//    private val botPose: DoubleArray
//    private val TagID = 0
//    private val emptyArray: DoubleArray
//    private val useMegaTag2 = false
//    private val LLHelpersBotPoseTargetSpace: DoubleArray
//    fun periodic()  {
//        SmartDashboard.putNumber("Limelight_Angle", getThetaOffset());
//    }
//}

class LimelightRunner(
    private val tableTag: NetworkTable = NetworkTableInstance.getDefault().getTable("limelight"),
    private val tvSub: DoubleSubscriber = tableTag.getDoubleTopic("tv").subscribe(0.0),
    private val ledPub: DoublePublisher = tableTag.getDoubleTopic("ledMode").publish()
) {
    fun periodic() {
         SmartDashboard.putBoolean("Has Target Tag?", hasTargetTag)
         ledPub.set( (if (hasTargetTag) 3.0 else 1.0) )
//        SmartDashboard.putNumber("X Offset", tagxOffset)
//        //SmartDashboard.putNumber("Y Offset", yOffset)
//        SmartDashboard.putNumber("% of Image", tagArea)
//        SmartDashboard.putNumber("Distance", Units.metersToInches(distance))
    }


    private val hasTargetTag: Boolean
        get() {
            return tvSub.get() > 0.0
        }

    fun close (){
        tvSub.close()
        ledPub.close()
    }

    val tagxOffset: Double get() = tableTag.getEntry("tx").getDouble(0.0) // -29.8 to 29.8 degrees
    val tagyOffset: Double get() = tableTag.getEntry("ty").getDouble(0.0)
    val tagArea: Double get() = tableTag.getEntry("ta").getDouble(0.0)


    //returns distance to AprilTag
    val distance: Double
        get() {
            //33.75 is height in inches of AprilTag off floor
            //21.41 degrees is mounting angle of limelight
            val angleInRadians = Math.toRadians((21.41 + tagyOffset))
            val distance = 33.75 / tan(angleInRadians)

            return if (hasTargetTag) distance else Double.NaN
        }

    //Subject to change
//  override val distance: Double
//        get() {
//            val targetHeight = 2.6416
//            val cameraHeight = 0.81 //Subject to change
//
//            val cameraAngle = 50.0 //Subject to change (ish)
//
//            val targetAngle: Double = yOffset
//            val totalAngleRad = Units.degreesToRadians(cameraAngle + targetAngle)
//            val distance = (targetHeight - cameraHeight) / tan(totalAngleRad)
//
//            return if (hasTarget) distance else Double.NaN
//        }


    fun setLight(mode: Boolean) {
        tableTag.getEntry("ledMode").setValue(if (mode) 3.0 else 1.0)
    }

//    fun lookupTableRound(distanceToTag: Double): Int =
//        ((distanceToTag - 36.37) / 12.0)
//            .roundToInt()
//            .coerceIn(0, 11)
}