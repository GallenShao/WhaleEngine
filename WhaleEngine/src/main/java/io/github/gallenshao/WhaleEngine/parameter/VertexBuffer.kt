package io.github.gallenshao.WhaleEngine.parameter

import android.opengl.GLES30.*
import io.github.gallenshao.utils.BufferUtils
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.Buffer

class VertexBuffer(
    var vertices_: FloatArray,
    private val delegate_: Lazy<Buffer> = lazy {
        BufferUtils.getBuffer(vertices_)
    }
) : GLBuffer(
    delegate_,
    java.lang.Float.SIZE / java.lang.Byte.SIZE,
    GL_ARRAY_BUFFER
) {

    fun updateValue(vertices: FloatArray) {
        if (delegate_.isInitialized()) {
            updateValue(BufferUtils.getBuffer(vertices))
        } else {
            LogUtils.i(TAG, "vertex buffer not initialize, simply replace it")
        }
        vertices_ = vertices
    }

}