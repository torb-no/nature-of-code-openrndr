package chp_02_forces.ex_06_boxHittingWater

import org.openrndr.application
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

        val gravity = Vector2(0.0, 0.2)
        val wind = Vector2(0.01, 0.0)

        val balls = (1..10).map { Box(this, it*70.0) }


        val dimensions = drawer.bounds.dimensions
        val liquidHeight = 150.0
        val liquid = Liquid(this,
            Rectangle(
                x = 0.0,
                y = dimensions.y - liquidHeight,
                width = dimensions.x,
                height = liquidHeight),
            coefficient = 0.1
        )

        extend {
            balls.forEach {
                it.applyForce(gravity)
                it.applyForce(wind)
                it.update()
                liquid.process(it)

                it.display()
            }

            liquid.display()
        }
    }
}
