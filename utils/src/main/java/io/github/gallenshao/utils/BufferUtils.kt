package io.github.gallenshao.utils

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

object BufferUtils {

    fun getBuffer(id : Int) : IntBuffer {
        val buffer = IntBuffer.allocate(1)
        buffer.put(id)
        buffer.position(0)
        return buffer
    }

    fun getBuffer(array : FloatArray) : FloatBuffer {
        val size = array.size * java.lang.Float.SIZE / java.lang.Byte.SIZE
        val floatBuffer = ByteBuffer.allocateDirect(size)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        floatBuffer.put(array)
        floatBuffer.position(0)
        return floatBuffer
    }

    fun getBuffer(array : IntArray) : IntBuffer {
        val size = array.size * java.lang.Integer.SIZE / java.lang.Byte.SIZE
        val intBuffer = ByteBuffer.allocateDirect(size)
            .order(ByteOrder.nativeOrder())
            .asIntBuffer()
        intBuffer.put(array)
        intBuffer.position(0)
        return intBuffer
    }
}