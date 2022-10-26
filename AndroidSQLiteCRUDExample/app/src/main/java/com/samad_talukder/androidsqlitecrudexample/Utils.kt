package com.samad_talukder.androidsqlitecrudexample

import android.graphics.Color
import kotlin.random.Random

object Utils {

    fun generateRandomColor(): String {
        val baseRed = Color.red(-1)
        val baseGreen = Color.green(-1)
        val baseBlue = Color.blue(-1)
        val random = Random(System.currentTimeMillis())

        val red: Int = (random.nextInt(256) + baseRed) / 2
        val green: Int = (random.nextInt(256) + baseGreen) / 2
        val blue: Int = (random.nextInt(256) + baseBlue) / 2

        return "#" + red.toString(16) + green.toString(16) + blue.toString(16)

    }

    fun randomColor(): Int {
        return (Math.random() * 16777215).toInt() or (0xFF shl 24)
    }

    fun getRandomColor(): Int {
        val random = java.util.Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}