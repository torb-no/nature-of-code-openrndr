package chp_01_vectors.ex_08_accelerationBasedOnProximity

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import org.openrndr.math.clamp
import utils.limit

class Mover(val program: Program) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = dimensions / 2.0
    var velocity = Vector2.ZERO

    val velocityLimit = 10.0

    fun updateAndDisplay() {
        // Update
        val difference = program.mouse.position - position
        val differenceScalar = difference.length / 100.0
        val direction = difference.normalized

        val strength = (differenceScalar - 0.75).clamp(0.0, 0.75)
        val acceleration = direction * strength

        velocity += acceleration
        velocity = velocity.limit(velocityLimit)
        position += velocity

        // Display
        drawer.circle(position, 20.0)

        drawer.stroke = ColorRGBa.WHITE
        drawer.strokeWeight = 5.0 - differenceScalar
        drawer.lineSegment(program.mouse.position, position)
    }
}