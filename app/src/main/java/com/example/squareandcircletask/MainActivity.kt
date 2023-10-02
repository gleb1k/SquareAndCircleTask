package com.example.squareandcircletask

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circle: View = findViewById(R.id.circle)
        val square: View = findViewById(R.id.square)

        val root: View = findViewById(R.id.container)

        root.doOnLayout {
            square.layoutParams.height = root.width / 5
            square.layoutParams.width = root.width / 5
            square.requestLayout()

            circle.layoutParams.height = root.width / 4
            circle.layoutParams.width = root.width / 4
            circle.requestLayout()

            root.doOnNextLayout {
                startAnimate(circle, square, root)
            }
        }
    }

    private fun startAnimate(circle: View, square: View, root: View) {
        val interpolator = LinearInterpolator()

        square.animate()
            .setDuration(TimeUnit.SECONDS.toMillis(15))
            .translationY((-root.height + square.height).toFloat())
            .setInterpolator(interpolator)
            .start()

        circle.animate()
            .setDuration(TimeUnit.SECONDS.toMillis(15))
            .translationX((root.width - circle.width).toFloat())
            .setInterpolator(interpolator)
            .start()
    }
}