package io.github.gallenshao.WhaleEngine.graphics

import io.github.gallenshao.WhaleEngine.Object2D
import io.github.gallenshao.WhaleEngine.Renderer
import kotlin.math.min

class CircleObject2D : Object2D() {

    fun getRadius() : Float {
        return min(size.w, size.h) / 2
    }

    override fun getUniformCounts(): Int {
        return 1 + super.getUniformCounts()
    }

    override fun genUniform(): String {
        return "uniform vec4 u_CircleObject2D_Radius_${type};\n" + super.genUniform()
    }

    override fun updateUniform(renderer: Renderer) {
        super.updateUniform(renderer)
        renderer.setVec4Parameter("u_CircleObject2D_Radius_${type}", getRadius())
    }

    /**
     * generated fragment shader function body
     *   function parameters: (vec2 pos), which is the texcoord of the draw area
     */
    override fun genShader() : String {
        return """
            float w = (pos.x - 0.5) * u_Object2D_Size_${type}.x;
            float h = (pos.y - 0.5) * u_Object2D_Size_${type}.y;
            float dist = w * w + h * h;
            if (dist < u_CircleObject2D_Radius_${type}.r * u_CircleObject2D_Radius_${type}.r) {
                return u_Object2D_Color_${type};
            } else {
                return vec4(0.0f, 0.0f, 0.0f, 0.0f);
            }
        """.trimIndent()
    }

}