package com.example.instagramapp

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_notifications.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var button: Button
    lateinit var imageAnim: ImageView
    var widthSquare = 0
    var heightSquare = 0
    var squareBig = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_notifications, container, false)
        button = inflatedView.fragment_notifications_button
        imageAnim = inflatedView.fragment_notifications_image
        (imageAnim.drawable as AnimatedVectorDrawable).start()

        button.setOnClickListener {
            startAnimation()
        }
        return inflatedView
    }

    fun startAnimation() {
        if (widthSquare == 0) widthSquare = button.width
        if (heightSquare == 0) heightSquare = button.height
        val dp = resources.displayMetrics
        var valueAnimatorWidth: ValueAnimator
        var valueAnimatorHeight: ValueAnimator


        if (squareBig) {
            valueAnimatorWidth = ValueAnimator.ofInt(dp.widthPixels - (dp.density * 64).toInt(), widthSquare)
            valueAnimatorHeight = ValueAnimator.ofInt(dp.heightPixels - (dp.density * 256).toInt(), heightSquare)
            squareBig = false
        } else {
            valueAnimatorWidth = ValueAnimator.ofInt(widthSquare, dp.widthPixels - (dp.density * 64).toInt())
            valueAnimatorHeight = ValueAnimator.ofInt(heightSquare, dp.heightPixels - (dp.density * 256).toInt())
            squareBig = true
        }
        valueAnimatorHeight.addUpdateListener {
            button.height = valueAnimatorHeight.animatedValue as Int
            button.layoutParams.height = valueAnimatorHeight.animatedValue as Int
        }
        valueAnimatorWidth.addUpdateListener {
            button.width = valueAnimatorWidth.animatedValue as Int
            button.layoutParams.width = valueAnimatorWidth.animatedValue as Int
        }

        val animatorSet = AnimatorSet()
        animatorSet.duration = 700
        animatorSet.playSequentially(valueAnimatorHeight, valueAnimatorWidth)
        animatorSet.start()
    }

    fun onButtonPressed(fragment: Int) {
        listener?.onFragmentInteraction(fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragment: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
