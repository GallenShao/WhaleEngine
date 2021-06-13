package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.WhaleEngine.Object2D
import io.github.gallenshao.WhaleEngine.parameter.CombinedParameter

class Object2DInstance(val object2D : Object2D) {

    var position : Point = Point()  // origin at BottomLeft

    fun genVertices(canvasSize : Size) : CombinedParameter.CombinedItem {
        val rt = (position + (object2D.size / 2.0f)) / canvasSize * 2f - 1f
        val rb = (position + (Size(object2D.size.w, -object2D.size.h) / 2.0f)) / canvasSize * 2f - 1f
        val lb = (position - (object2D.size / 2.0f)) / canvasSize * 2f - 1f
        val lt = (position - (Size(object2D.size.w, -object2D.size.h) / 2.0f)) / canvasSize * 2f - 1f

        return CombinedParameter.CombinedItem(
            floatArrayOf(
                rt.x, rt.y, 0.0f,
                rb.x, rb.y, 0.0f,
                lb.x, lb.y, 0.0f,
                lt.x, lt.y, 0.0f
            ), floatArrayOf(
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 0.0f,
                0.0f, 1.0f
            ), intArrayOf(
                0, 1, 2,
                0, 2, 3
            ), object2D.type
        )
    }

}