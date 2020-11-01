package chp_02_forces.ex_04_friction_pockets

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import utils.QuitOnEsc

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend(QuitOnEsc())
        extend(Screenshots())

        val gravity = Vector2(0.0, 0.1)
        val wind = Vector2(0.01, 0.0)

        val balls = (1..10).map { Ball(this) }

        val frictionPockets = listOf(
            FrictionPocket(this,
                Rectangle(350.0, 300.0, 150.0, 100.0),
                coefficient = 0.055,
            ),
            FrictionPocket(this,
                Rectangle(300.0, 500.0, 300.0, 50.0),
                coefficient = -0.055,
                color = ColorRGBa.GREEN,
            ),
            FrictionPocket(this,
                Rectangle(650.0, 500.0, 100.0, 100.0),
                coefficient = -1.0,
                color = ColorRGBa.YELLOW,
            ),
            FrictionPocket(this,
                Rectangle(300.0, 0.0, 500.0, 100.0),
                coefficient = 0.25,
                color = ColorRGBa.WHITE,
            ),
        )

        extend {
            balls.forEach {
                it.applyForce(gravity)
                it.applyForce(wind)
                frictionPockets.forEach { p -> p.process(it) }

                it.update()
            }

            frictionPockets.forEach { it.display() }

            balls.forEach { it.display() }
        }
    }
}
