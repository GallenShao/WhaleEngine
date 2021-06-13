package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class Scene(val size : Size) {

    val renderBatches : ArrayList<RenderBatch> = ArrayList()

    init {
        renderBatches.add(RenderBatch(size))
    }

    fun addObject(instance : Object2DInstance) {
        val renderBatch = renderBatches.last()
        if (renderBatch.addObject(instance)) {
            return
        }
        val newRenderBatch = RenderBatch(size)
        if (!newRenderBatch.addObject(instance)) {
            LogUtils.e(TAG, "cound not add object to scene!")
            return
        }
        renderBatches.add(newRenderBatch)
    }

    fun compile() {
        renderBatches.forEach { batch -> batch.compile() }
    }

    fun render() {
        renderBatches.forEach { batch -> batch.render() }
    }

    fun updateSize(size : Size) {
        renderBatches.forEach { batch -> batch.size = size }
    }

}