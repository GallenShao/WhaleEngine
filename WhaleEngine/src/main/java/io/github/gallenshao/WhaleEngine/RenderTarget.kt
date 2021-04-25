package io.github.gallenshao.WhaleEngine

import android.opengl.GLES30.*
import java.nio.IntBuffer

class RenderTarget(
    val framebufferId: Int = 0,
    val textureId: Int = 0,
    var width: Int = 0,
    var height: Int = 0
) {

    fun active() {
        glBindFramebuffer(GL_FRAMEBUFFER, framebufferId)
    }

    fun BindTexture(texture : Int) {
        active()

    }

    fun release() {
        val buffer = IntBuffer.allocate(1)
        if (framebufferId != 0) {
            buffer.position(0)
            buffer.put(framebufferId)
            glDeleteFramebuffers(1, buffer)
        }

        if (textureId != 0) {
            buffer.position(0)
            buffer.put(textureId)
            glDeleteTextures(1, buffer)
        }
    }

    companion object {

        val TAG = RenderTarget::class.java.simpleName

        fun create(w : Int, h : Int) : RenderTarget {
            return RenderTarget()
        }

    }

}