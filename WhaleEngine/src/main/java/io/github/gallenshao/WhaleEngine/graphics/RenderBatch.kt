package io.github.gallenshao.WhaleEngine.graphics

import android.opengl.GLES30
import io.github.gallenshao.WhaleEngine.Object2D
import io.github.gallenshao.WhaleEngine.Renderer
import io.github.gallenshao.WhaleEngine.parameter.CombinedParameter
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.IntBuffer

class RenderBatch(var size : Size) {

    val instances : ArrayList<Object2DInstance> = ArrayList()
    val objects_type : HashSet<Int> = HashSet()
    var uniformCounts = 0

    var renderer : Renderer? = null

    fun addInstance(instance : Object2DInstance) : Boolean {
        if (!objects_type.contains(instance.object2D.type)) {
            if (uniformCounts + instance.object2D.getUniformCounts() > MAX_UNIFORMS) {
                return false
            }
            objects_type.add(instance.object2D.type)
            uniformCounts += instance.object2D.getUniformCounts()
        }
        instances.add(instance)
        return true
    }

    fun compile() {
        renderer?.release()
        renderer = Renderer(SCENE_VERTEX_SHADER, genFragmentShader())
    }

    fun release() {
        renderer?.release()
    }

    fun render() {
        if (renderer == null) {
            return
        }

        val combinedParameter = CombinedParameter()
        for (instance in instances) {
            instance.object2D.updateUniform(renderer!!)
            combinedParameter.add(instance.genVertices(size))
        }

        renderer!!.setCombinedParameter(combinedParameter)
        renderer!!.render()
    }

    private fun genUniforms() : String {
        var uniforms = ""
        val typeSet : HashSet<Int> = HashSet()
        for (instance in instances) {
            if (!typeSet.contains(instance.object2D.type)) {
                uniforms += instance.object2D.genUniform() + "\n"
            }
            typeSet.add(instance.object2D.type)
        }
        return uniforms
    }

    private fun genFunctions() : String {
        var functions = ""
        val typeSet : HashSet<Int> = HashSet()
        for (instance in instances) {
            if (!typeSet.contains(instance.object2D.type)) {
                functions += "vec4 func_object_${instance.object2D.type}(vec2 pos) {\n"
                functions += instance.object2D.genShader()
                functions += "\n}\n"
            }
            typeSet.add(instance.object2D.type)
        }
        return functions
    }

    private fun genCalls() : String {
        var calls = ""
        val typeSet : HashSet<Int> = HashSet()
        var isFirst = true
        for (instance in instances) {
            if (!typeSet.contains(instance.object2D.type)) {
                if (!isFirst) {
                    calls += " else "
                }
                calls += """
                    if (v_itemType > ${instance.object2D.type.toFloat() - 0.5f} && v_itemType < ${instance.object2D.type.toFloat() + 0.5f}) {
                        gl_FragColor = func_object_${instance.object2D.type}(v_texCoord);
                        return;
                    }
                """.trimIndent()
                isFirst = false;
            }
            typeSet.add(instance.object2D.type)
        }
        return calls
    }

    private fun genFragmentShader() : String {
        return """
            precision mediump float;

            varying vec2 v_texCoord;
            varying float v_itemType;
            
            ${genUniforms()}
            
            ${genFunctions()}

            void main() {
                ${genCalls()}
                gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0);
            }
        """.trimIndent()
    }

    companion object {
        val SCENE_VERTEX_SHADER = """
            attribute vec3 a_Position;
            attribute vec2 a_texCoord;
            attribute float a_itemType;
            varying vec2 v_texCoord;
            varying float v_itemType;
            void main() {
                v_itemType = a_itemType;
                v_texCoord = a_texCoord;
                gl_Position = vec4(a_Position, 1.0);
            }
        """.trimIndent()

        val MAX_UNIFORMS by lazy {
            val buffer = IntBuffer.allocate(1)
            GLCommand.glGetIntegerv(GLES30.GL_MAX_FRAGMENT_UNIFORM_VECTORS, buffer)
            LogUtils.w(TAG, "MAX_UNIFORMS: ${buffer[0]}")
            buffer[0]
        }
    }
}