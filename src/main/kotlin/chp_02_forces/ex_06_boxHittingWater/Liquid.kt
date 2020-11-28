package chp_02_forces.ex_06_boxHittingWater

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

class Liquid(
        program: Program,
        val area: Rectangle,
        val coefficient: Double,
) {
    val drawer = program.drawer

    fun process(box: Box) {
        // Maybe take different x and y dragCoeffients into account?
        // (Currently x dragCoefficient affects all axes)

        if (area.contains(box.position) || area.contains(box.position + box.size)) {
            // Apply friction
            val dragCoefficient = box.size.x
            val dragMagnitude = box.velocity.squaredLength * coefficient * dragCoefficient * 0.01
            val dragForce = box.velocity.normalized * -dragMagnitude
            box.applyForce(dragForce)
        }
    }

    fun display() {
        drawer.fill = ColorRGBa(0.5, 0.5, 1.0, 0.85)
        drawer.rectangle(area)
    }
}