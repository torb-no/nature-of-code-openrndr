package chp_00_intro.ex_01_randomWalkerDownRight

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extensions.Screenshots

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend(Screenshots())

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
