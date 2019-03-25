package com.phantomvk.vkit.util

import android.content.Context
import android.widget.Toast

fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dip(value: Float): Float = value * resources.displayMetrics.density

fun Context.screenWidth() = resources.displayMetrics.widthPixels

fun Context.screenHeight() = resources.displayMetrics.heightPixels

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
