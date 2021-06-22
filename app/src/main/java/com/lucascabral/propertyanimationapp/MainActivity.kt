package com.lucascabral.propertyanimationapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lucascabral.propertyanimationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenersToClick()
    }

    private fun listenersToClick() {
        binding.apply {
            rotateButton.setOnClickListener {
                rotateStar()
            }
            translateButton.setOnClickListener {
                translateStar()
            }
            scaleButton.setOnClickListener {
                scaleStar()
            }
            fadeButton.setOnClickListener {
                faderStar()
            }
            colorizeButton.setOnClickListener {
                colorizeBackground()
            }
            showerButton.setOnClickListener {
                shower()
            }
        }
    }

    private fun rotateStar() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ROTATION, -360f, 0f)
        animator.duration = 1200
        animator.disableViewDuringAnimation(binding.rotateButton)
        animator.start()
    }

    private fun translateStar() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.TRANSLATION_X, 300f)
        animator.duration = 800
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.translateButton)
        animator.start()
    }

    private fun scaleStar() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 6f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 6f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(binding.star, scaleX, scaleY)
        animator.duration = 500
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.scaleButton)
        animator.start()
    }

    private fun faderStar() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.fadeButton)
        animator.start()
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun colorizeBackground() {
        val animator = ObjectAnimator.ofArgb(binding.star.parent,
            "backgroundColor", Color.BLACK, Color.RED)
        animator.duration = 500
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.colorizeButton)
        animator.start()
    }

    private fun shower() {

    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
}