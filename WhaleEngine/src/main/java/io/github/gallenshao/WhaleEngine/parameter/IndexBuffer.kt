package io.github.gallenshao.WhaleEngine.parameter

import android.opengl.GLES30.*
import io.github.gallenshao.utils.BufferUtils
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.Buffer

class IndexBuffer(
    var indices_: IntArray,
    private val delegate_: Lazy<Buffer> = lazy {
        BufferUtils.getBuffer(indices_)
    }
) : GLBuffer(
    delegate_,
    java.lang.Integer.SIZE / java.lang.Byte.SIZE,
    GL_ELEMENT_ARRAY_BUFFER
) {

    fun updateValue(indices: IntArray) {
        if (delegate_.isInitialized()) {
            updateValue(BufferUtils.getBuffer(indices))
        } else {
            LogUtils.i(TAG, "index buffer not initialize, simply replace it")
        }
        indices_ = indices
    }
}