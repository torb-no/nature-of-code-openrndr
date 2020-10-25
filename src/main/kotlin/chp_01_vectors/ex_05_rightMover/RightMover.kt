package chp_01_vectors.ex_05_rightMover

import org.openrndr.KEY_ARROW_DOWN
import org.openrndr.KEY_ARROW_UP
import org.openrndr.Program
import org.openrndr.math.Vector2
import org.openrndr.math.clamp

val minVelocity = Vector2(0.0)
val maxVelocity = Vector2(25.0)

class RightMover(program: Program) {
    val drawer = program.drawer

    var position = drawer.bounds.dimensions / 2.0
    var velocity = Vector2.ZERO
    var acceleration = Vector2.ZERO

    fun update() {
        velocity += acceleration
        velocity = velocity.clamp(minVelocity, maxVelocity)
        position += velocity

        if (position.x > drawer.width) {
            position = position.copy(x = 0.0)
        }
    }

    fun draw() {
        drawer.circle(position, 20.0)
    }

    init {
        program.keyboard.keyDown.listen {
            if (it.key == KEY_ARROW_UP) {
                acceleration = Vector2(0.333, 0.0)
            }
            if (it.key == KEY_ARROW_DOWN) {
                acceleration = Vector2(-1.0, 0.0)
            }
        }
        program.keyboard.keyUp.listen {
            acceleration = Vector2.ZERO
        }
    }
}