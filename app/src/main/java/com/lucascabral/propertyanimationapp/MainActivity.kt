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
                translater()
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
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                binding.rotateButton.isEnabled = false
            }
            override fun onAnimationEnd(animation: Animator?) {
                binding.rotateButton.isEnabled = true
            }
        })
        animator.start()
    }

    private fun translater() {

    }

    private fun scaler() {

    }

    private fun fader() {

    }

    private fun colorizer() {

    }

    private fun shower() {

    }
}