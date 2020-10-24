package chp_00_intro.ex_07_perlinWalker

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.random
import org.openrndr.extra.noise.simplex
import org.openrndr.math.Vector2

class Walker(program: Program) {
    private val drawer = program.drawer
    private var currentPosition = drawer.bounds.dimensions / 2.0
    private var previousPosition = currentPosition

    private var seed = random(0.0, 100.0).toInt()
    private var noiseX = random(0.0, 10000.0)


    fun update() {
        previousPosition = currentPosition

        noiseX += 0.005
        // Use simplex noise instead of perlin noise
        currentPosition += Vector2.simplex(seed, noiseX)
    }

    fun draw() {
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(previousPosition, currentPosition)
    }
}