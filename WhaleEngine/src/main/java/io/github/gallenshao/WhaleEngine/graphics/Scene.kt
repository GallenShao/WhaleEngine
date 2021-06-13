package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class Scene(var size_ : Size,
            val background : RectObject2D = RectObject2D(size_, Vec4(1.0f, 1.0f, 1.0f, 1.0f))) {

    val backgroundInstance by lazy {
        val instance = background.genInstance()
        instance.position = Point(size_.w / 2, size_.h / 2)
        instance
    }

    val renderBatches : ArrayList<RenderBatch> by lazy {
        val batch = RenderBatch(size_)
        batch.addInstance(backgroundInstance)

        val batches = ArrayList<RenderBatch>()
        batches.add(batch)
        batches
    }

    fun addInstance(instance : Object2DInstance) {
        val renderBatch = renderBatches.last()
        if (renderBatch.addInstance(instance)) {
            return
        }
        val newRenderBatch = RenderBatch(size_)
        if (!newRenderBatch.addInstance(instance)) {
            LogUtils.e(TAG, "cound not add object to scene!")
            return
        }
        renderBatches.add(newRenderBatch)
    }

    fun compile() {
        renderBatches.forEach { batch -> batch.compile() }
    }

    fun release() {
        renderBatches.forEach { batch -> batch.release() }
    }

    fun render() {
        renderBatches.forEach { batch -> batch.render() }
    }

    fun updateSize(size : Size) {
        size_ = size

        renderBatches.forEach { batch -> batch.size = size_ }

        // background
        background.size = size_
        backgroundInstance.position = Point(size_.w / 2, size_.h / 2)
    }

}