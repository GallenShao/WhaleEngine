package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.WhaleEngine.Object2D

class RectObject2D : Object2D() {

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