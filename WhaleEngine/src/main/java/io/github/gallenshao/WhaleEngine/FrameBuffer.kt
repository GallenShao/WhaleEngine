package io.github.gallenshao.WhaleEngine

import android.opengl.GLES30.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.BufferUtils
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.IntBuffer

class FrameBuffer(val id : Int = 0) {

    companion object {
        fun create() : FrameBuffer{
            val buffer = IntBuffer.allocate(1)
            GLCommand.glGenFramebuffers(1, buffer)
            if (buffer[0] == 0) {
                LogUtils.e(TAG, "fail to create framebuffer")
            }
            return FrameBuffer(buffer[0])
        }
    }

    fun active() {
        GLCommand.glBindFramebuffer(GL_FRAMEBUFFER, id)
    }

    fun inactive() {
        GLCommand.glBindFramebuffer(GL_FRAMEBUFFER, 0)
    }

    fun release() {
        GLCommand.glDeleteFramebuffers(1, BufferUtils.getBuffer(id))
    }

    fun bindTexture(texture: Texture) {
        active()
        GLCommand.glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture.id, 0)
    }

}