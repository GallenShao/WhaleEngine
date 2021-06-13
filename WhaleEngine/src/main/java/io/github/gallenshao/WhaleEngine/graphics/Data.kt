package io.github.gallenshao.WhaleEngine.graphics

data class Size(val w : Float = 0.0f, val h : Float = 0.0f)

operator fun Size.plus(t : Float) = Size(w + t, h + t)
operator fun Size.plus(t : Size) = Size(w + t.w, h + t.h)

operator fun Size.minus(t : Float) = Size(w - t, h - t)
operator fun Size.minus(t : Size) = Size(w - t.w, h - t.h)

operator fun Size.times(t : Float) = Size(w * t, h * t)

operator fun Size.div(t : Float) = Size(w / t, h / t)

data class Point(val x : Float = 0.0f, val y : Float = 0.0f)

operator fun Point.unaryMinus() = Point(-x, -y)

operator fun Point.plus(t : Float) = Point(x + t, y + t)
operator fun Point.plus(t : Size) = Point(x + t.w, y + t.h)

operator fun Point.minus(t : Float) = Point(x - t, y - t)
operator fun Point.minus(t : Size) = Point(x - t.w, y - t.h)

operator fun Point.times(t : Float) = Point(x * t, y * t)
operator fun Point.times(t : Size) = Point(x * t.w, y * t.h)

operator fun Point.div(t : Float) = Point(x / t, y / t)
operator fun Point.div(t : Size) = Point(x / t.w, y / t.h)

data class Vec4(val r : Float, val g : Float, val b : Float, val a : Float)