package com.example.christoforos.sandbox.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

//-------------------- Methods --------------------
//...


//-------------------- Extensions --------------------
fun Window.windowVisibleRect(): Rect {
    val ret = Rect()
    this.decorView.getWindowVisibleDisplayFrame(ret)
    return ret
}

fun Context.windowSize(): Point {
    val displayMetrics = this.resources.displayMetrics
    val ret = Point()
    ret.set(displayMetrics.widthPixels, displayMetrics.heightPixels)
    return ret
}

fun View.locationOnScreen(): IntArray {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return location
}

fun View.locationInWindow(): IntArray {
    val location = IntArray(2)
    this.getLocationInWindow(location)
    return location
}

fun Float.toPx(res: Resources): Int {
    val scale = res.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun Int.toDp(res: Resources): Float {
    return this / res.displayMetrics.density
}

fun Resources.intDensity(): Int {
    val density = this.displayMetrics.density
    return when {
        density < 1 -> 1
        density < 1.5 -> 2
        density < 2 -> 3
        density < 3 -> 4
        density < 4 -> 5
        else -> 5
    }
}

fun View.showKeyboard(context: Context, delay: Long = 0) {
    FutureTask {
        this.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }.start(delay)
}

fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
    this.clearFocus()
}

fun Context.isLandscape(): Boolean {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val rotation = windowManager.defaultDisplay.rotation

    return rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270
}

fun Activity.lockOrientation() {
    val display = (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val rotation = display.rotation
    val tempOrientation = this.resources.configuration.orientation
    when (tempOrientation) {
        Configuration.ORIENTATION_LANDSCAPE -> if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_90)
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        Configuration.ORIENTATION_PORTRAIT -> if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_270)
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        else
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
    }
}

fun Activity.unlockOrientation() {
    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}

fun Activity.forcePortrait() {
    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

//---------- getExpectedLineCount ----------

fun TextView.getExpectedLineCount(sizeDp: Int): Int {
    val context = this.context

    val textPaint = TextPaint()
    textPaint.typeface = this.typeface
    textPaint.flags = textPaint.flags or Paint.SUBPIXEL_TEXT_FLAG

    return getLineCount(
            this.text,
            textPaint,
            sizeDp.toFloat(),
            this.measuredWidth,
            context.resources.displayMetrics,
            context
    )
}

private fun getLineCount(text: CharSequence, paint: TextPaint, size: Float, width: Int, displayMetrics: DisplayMetrics, context: Context): Int {
    paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, displayMetrics)
    val layout = StaticLayout(text, paint, width, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0.0f, true)
    return layout.lineCount
}

//----------------------------------------


//---------- View Measured Listener ----------
fun View.onViewMeasureListener(listener: OnViewMeasureListener) {
    if (this.measuredWidth == 0 || this.measuredHeight == 0) {
        val view = this
        this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    view.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
                listener.onViewMeasured(view)
            }
        })
    } else {
        listener.onViewMeasured(this)
    }
}

interface OnViewMeasureListener {
    fun onViewMeasured(view: View)
}
//-------------------------------------------

