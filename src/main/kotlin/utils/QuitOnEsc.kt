package utils

import org.openrndr.Extension
import org.openrndr.KEY_ESCAPE
import org.openrndr.Program

class QuitOnEsc : Extension {
    override var enabled = true

    override fun setup(program: Program) {
        program.keyboard.keyDown.listen {
            if (!it.propagationCancelled) {
                if (it.key == KEY_ESCAPE) {
                    program.application.exit()
                }
            }
        }
    }
}