package chp_02_forces.ex_01_noiseWind

import org.openrndr.Program
import org.openrndr.math.Vector2
import utils.limit
import kotlin.math.abs

class Mover(program: Program) {
    val radius = 40.0
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = dimensions / 2.0
    var velocity = Vector2.ZERO
    var acceleration = Vector2.ZERO
    var accumulatedForce = Vector2.ZERO

    val maxVelocity = 20.0

    fun applyForce(force: Vector2) {
        accumulatedForce += force
    }

    fun update() {
        acceleration = accumulatedForce

        velocity += acceleration
        velocity = velocity.limit(maxVelocity)
        position += velocity

        // Bounce off edges
        val bounceForce = 0.85
        if (position.x > dimensions.x - radius) {
            velocity = velocity.copy(x = -abs(velocity.x * bounceForce))
        }
        else if (position.x < radius) {
            velocity = velocity.copy(x = abs(velocity.x * bounceForce))
        }
        if (position.y > dimensions.y - radius) {
            velocity = velocity.copy(y = -abs(velocity.y * bounceForce))
        }
        else if (position.y < radius) {
            velocity = velocity.copy(y = abs(velocity.y * bounceForce))
        }

        // Reset force
        accumulatedForce = Vector2.ZERO
    }

    fun display() {
        drawer.circle(position, radius)
    }
}