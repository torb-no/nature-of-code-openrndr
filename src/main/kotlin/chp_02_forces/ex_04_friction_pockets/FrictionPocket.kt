package chp_02_forces.ex_04_friction_pockets

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

class FrictionPocket(
    program: Program,
    val area: Rectangle,
    val coefficient: Double,
    val color: ColorRGBa = ColorRGBa.GRAY,
) {
    val drawer = program.drawer

    fun process(ball: Ball) {
        if (area.contains(ball.position - ball.radiusVec) ||
            area.contains(ball.position + ball.radiusVec)) {

            // Apply friction
            val frictionForce = ball.velocity.normalized * -coefficient
            ball.applyForce(frictionForce)
        }
    }

    fun display() {
        drawer.fill = color
        drawer.rectangle(area)
    }
}