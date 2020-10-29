package chp_02_forces.ex_01_noiseWind

import org.openrndr.application
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.noise.random
import org.openrndr.extra.noise.simplex
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

        val upwind = Vector2(0.0, -0.002)

        var noisePosition = random(0.0, 1000.0)
        var seed = random(0.0, 1000.0).toInt()

        val mover = Mover(this)

        extend {
            noisePosition += 0.01
            val noiseWind = Vector2.simplex(seed, noisePosition) * 0.15

            mover.applyForce(noiseWind)
            mover.applyForce(upwind)

            mover.update()
            mover.display()
        }
    }
}
