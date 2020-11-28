package chp_02_forces.ex_06_boxHittingWater

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import org.openrndr.math.clamp
import kotlin.math.abs

class Box(
        program: Program,
        var position: Vector2,
        val size: Vector2,
) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var velocity = Vector2.ZERO
    var acceleration = Vector2.ZERO

    val mass = 3.0

    val color = ColorRGBa(
            random(0.3, 0.8),
            random(0.3, 0.8),
            random(0.3, 0.8),
            a = 0.66
    )

    val maxPos = dimensions - Vector2(size.x, size.y)

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

        // Never be outside:
        position = position.clamp(Vector2.ZERO, maxPos)
    }

    fun display() {
        drawer.stroke = ColorRGBa.TRANSPARENT
        drawer.fill = color

        drawer.rectangle(position, size.x, size.y)
    }

    fun applyForce(force: Vector2) {
        acceleration += (force / mass)
    }
}