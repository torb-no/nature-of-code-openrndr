package chp_01_vectors.ex_06_noiseAcceleration

import org.openrndr.Program
import org.openrndr.extra.noise.random
import org.openrndr.extra.noise.simplex
import org.openrndr.math.Vector2

class Mover(program: Program) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = dimensions / 2.0
    var velocity = Vector2.ZERO

    var noisePosition = random(0.0, 1000.0)
    val seed = random(0.0, 1000000.0).toInt()

    val maxVelocity = 4.0

    fun update() {
        // Noise acceleration
        noisePosition += 0.01
        val acceleration = Vector2.simplex(seed, noisePosition)

        // Update, limit velocity
        velocity += acceleration
        if (velocity.length > maxVelocity) {
            velocity = velocity.normalized * maxVelocity
        }

        position += velocity

        // Fall through edges
        if (position.x > dimensions.x) {
            position = position.copy(x = 0.0)
        }
        else if (position.x < 0.0) {
            position = position.copy(x = dimensions.x)
        }

        if (position.y > dimensions.y) {
            position = position.copy(y = 0.0)
        }
        else if (position.y < 0) {
            position = position.copy(y = dimensions.y)
        }
    }

    fun display() {
        drawer.circle(position, 20.0)
    }
}