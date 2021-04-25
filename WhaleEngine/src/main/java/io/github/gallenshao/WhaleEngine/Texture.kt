package io.github.gallenshao.WhaleEngine

import io.github.gallenshao.WhaleEngine.utils.GLCommand
import java.nio.IntBuffer

class Texture() {

    val id by lazy {
        0
    }

    companion object {

        fun create(): Int {
            val buffer = IntBuffer.allocate(1)
            GLCommand.glGenTextures(1, buffer)
            return buffer[0]
        }

    }

}