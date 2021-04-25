package io.github.gallenshao.WhaleEngineExample

import android.opengl.GLES30.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import io.github.gallenshao.WhaleEngine.*
import io.github.gallenshao.WhaleEngine.parameter.Layout

class MyGLRenderer : GLSurfaceView.Renderer {

    val TAG = MyGLRenderer::class.java.simpleName

    val VERTEX_SHADER = """
            attribute vec4 a_Position;
            void main() {
                gl_Position = a_Position;
            }
        """.trimIndent()

    val FRAGMENT_SHADER = """
            precision mediump float;
            uniform vec4 u_Color;
            void main() {
                gl_FragColor = u_Color;
            }
        """.trimIndent()

    val renderer = Renderer(VERTEX_SHADER, FRAGMENT_SHADER)

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        renderer.setVec4Parameter("u_Color", 1.0f, 0.0f, 0.0f, 1.0f)
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height);
    }

    override fun onDrawFrame(p0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT);
        renderer.render()
    }

}