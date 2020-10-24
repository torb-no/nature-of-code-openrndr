package chp00intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.random

/**
 * Create a random walker that has a tendency to move down and to the right.
 * (We’ll see the solution to this in the next section.)
 *
 * http://natureofcode.com/book/introduction/#intro_exercise1
 */

class Walker(val drawer: Drawer) {
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

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.BLACK)
        }

        val walker = Walker(drawer)


        extend {
            walker.update()

            drawer.isolatedWithTarget(rt) {
                walker.draw()
            }

            drawer.image(rt.colorBuffer(0))
        }
    }
}
