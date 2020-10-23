package Chapter00Intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.random
import kotlin.math.cos
import kotlin.math.sin

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        var x = width / 2.0
        var y = height / 2.0

        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.BLACK)
        }


        extend {
            val r = random(0.0, 1.0)
            when {
                r < 0.28 -> x++
                r < 0.56 -> y--
                r < 0.75 -> x--
                else -> y--
            }

            drawer.isolatedWithTarget(rt) {
                drawer.stroke = ColorRGBa.TRANSPARENT
                drawer.rectangle(x, y, 1.0, 1.0)
//                drawer.circle(x, y, 20.0)
            }

            drawer.image(rt.colorBuffer(0))
        }
    }
}
