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
        // TODO
//        if (area.contains(box.position - box.radiusVec) ||
//                area.contains(box.position + box.radiusVec)) {
//
//            // Apply friction
//            val dragMagnitude = box.velocity.squaredLength * coefficient
//            val dragForce = box.velocity.normalized * -dragMagnitude
//            box.applyForce(dragForce)
//        }
    }

    fun display() {
        drawer.fill = ColorRGBa(0.5, 0.5, 1.0, 0.85)
        drawer.rectangle(area)
    }
}