package ex_06_customProbability

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.uniform
import org.openrndr.math.Vector2

class Walker(program: Program) {
    private val drawer = program.drawer
    private var currentPosition = drawer.bounds.dimensions / 2.0
    private var previousPosition = currentPosition
    private var counter = 0

    fun update() {
        // Sometimes have chance of huge jumps
        val stepSize = if (counter % 100 == 0) 125.0 else 3.5
        counter += 1

        previousPosition = currentPosition
        currentPosition += Vector2.uniform(-stepSize, stepSize)
    }

    fun draw() {
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(previousPosition, currentPosition)
    }
}