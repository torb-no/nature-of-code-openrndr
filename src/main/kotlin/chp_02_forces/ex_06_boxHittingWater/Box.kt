package chp_02_forces.ex_06_boxHittingWater

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import org.openrndr.math.clamp
import kotlin.math.abs

class Box(
        program: Program,
        x: Double,
) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = Vector2(x, random(25.0, 300.0))
    var velocity = Vector2.ZERO
    var acceleration = Vector2.ZERO

    val mass = 3.0
//    val radius = mass * 10.0

    val color = ColorRGBa(
            random(0.3, 0.8),
            random(0.3, 0.8),
            random(0.3, 0.8),
            a = 0.66
    )

//    val radiusVec = Vector2(radius, radius)
//    val minPos = radiusVec
//    val maxPos = dimensions - minPos

    fun update() {
        velocity += acceleration
        position += velocity

        acceleration = Vector2.ZERO

        // Bounce off edges: TODO
//        val bounceForce = 0.85
//        if (position.x > dimensions.x - radius) {
//            velocity = velocity.copy(x = -abs(velocity.x * bounceForce))
//        }
//        else if (position.x < radius) {
//            velocity = velocity.copy(x = abs(velocity.x * bounceForce))
//        }
//        if (position.y > dimensions.y - radius) {
//            velocity = velocity.copy(y = -abs(velocity.y * bounceForce))
//        }
//        else if (position.y < radius) {
//            velocity = velocity.copy(y = abs(velocity.y * bounceForce))
//        }

        // Never be outside: TODO
//        position = position.clamp(minPos, maxPos)
    }

    fun display() {
        drawer.stroke = ColorRGBa.TRANSPARENT
        drawer.fill = color

//        drawer.circle(position, radius)
    }

    fun applyForce(force: Vector2) {
        acceleration += (force / mass)
    }
}