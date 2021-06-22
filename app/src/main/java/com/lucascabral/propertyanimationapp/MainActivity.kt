package com.lucascabral.propertyanimationapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
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
                scaler()
            }
            fadeButton.setOnClickListener {
                fader()
            }
            colorizeButton.setOnClickListener {
                colorizer()
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

    private fun scaler() {

    }

    private fun fader() {

    }

    private fun colorizer() {

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