package io.github.gallenshao.WhaleEngine

import io.github.gallenshao.WhaleEngine.parameter.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import android.opengl.GLES30.*
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG

class Renderer(
    val vertexShader: String = SIMPLE_VERTEX_SHADER,
    val fragmentShader: String = SIMPLE_FRAGMENT_SHADER
) {

    val parameters: HashMap<String, Parameter> = HashMap()
    var input: ArrayList<Texture> = ArrayList()
    var output: RenderTarget? = null

    var drawMode: DrawMode = DrawMode.WE_TRIANGLES

    val VERTEX_PARAMETER_KEY = "VERTEX_PARAMETER_KEY"
    val INDEX_PARAMETER_KEY = "INDEX_PARAMETER_KEY"

    var elementSize = 0

    init {
        setVertices(SIMPLE_VERTEX, Layout.getSimpleLayout(0))
        setIndices(SIMPLE_INDEX)
    }

    val program by lazy {
        Program.fromString(vertexShader, fragmentShader)
    }

    fun release() {
        parameters.forEach { (_, parameter) ->
            parameter.release()
        }
        parameters.clear()
        program.release()
    }

    fun setVec4Parameter(key: String, x: Float = 0.0f, y: Float = 0.0f, z: Float = 0.0f, w: Float = 0.0f) {
        val parameter = parameters.getOrPut(key, {
            Vec4Parameter(key)
        }) as Vec4Parameter
        parameter.updateValue(x, y, z, w)
    }

    fun setVertices(vertices : FloatArray, layout: Layout) {
        val parameter = parameters.getOrPut(VERTEX_PARAMETER_KEY, {
            VertexParameter(vertices, layout)
        }) as VertexParameter
        parameter.updateValue(vertices, layout)
    }

    fun setIndices(indices : IntArray) {
        val parameter = parameters.getOrPut(INDEX_PARAMETER_KEY, {
            IndexParameter(indices)
        }) as IndexParameter
        parameter.updateValue(indices)
        elementSize = indices.size
    }

    fun setCombinedParameter(combinedParameter: CombinedParameter) {
        setVertices(combinedParameter.vertices_.toFloatArray(), combinedParameter.layout)
        setIndices(combinedParameter.indices_.toIntArray())
    }

    private fun bindParameters() {
        for ((key, parameter) in parameters) {
            LogUtils.d(TAG, "bind ${key}")
            parameter.bind(program)
        }
    }

    private fun unbindParameters() {
        for ((_, parameter) in parameters) {
            parameter.unbind(program)
        }
    }

    private fun performRender() {
        val mode = when(drawMode) {
            DrawMode.WE_TRIANGLES -> GL_TRIANGLES
            DrawMode.WE_LINES -> GL_LINES
        }
        GLCommand.glDrawElements(mode, elementSize, GL_UNSIGNED_INT, 0)
    }

    private fun bindRenderState() {
        GLCommand.glEnable(GL_BLEND)
        GLCommand.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    fun render() {
        program.active()
        bindRenderState()
        bindParameters()
        performRender()
        unbindParameters()
        program.inactive()
    }

    companion object {
        val SIMPLE_VERTEX_SHADER = """
            attribute vec4 a_Position;
            void main() {
                gl_Position = a_Position;
            }
        """.trimIndent()

        val SIMPLE_FRAGMENT_SHADER = """
            precision mediump float;
            uniform vec4 u_Color;
            void main() {
                gl_FragColor = u_Color;
            }
        """.trimIndent()

        val SIMPLE_VERTEX = floatArrayOf(
            -1.0f, -1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f
        )

        val SIMPLE_INDEX = intArrayOf(
            0, 1, 2, 1, 2, 3
        )
    }

}