package io.github.gallenshao.WhaleEngine

import io.github.gallenshao.WhaleEngine.graphics.*

abstract class Object2D {

    var size : Size = Size(1.0f, 1.0f)
    var color : Vec4 = Vec4(1.0f, 1.0f, 1.0f, 1.0f)
    val type : Int = type_family++

    fun genInstance() : Object2DInstance {
        return Object2DInstance(this)
    }

    open fun getUniformCounts() : Int {
        return 2
    }

    open fun genUniform(): String {
        return """
            uniform vec4 u_Object2D_Color_${type};
            uniform vec4 u_Object2D_Size_${type};
        """.trimIndent()
    }

    open fun updateUniform(renderer: Renderer) {
        renderer.setVec4Parameter("u_Object2D_Color_${type}", color.r, color.g, color.b, color.a)
        renderer.setVec4Parameter("u_Object2D_Size_${type}", size.w, size.h)
    }

    /**
     * generated fragment shader function body
     *   function parameters: (vec2 pos), which is the texcoord of the draw area
     */
    abstract fun genShader() : String

    companion object {
        var type_family = 0
    }

}