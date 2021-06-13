package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.WhaleEngine.Object2D

open class RectObject2D(size : Size = Size(1.0f, 1.0f),
                        color : Vec4 = Vec4(1.0f, 1.0f, 1.0f, 1.0f))
    : Object2D(size, color) {

    /**
     * generated fragment shader function body
     *   function parameters: (vec2 pos), which is the texcoord of the draw area
     */
    override fun genShader() : String {
        return """
            return u_Object2D_Color_${type};
        """.trimIndent()
    }

}