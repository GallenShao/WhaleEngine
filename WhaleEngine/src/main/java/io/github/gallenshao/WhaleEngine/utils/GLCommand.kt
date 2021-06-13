package io.github.gallenshao.WhaleEngine.utils

import android.opengl.GLES30
import io.github.gallenshao.utils.LogUtils
import io.github.gallenshao.utils.TAG
import java.nio.Buffer
import java.nio.IntBuffer

const val GL_DEBUG = true

object GLCommand {

    fun checkError(tag: String) {
        if (GL_DEBUG) {
            LogUtils.i(TAG, "calling - $tag")
        }
        val error = GLES30.glGetError()
        val msg = getStr(error)
        if (error != GLES30.GL_NO_ERROR) {
            LogUtils.e(TAG, "glGetError - ${tag}: ${msg}")
        }
    }

    fun getStr(enum: Int): String {
        return when (enum) {
            GLES30.GL_INVALID_ENUM -> "GL_INVALID_ENUM"
            GLES30.GL_INVALID_VALUE -> "GL_INVALID_VALUE"
            GLES30.GL_INVALID_OPERATION -> "GL_INVALID_OPERATION"
            GLES30.GL_INVALID_FRAMEBUFFER_OPERATION -> "GL_INVALID_FRAMEBUFFER_OPERATION"
            GLES30.GL_OUT_OF_MEMORY -> "GL_OUT_OF_MEMORY"
            GLES30.GL_FRAGMENT_SHADER -> "GL_FRAGMENT_SHADER"
            GLES30.GL_VERTEX_SHADER -> "GL_VERTEX_SHADER"
            GLES30.GL_ELEMENT_ARRAY_BUFFER -> "GL_ELEMENT_ARRAY_BUFFER"
            GLES30.GL_ARRAY_BUFFER -> "GL_ARRAY_BUFFER"
            GLES30.GL_TRIANGLES -> "GL_TRIANGLES"
            GLES30.GL_LINES -> "GL_LINES"
            GLES30.GL_FLOAT -> "GL_FLOAT"
            GLES30.GL_INT -> "GL_INT"
            GLES30.GL_UNSIGNED_INT -> "GL_UNSIGNED_INT"
            GLES30.GL_FRAMEBUFFER -> "GL_FRAMEBUFFER"
            else -> ""
        }
    }

    fun glCreateProgram(): Int {
        val ret = GLES30.glCreateProgram()
        checkError(object {}.javaClass.enclosingMethod!!.name + "-> ${ret}")
        return ret
    }

    fun glUseProgram(id: Int) {
        GLES30.glUseProgram(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glDeleteProgram(id: Int) {
        GLES30.glDeleteProgram(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glLinkProgram(id: Int) {
        GLES30.glLinkProgram(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glGetProgramInfoLog(id: Int): String {
        val ret = GLES30.glGetProgramInfoLog(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
        return ret
    }

    fun glGetProgramiv(id: Int, type: Int, array: IntArray, pos: Int) {
        GLES30.glGetProgramiv(id, type, array, pos)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glAttachShader(program: Int, shader: Int) {
        GLES30.glAttachShader(program, shader)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${program}, ${shader})")
    }

    fun glCreateShader(type: Int): Int {
        val ret = GLES30.glCreateShader(type)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(type)}) -> ${ret}")
        return ret
    }

    fun glShaderSource(id: Int, source: String) {
        GLES30.glShaderSource(id, source)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glCompileShader(id: Int) {
        GLES30.glCompileShader(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glDeleteShader(id: Int) {
        GLES30.glDeleteShader(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
    }

    fun glGetShaderInfoLog(id: Int): String {
        val ret = GLES30.glGetShaderInfoLog(id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id})")
        return ret
    }

    fun glGetShaderiv(id: Int, type: Int, array: IntArray, pos: Int) {
        GLES30.glGetShaderiv(id, type, array, pos)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glGenBuffers(count: Int, buffer: IntBuffer) {
        GLES30.glGenBuffers(count, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glBindBuffer(type: Int, id: Int) {
        GLES30.glBindBuffer(type, id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(type)}, ${id})")
    }

    fun glBufferData(type: Int, size: Int, buffer: Buffer, mode: Int) {
        GLES30.glBufferData(type, size, buffer, mode)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(type)}, ${size}, ...)")
    }

    fun glBufferSubData(type: Int, pos: Int, size: Int, buffer: Buffer) {
        GLES30.glBufferSubData(type, pos, size, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(type)}, ${pos}, ${size}, ...)")
    }

    fun glDeleteBuffers(count: Int, buffer: IntBuffer) {
        GLES30.glDeleteBuffers(count, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glGetUniformLocation(id: Int, key: String): Int {
        val ret = GLES30.glGetUniformLocation(id, key)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id}, ${key}) -> ${ret}")
        return ret
    }

    fun glGetAttribLocation(id: Int, key: String): Int {
        val ret = GLES30.glGetAttribLocation(id, key)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${id}, ${key}) -> ${ret}")
        return ret
    }

    fun glUniform4f(location: Int, x: Float, y: Float, z: Float, w: Float) {
        GLES30.glUniform4f(location, x, y, z, w)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${location}, ${x}, ${y}, ${z}, ${w})")
    }

    fun glVertexAttribPointer(location: Int, size: Int, item_type: Int, normalized: Boolean, totalSize: Int, offset: Int) {
        GLES30.glVertexAttribPointer(location, size, item_type, normalized, totalSize, offset)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${location}, ${size}, ${getStr(item_type)}, ${normalized}, ${totalSize}, ${offset})")
    }

    fun glEnableVertexAttribArray(location: Int) {
        GLES30.glEnableVertexAttribArray(location)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${location})")
    }

    fun glDisableVertexAttribArray(location: Int) {
        GLES30.glDisableVertexAttribArray(location)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${location})")
    }

    fun glGenFramebuffers(count: Int, buffer: IntBuffer) {
        GLES30.glGenFramebuffers(count, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glBindFramebuffer(type: Int, id: Int) {
        GLES30.glBindFramebuffer(type, id)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(type)}, ${id})")
    }

    fun glDeleteFramebuffers(count: Int, buffer: IntBuffer) {
        GLES30.glDeleteFramebuffers(count, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glFramebufferTexture2D(target: Int, attachment: Int, textureTarget: Int, texture: Int, level: Int) {
        GLES30.glFramebufferTexture2D(target, attachment, textureTarget, texture, level)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glDrawElements(mode: Int, size: Int, type: Int, location: Int) {
        GLES30.glDrawElements(mode, size, type, location)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${getStr(mode)}, ${size}, ${getStr(type)}, ${location})")
    }

    fun glGenTextures(count: Int, buffer: IntBuffer) {
        GLES30.glGenTextures(count, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name)
    }

    fun glGetIntegerv(key : Int, buffer: IntBuffer) {
        GLES30.glGetIntegerv(key, buffer)
        checkError(object {}.javaClass.enclosingMethod!!.name + "(${key})")
    }

}