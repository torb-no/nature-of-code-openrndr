package chp_02_forces.ex_05_liquidDrag

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

class Liquid(
        program: Program,
        val area: Rectangle,
        val coefficient: Double,
) {
    val drawer = program.drawer

    fun process(ball: Ball) {
        if (area.contains(ball.position - ball.radiusVec) ||
            area.contains(ball.position + ball.radiusVec)) {

            // Apply friction
            val dragMagnitude = ball.velocity.squaredLength * coefficient
            val dragForce = ball.velocity.normalized * -dragMagnitude
            ball.applyForce(dragForce)
        }
    }

    fun display() {
        drawer.fill = ColorRGBa(0.5, 0.5, 1.0, 0.85)
        drawer.rectangle(area)
    }
}