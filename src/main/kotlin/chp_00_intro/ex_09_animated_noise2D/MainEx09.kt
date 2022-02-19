package chp_00_intro.ex_09_animated_noise2D

import org.openrndr.application
import org.openrndr.color.ColorHSLa
import org.openrndr.draw.*
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.noise.perlin
import org.openrndr.extra.noise.random
import org.openrndr.math.map
import utils.QuitOnEsc

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend(QuitOnEsc())
        extend(Screenshots())

        val seed = random(0.0, 10000.0).toInt()

        val cb = colorBuffer(width, height)

        var z = 0.0

        extend {
            // Download cb's contents so it's ready to manipulate
            cb.shadow.download()

            z += 0.02
            for (x in 0 until width) {
                for (y in 0 until height) {
                    val hueSat = perlin(seed, x * 0.0022, y * 0.003, z)
                    val lum = map(0.0, 1.0, 0.25, 1.5, perlin(seed, x * 0.008, y * 0.002, z))
                    val color = ColorHSLa(hueSat * 0.001, hueSat * 0.25, lum, 1.0)
                    cb.shadow[x, y] = color.toRGBa()
                }
            }

            // Upload shadow so cb get's updated
            cb.shadow.upload()

            drawer.image(cb)
        }
    }
}
