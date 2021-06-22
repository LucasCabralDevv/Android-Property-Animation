package com.lucascabral.propertyanimationapp

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
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
                showerStars()
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
        val animator = ObjectAnimator.ofArgb(
            binding.star.parent,
            "backgroundColor", Color.BLACK, Color.CYAN
        )
        animator.duration = 500
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.colorizeButton)
        animator.start()
    }

    private fun showerStars() {
        val container = binding.star.parent as ViewGroup
        val containerWidth = container.width
        val containerHeight = container.height
        var starWidth: Float = binding.star.width.toFloat()
        var starHeight: Float = binding.star.height.toFloat()

        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star_24)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)
        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX
        starWidth *= newStar.scaleX
        starHeight *= newStar.scaleY
        newStar.translationX = Math.random().toFloat() * containerWidth - starWidth / 2

        val mover = ObjectAnimator
            .ofFloat(newStar, View.TRANSLATION_Y, -starHeight, containerHeight + starHeight)
        mover.interpolator = AccelerateInterpolator(1f)

        val rotator = ObjectAnimator.ofFloat(newStar, View.ROTATION,
            (Math.random() * 1080).toFloat())
        rotator.interpolator = LinearInterpolator()

        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        set.start()
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