package chp_02_forces.ex_03_wall_push_force

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2

class Ball(program: Program) {
    val drawer = program.drawer
    val dimensions = drawer.bounds.dimensions

    var position = dimensions / 3.0
    var velocity = Vector2.ZERO
    var acceleration = Vector2.ZERO

    val mass = random(1.0, 10.0)
    val color = ColorRGBa(
            random(0.3, 0.8),
            random(0.3, 0.8),
            random(0.3, 0.8),
            0.66
    )

    fun update() {
        velocity += acceleration
        position += velocity

        acceleration = Vector2.ZERO
    }

    fun display() {
        drawer.stroke = ColorRGBa.TRANSPARENT
        drawer.fill = color
        val radius = mass * 10.0
        drawer.circle(position, radius)
    }

    fun applyForce(force: Vector2) {
        acceleration += (force / mass)
    }
}