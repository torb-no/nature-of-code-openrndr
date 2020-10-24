package ex_06_customProbability

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extensions.Screenshots
import utils.QuitOnEsc

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend(QuitOnEsc())
        extend(Screenshots())

        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.BLACK)
        }

        val walker = Walker(this)

        extend {
            walker.update()

            drawer.isolatedWithTarget(rt) {
                walker.draw()
            }

            drawer.image(rt.colorBuffer(0))
        }
    }
}
