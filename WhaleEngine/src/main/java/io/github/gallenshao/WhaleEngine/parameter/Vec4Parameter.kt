package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program
import io.github.gallenshao.WhaleEngine.utils.GLCommand

class Vec4Parameter(key: String) : UniformParameter(key) {

    val values = FloatArray(4)

    fun updateValue(x: Float, y: Float, z: Float, w: Float) {
        values[0] = x
        values[1] = y
        values[2] = z
        values[3] = w
    }

    override fun bind(program: Program)  {
        GLCommand.glUniform4f(program.uniformLocation(key), values[0], values[1], values[2], values[3])
    }

    override fun unbind(program: Program) {

    }

}