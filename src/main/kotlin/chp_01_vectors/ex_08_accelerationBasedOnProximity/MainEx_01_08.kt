package chp_01_vectors.ex_08_accelerationBasedOnProximity

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

        val mover = Mover(this)

        extend {
            mover.update()
            mover.display()
        }
    }
}
