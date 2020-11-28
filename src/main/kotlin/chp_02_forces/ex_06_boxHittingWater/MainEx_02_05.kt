package chp_02_forces.ex_06_boxHittingWater

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

        val gravity = Vector2(0.0, 0.2)
        val wind = Vector2(0.01, 0.0)

        val boxes = mutableListOf<Box>()

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

        var drag: Rectangle? = null
        mouse.buttonDown.listen {
            drag = Rectangle(it.position, 1.0, 1.0)
        }

        mouse.dragged.listen {
            val currentDrag = drag
            if (currentDrag != null) {
                val size = it.position - currentDrag.corner
                drag = currentDrag.copy(width = size.x, height = size.y)
            }
        }

        mouse.buttonUp.listen {
            val currentDrag = drag
            if (currentDrag != null) {
                val size = Vector2(currentDrag.width, currentDrag.height)
                boxes.add(Box(this, currentDrag.corner, size))
            }
            drag = null
        }

        extend {
            boxes.forEach {
                it.applyForce(gravity)
                it.applyForce(wind)
                it.update()
                liquid.process(it)

                it.display()
            }

            liquid.display()

            val currentDrag = drag
            if (currentDrag != null) {
                drawer.fill = ColorRGBa.GRAY
                drawer.rectangle(currentDrag)
            }
        }
    }
}
