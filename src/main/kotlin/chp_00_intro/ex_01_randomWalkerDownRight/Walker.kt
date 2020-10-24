package chp_00_intro.ex_01_randomWalkerDownRight

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.random

class Walker(private val drawer: Drawer) {
    var x = drawer.bounds.dimensions.x / 2.0
    var y = drawer.bounds.dimensions.y / 2.0

    fun update() {
        val r = random(0.0, 1.0)
        when {
            r < 0.28 -> x++
            r < 0.56 -> y++
            r < 0.75 -> x--
            else -> y--
        }
    }

    fun draw() {
        drawer.stroke = ColorRGBa.TRANSPARENT
        drawer.rectangle(x, y, 1.0, 1.0)
    }
}