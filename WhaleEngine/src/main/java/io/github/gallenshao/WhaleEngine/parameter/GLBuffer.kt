package io.github.gallenshao.WhaleEngine.parameter

import android.opengl.GLES30.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.BufferUtils
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.Buffer
import java.nio.IntBuffer

open class GLBuffer(
    bufferDelegate: Lazy<Buffer>,
    private val item_size: Int,
    private val type: Int
) {

    private val nativeBuffer by bufferDelegate

    val idDelegate = lazy {
        val buffer = IntBuffer.allocate(1)
        GLCommand.glGenBuffers(1, buffer)
        val vbo = buffer[0]

        GLCommand.glBindBuffer(type, vbo)
        GLCommand.glBufferData(type, nativeBuffer.capacity() * item_size, nativeBuffer, GL_DYNAMIC_DRAW)

        vbo
    }
    val id by idDelegate

    open fun active() {
        GLCommand.glBindBuffer(type, id)
    }

    fun inactive() {
        GLCommand.glBindBuffer(type, 0)
    }

    fun updateValue(buffer: Buffer) {
        val size = buffer.capacity() * item_size
        GLCommand.glBindBuffer(type, id)
        GLCommand.glBufferData(type, size, buffer, GL_DYNAMIC_DRAW)
//        GLCommand.glBufferSubData(type, 0, size, buffer)
    }

    fun release() {
        GLCommand.glDeleteBuffers(1, BufferUtils.getBuffer(id))
    }

}