package io.github.gallenshao.WhaleEngine.parameter

class Layout {

    class LayoutItem(val name: String, val size: Int, val offset: Int)

    val layoutItems : ArrayList<LayoutItem> = ArrayList()
    var totalSize = 0

    fun item(name: String, size: Int) : Layout {
        layoutItems.add(LayoutItem(name, size, totalSize))
        totalSize += size
        return this
    }

    companion object {

        fun getSimpleLayout(texCounts: Int = 0): Layout {
            var layout = Layout().item("a_Position", 3)
            for (index in 0 until texCounts) {
                layout = layout.item("v_texCoord${index}", 2)
            }
            return layout
        }

    }

}