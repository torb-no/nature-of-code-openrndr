package chp_00_intro.ex_03_dynamic_walker

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*

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

        val walker = Walker(this)

        extend {
            drawer.isolatedWithTarget(rt) {
                walker.updateAndDraw()
            }

            drawer.image(rt.colorBuffer(0))
        }
    }
}
