package chp_00_intro.ex_03_dynamic_walker

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.uniform
import org.openrndr.math.Vector2

class Walker(private val program: Program) {
    private val drawer = program.drawer
    private var position = drawer.bounds.dimensions / 2.0

    fun updateAndDraw() {
        val diff = (program.mouse.position - position) * 0.00033

        for (i in 1..15) {
            position += Vector2.uniform(-Vector2.ONE + diff, Vector2.ONE + diff)

            drawer.stroke = ColorRGBa.TRANSPARENT
            drawer.rectangle(position, 1.0, 1.0)
        }

    }
}