package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program
import android.opengl.GLES30.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class VertexParameter(var vertices_: FloatArray, val layout: Layout) : Parameter() {

    val vertexBuffer = VertexBuffer(vertices_)

    override fun bind(program: Program) {
        vertexBuffer.active()
        for (item in layout.layoutItems) {
            val location = program.attributeLocation(item.name)
            GLCommand.glVertexAttribPointer(location,
                    item.size,
                    GL_FLOAT,
                    false,
                    layout.totalSize * java.lang.Float.SIZE / java.lang.Byte.SIZE,
                    item.offset)
            GLCommand.glEnableVertexAttribArray(location)
        }
    }

    override fun unbind(program: Program) {
        for (item in layout.layoutItems) {
            GLCommand.glDisableVertexAttribArray(program.attributeLocation(item.name))
        }
        vertexBuffer.inactive()
    }

    override fun release() {
        vertexBuffer.release()
    }

    fun updateValue(vertices: FloatArray) {
        vertices_ = vertices
        vertexBuffer.updateValue(vertices)
    }

}