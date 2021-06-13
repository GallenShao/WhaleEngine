package io.github.gallenshao.WhaleEngineExample

import android.opengl.GLES30.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import io.github.gallenshao.WhaleEngine.graphics.*

class MyGLRenderer : GLSurfaceView.Renderer {

    var canvasSize : Size = Size(0.0f, 0.0f)
    val scene_delegate = lazy {
        Scene(canvasSize, RectObject2D(canvasSize, Vec4(1.0f, 1.0f, 0.0f, 1.0f)))
    }
    val scene by scene_delegate

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        val rect = RectObject2D()
        rect.color = Vec4(1.0f, 0.0f, 0.0f, 1.0f)
        rect.size = Size(100.0f, 100.0f)

        val rect_instance1 = rect.genInstance()
        rect_instance1.position = Point(50.0f, 50.0f)

        val rect_instance2 = rect.genInstance()
        rect_instance2.position = Point(300.0f, 300.0f)

        val circle = CircleObject2D()
        circle.color = Vec4(0.0f, 1.0f, 0.0f, 1.0f)
        circle.size = Size(100.0f, 200.0f)

        val circle_instance1 = circle.genInstance()
        circle_instance1.position = Point(500.0f, 500.0f)

        scene.addInstance(rect_instance1)
        scene.addInstance(rect_instance2)
        scene.addInstance(circle_instance1)

        scene.compile()
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height);
        canvasSize = Size(width.toFloat(), height.toFloat())
        if (scene_delegate.isInitialized()) {
            scene.updateSize(canvasSize)
        }
    }

    override fun onDrawFrame(p0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT);
        scene.render()
    }

}