package io.github.gallenshao.WhaleEngine.parameter

import io.github.gallenshao.utils.LogUtils

class CombinedParameter {

    data class CombinedItem(val vertex : FloatArray, val texCoord : FloatArray, val index : IntArray, val type : Int)

    val vertices_ : ArrayList<Float> = ArrayList()
    val indices_ : ArrayList<Int> = ArrayList()

    val layout by lazy {
        Layout().item("a_Position", 3)
                .item("a_itemType", 1)
                .item("a_texCoord", 2)
    }

    fun add(item : CombinedItem) {
        add(item.vertex, item.texCoord, item.index, item.type)
    }

    fun add(canvasCoord : FloatArray, texCoord : FloatArray, index : IntArray, type : Int) {
        val originSize = vertices_.size / 6
        for (i in 0 until canvasCoord.size / 3) {
            vertices_.add(canvasCoord[i * 3])
            vertices_.add(canvasCoord[i * 3 + 1])
            vertices_.add(canvasCoord[i * 3 + 2])
            vertices_.add(type.toFloat())
            vertices_.add(texCoord[i * 2])
            vertices_.add(texCoord[i * 2 + 1])
        }

        indices_.addAll(index.map {
            originIndex -> originIndex + originSize
        })
    }

}