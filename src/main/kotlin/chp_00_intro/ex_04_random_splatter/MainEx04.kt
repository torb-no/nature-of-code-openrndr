package chp_00_intro.ex_04_random_splatter

import org.openrndr.application
import org.openrndr.color.ColorHSLa
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.noise.gaussian
import org.openrndr.math.Vector2
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

        // Vector versions of gaussian values
        val mean = drawer.bounds.dimensions / 2.0
        val deviation = Vector2.ONE * 100.0

        // Unchanging color values
        val saturation = 0.75
        val lightness = 0.5
        val alpha = 0.66

        extend {
            drawer.isolatedWithTarget(rt) {
                val position = Vector2.gaussian(mean, deviation)
                val hue = gaussian(128.0, 50.0)

                drawer.stroke = ColorRGBa.TRANSPARENT
                drawer.fill = ColorHSLa(hue, saturation, lightness, alpha).toRGBa()
                drawer.circle(position, 20.0)
            }

            drawer.image(rt.colorBuffer(0))
        }
    }
}
