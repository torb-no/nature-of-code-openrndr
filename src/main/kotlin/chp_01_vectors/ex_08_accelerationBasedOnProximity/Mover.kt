package chp_01_vectors.ex_08_accelerationBasedOnProximity

import org.openrndr.Program
import org.openrndr.math.Vector2
import org.openrndr.math.clamp

fun Vector2.limit(d: Double) =
    if (length > d) normalized * d
    else this

class Mover(val program: Program) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = dimensions / 2.0
    var velocity = Vector2.ZERO

    val velocityLimit = 10.0

    fun update() {
        val difference = program.mouse.position - position
        val direction = difference.normalized

        val strength = (difference.length/100 - 0.75).clamp(0.0, 0.75)
        val acceleration = direction * strength

        velocity += acceleration
        velocity = velocity.limit(velocityLimit)
        position += velocity
    }

    fun display() {
        drawer.circle(position, 20.0)
    }
}