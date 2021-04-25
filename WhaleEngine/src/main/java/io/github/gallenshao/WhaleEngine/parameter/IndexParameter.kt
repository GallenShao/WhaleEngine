package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program

class IndexParameter(var indices_ : IntArray) : Parameter() {

    val indexBuffer = IndexBuffer(indices_)

    override fun bind(program: Program) {
        indexBuffer.active()
    }

    override fun unbind(program: Program) {
        indexBuffer.inactive()
    }

    override fun release() {
        indexBuffer.release()
    }

    fun updateValue(indices : IntArray) {
        indices_ = indices
        indexBuffer.updateValue(indices)
    }

}