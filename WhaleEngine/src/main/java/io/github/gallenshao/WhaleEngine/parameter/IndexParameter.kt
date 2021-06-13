package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class IndexParameter(var indices_ : IntArray) : Parameter() {

    val indexBuffer = IndexBuffer(indices_)

    override fun bind(program: Program) {
        indexBuffer.active()

        // Debug
        if (LogUtils.minLogLevel >= LogUtils.DEBUG) {
            var msg = "indices: \n"
            for (i in 0 until indices_.size) {
                msg += "${indices_[i]}, "
                if (i % 3 == 2) {
                    msg += "\n"
                }
            }
            LogUtils.d(TAG, msg)
        }
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