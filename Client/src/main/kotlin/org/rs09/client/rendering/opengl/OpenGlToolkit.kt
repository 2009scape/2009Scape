package org.rs09.client.rendering.opengl

import com.jogamp.opengl.GL
import org.rs09.client.rendering.Toolkit
import org.runite.client.HDToolKit

class OpenGlToolkit: Toolkit() {

    override fun fillRect(x: Int, y: Int, w: Int, h: Int, rgb: Int, alpha: Int) {
        HDToolKit.method1835()
        val var6 = x.toFloat()
        val var7 = var6 + w.toFloat()
        val var8 = (HDToolKit.viewHeight - y).toFloat()
        val var9 = var8 - h.toFloat()
        val var10 = HDToolKit.gl

        var10.glBegin(GL.GL_TRIANGLE_FAN)
        var10.glColor4ub((rgb shr 16).toByte(), (rgb shr 8).toByte(), rgb.toByte(), if (alpha > 255) -1 else alpha.toByte())
        var10.glVertex2f(var6, var8)
        var10.glVertex2f(var6, var9)
        var10.glVertex2f(var7, var9)
        var10.glVertex2f(var7, var8)
        var10.glEnd()
    }

    override fun drawHorizontalLine(x: Int, y: Int, w: Int, rgb: Int) {
        HDToolKit.method1835()
        val startX = x.toFloat() + 0.3f
        val endX = startX + w.toFloat()
        val yPos = HDToolKit.viewHeight.toFloat() - (y.toFloat() + 0.3f)
        val gl = HDToolKit.gl

        gl.glBegin(GL.GL_LINES)
        gl.glColor3ub((rgb shr 16).toByte(), (rgb shr 8).toByte(), rgb.toByte())
        gl.glVertex2f(startX, yPos)
        gl.glVertex2f(endX, yPos)
        gl.glEnd()
    }

    override fun drawVerticalLine(x: Int, y: Int, h: Int, rgb: Int) {
        HDToolKit.method1835()
        val var4 = x.toFloat() + .3f
        val var5 = HDToolKit.viewHeight.toFloat() - (y.toFloat() + 0.3f)
        val var6 = var5 - h.toFloat()
        val var7 = HDToolKit.gl

        var7.glBegin(GL.GL_LINES)
        var7.glColor3ub((rgb shr 16).toByte(), (rgb shr 8).toByte(), rgb.toByte())
        var7.glVertex2f(var4, var5)
        var7.glVertex2f(var4, var6)
        var7.glEnd()
    }

    override fun drawRect(x: Int, y: Int, w: Int, h: Int, rgb: Int, alpha: Int) {
        HDToolKit.method1835()
        val var5 = x.toFloat() + 0.3f
        val var6 = var5 + (w.toFloat() - 1)
        val var7 = (HDToolKit.viewHeight.toFloat() - (y.toFloat() + 0.3f))
        val var8 = var7 - (h.toFloat() - 1)
        val var9 = HDToolKit.gl

        var9.glBegin(GL.GL_LINE_LOOP)
        var9.glColor4ub((rgb shr 16).toByte(), (rgb shr 8).toByte(), rgb.toByte(), if (alpha > 255) -1 else alpha.toByte())
        var9.glVertex2f(var5, var7)
        var9.glVertex2f(var5, var8)
        var9.glVertex2f(var6, var8)
        var9.glVertex2f(var6, var7)
        var9.glEnd()
    }

    override fun method934(x: Int, y: Int, w: Int, h: Int, rgb: Int) {
        HDToolKit.method1835()
        val var5 = x.toFloat()
        val var6 = var5 + w.toFloat()
        val var7 = (HDToolKit.viewHeight - y).toFloat()
        val var8 = var7 - h.toFloat()
        val var9 = HDToolKit.gl

        var9.glBegin(GL.GL_TRIANGLE_FAN)
        var9.glColor3ub((rgb shr 16).toByte(), (rgb shr 8).toByte(), rgb.toByte())
        var9.glVertex2f(var5, var7)
        var9.glVertex2f(var5, var8)
        var9.glVertex2f(var6, var8)
        var9.glVertex2f(var6, var7)
        var9.glEnd()
    }
}