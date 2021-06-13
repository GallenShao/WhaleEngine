package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program
import android.opengl.GLES30.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class VertexParameter(var vertices_: FloatArray, var layout_: Layout) : Parameter() {

    val vertexBuffer = VertexBuffer(vertices_)

    override fun bind(program: Program) {
        vertexBuffer.active()
        for (item in layout_.layoutItems) {
            val location = program.attributeLocation(item.name)
            GLCommand.glVertexAttribPointer(location,
                    item.size,
                    GL_FLOAT,
                    false,
                    layout_.totalSize * java.lang.Float.SIZE / java.lang.Byte.SIZE,
                    item.offset * java.lang.Float.SIZE / java.lang.Byte.SIZE)
            GLCommand.glEnableVertexAttribArray(location)
        }

        // Debug
        if (LogUtils.minLogLevel >= LogUtils.DEBUG) {
            var msg = "vertices_: \n"
            for (i in 0 until vertices_.size) {
                msg += "${vertices_[i]}, "
                if (i % layout_.totalSize == (layout_.totalSize - 1)) {
                    msg += "\n"
                }
            }
            LogUtils.d(TAG, msg)
        }
    }

    override fun unbind(program: Program) {
        for (item in layout_.layoutItems) {
            GLCommand.glDisableVertexAttribArray(program.attributeLocation(item.name))
        }
        vertexBuffer.inactive()
    }

    override fun release() {
        vertexBuffer.release()
    }

    fun updateValue(vertices: FloatArray, layout: Layout) {
        vertices_ = vertices
        layout_ = layout
        vertexBuffer.updateValue(vertices)
    }

}