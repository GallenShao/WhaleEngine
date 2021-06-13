package io.github.gallenshao.WhaleEngine

import android.opengl.GLES30.*
import io.github.gallenshao.WhaleEngine.utils.GLCommand
import io.github.gallenshao.utils.*

class Program {

    var id: Int = 0

    val locations: HashMap<String, Int> = HashMap()

    fun active() {
        GLCommand.glUseProgram(id)
    }

    fun inactive() {
        GLCommand.glUseProgram(0)
    }

    fun release() {
        GLCommand.glDeleteProgram(id)
        id = 0
        locations.clear()
    }

    internal fun uniformLocation(key: String) : Int {
        return locations.getOrPut(key, {
            GLCommand.glGetUniformLocation(id, key)
        })
    }

    internal fun attributeLocation(key: String) : Int {
        return locations.getOrPut(key, {
            GLCommand.glGetAttribLocation(id, key)
        })
    }

    companion object {

        fun fromString(vertexShader: String, fragmentShader: String) : Program {
            val vertexShaderId = compileVertexShader(vertexShader)
            val fragmentShaderId = compileFragmentShader(fragmentShader)
            val program = Program()
            if (vertexShaderId != 0 && fragmentShaderId != 0) {
                program.id = createProgram(vertexShaderId, fragmentShaderId)
            }
            return program
        }

        private fun compileFragmentShader(shader: String) : Int {
            LogUtils.d(TAG, "compiling fragment shader: \n${shader}")
            return compileShader(GL_FRAGMENT_SHADER, shader)
        }

        private fun compileVertexShader(shader : String) : Int {
            LogUtils.d(TAG, "compiling vertex shader: \n${shader}")
            return compileShader(GL_VERTEX_SHADER, shader)
        }

        private fun compileShader(type: Int, shader: String): Int {
            val shaderId = GLCommand.glCreateShader(type)
            if (shaderId == 0) {
                LogUtils.e(
                    Program.TAG,
                    "fail to create ${if (type == GL_FRAGMENT_SHADER) "framgment" else "vertex"} shader"
                )
                return 0
            }
            LogUtils.i(Program.TAG, "create ${if (type == GL_FRAGMENT_SHADER) "framgment" else "vertex"} shader [${shaderId}]")
            GLCommand.glShaderSource(shaderId, shader)
            GLCommand.glCompileShader(shaderId)

            val shaderInfo = GLCommand.glGetShaderInfoLog(shaderId)

            val compileStatus = IntArray(1)
            GLCommand.glGetShaderiv(shaderId, GL_COMPILE_STATUS, compileStatus, 0)
            if (compileStatus[0] == 0) {
                LogUtils.e(Program.TAG, "fail to compile shader, source:\n${shader}\nError Info:\n $shaderInfo")
                GLCommand.glDeleteShader(shaderId)
                return 0
            }

            return shaderId
        }

        private fun createProgram(vertexShaderId : Int, fragmentShaderId : Int) : Int {
            val programId = GLCommand.glCreateProgram()
            if (programId == 0) {
                LogUtils.e(Program.TAG, "fail to create program")
                return 0
            }
            LogUtils.i(Program.TAG, "create program [${programId}]")

            GLCommand.glAttachShader(programId, vertexShaderId)
            GLCommand.glAttachShader(programId, fragmentShaderId)
            GLCommand.glLinkProgram(programId)

            val programInfo = GLCommand.glGetProgramInfoLog(programId)

            val linkStatus = IntArray(1)
            GLCommand.glGetProgramiv(programId, GL_LINK_STATUS, linkStatus, 0)
            if (linkStatus[0] == 0) {
                LogUtils.e(Program.TAG, "fail to link program[$programId]: $programInfo")
                GLCommand.glDeleteProgram(programId)
                return 0
            }

            return programId
        }

    }

}