package chp_01_vectors.ex_01_rightMover

import org.openrndr.application
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

        val rightMover = RightMover(this)

        extend {
            rightMover.update()
            rightMover.draw()
        }
    }
}
