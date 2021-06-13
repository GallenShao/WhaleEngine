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

    val rect by lazy {
        val r = RectObject2D()
        r.color = Vec4(1.0f, 0.0f, 0.0f, 1.0f)
        r.size = Size(100.0f, 100.0f)
        r
    }

    val circle by lazy {
        val c = CircleObject2D()
        c.color = Vec4(0.0f, 1.0f, 0.0f, 1.0f)
        c.size = Size(100.0f, 100.0f)
        c
    }

    var rect_instance1 : Object2DInstance = rect.genInstance()
    var rect_instance2 : Object2DInstance = rect.genInstance()
    var circle_instance1 : Object2DInstance = circle.genInstance()
    var circle_instance2 : Object2DInstance = circle.genInstance()

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        rect_instance1.position = Point(50.0f, 2000.0f)
        rect_instance2.position = Point(500.0f, 2000.0f)
        circle_instance1.position = Point(300.0f, 2000.0f)
        circle_instance2.position = Point(800.0f, 2000.0f)

        scene.addInstance(rect_instance1)
        scene.addInstance(rect_instance2)
        scene.addInstance(circle_instance1)
        scene.addInstance(circle_instance2)

        scene.compile()
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height);
        canvasSize = Size(width.toFloat(), height.toFloat())
        if (scene_delegate.isInitialized()) {
            scene.updateSize(canvasSize)
        }
    }

    fun go(instance : Object2DInstance, speed : Int) {
        instance.position = Point(instance.position.x, instance.position.y - speed)
        if (instance.position.y - instance.object2D.size.h / 2 < 0) {
            instance.position = Point(instance.position.x, instance.object2D.size.h / 2)
        }
    }

    override fun onDrawFrame(p0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
        go(rect_instance1, 5)
        go(rect_instance2, 10)
        go(circle_instance1, 15)
        go(circle_instance2, 20)

        scene.render()
    }

}