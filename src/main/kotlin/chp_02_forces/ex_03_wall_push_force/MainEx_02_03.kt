package chp_02_forces.ex_03_wall_push_force

import org.openrndr.application
import org.openrndr.extensions.Screenshots
import org.openrndr.math.Vector2
import utils.QuitOnEsc
import kotlin.math.absoluteValue

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend(QuitOnEsc())
        extend(Screenshots())

        val middle = drawer.bounds.dimensions / 2.0
        val edgeMultiplier = 0.005

        val pushThreshold = drawer.bounds.dimensions / 2.5

        val gravity = Vector2(0.0, 0.1)
        val wind = Vector2(0.01, 0.0)

        val balls = (1..10).map { Ball(this) }


        extend {
            balls.forEach {
                it.applyForce(gravity)
                it.applyForce(wind)

                // Edge forces
                val diff = middle - it.position
                val edgeForce = Vector2(
                    if (diff.x.absoluteValue > pushThreshold.x) diff.x * edgeMultiplier
                    else 0.0,
                    if (diff.y.absoluteValue > pushThreshold.y) diff.y * edgeMultiplier
                    else 0.0,
                )
                it.applyForce(edgeForce)

                it.update()

            }

            balls.forEach { it.display() }
        }
    }
}
