package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.WhaleEngine.Program

abstract class Parameter() {

    abstract fun bind(program: Program)

    abstract fun unbind(program: Program)

    abstract fun release()

}