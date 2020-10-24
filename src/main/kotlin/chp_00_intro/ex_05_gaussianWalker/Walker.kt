package chp_00_intro.ex_05_gaussianWalker

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.gaussian
import org.openrndr.extra.noise.random
import org.openrndr.extra.noise.simplex
import org.openrndr.math.Vector2
import org.openrndr.math.clamp

class Walker(program: Program) {
    val drawer = program.drawer
    var currentPosition = drawer.bounds.dimensions / 2.0
    var previousPosition = currentPosition

    val mean = Vector2.ZERO
    val deviation = Vector2.ONE * 7.5

    fun update() {
        previousPosition = currentPosition

        currentPosition += Vector2.gaussian(mean, deviation)
        currentPosition = currentPosition.clamp(Vector2.ZERO, drawer.bounds.dimensions)
    }

    fun draw() {
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(previousPosition, currentPosition)
    }
}