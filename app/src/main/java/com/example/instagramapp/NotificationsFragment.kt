package com.example.instagramapp

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_notifications.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var blackView: View
    lateinit var button: Button
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
        val inflaterView = inflater.inflate(R.layout.fragment_notifications, container, false)
        blackView = inflaterView.fragment_notifications_black_square
        button = inflaterView.fragment_notifications_button

        button.setOnClickListener {
            button.layoutParams.width = 1000
            //startAnimation()
        }
        blackView.setOnClickListener {
            startAnimation()
        }
        return inflaterView
    }

    fun startAnimation() {
        /*if (widthSquare == 0) widthSquare = button.width
        if (heightSquare == 0) heightSquare = button.height*/
        val dp = resources.displayMetrics
        var valueAnimatorWidth = ValueAnimator.ofInt(1, 200)
        var valueAnimatorHeight = ValueAnimator.ofInt(1, 200)


        button.layoutParams.height = 1000
        button.layoutParams.width = 1000

        /*if (squareBig) {
            valueAnimatorWidth = ValueAnimator.ofInt(dp.widthPixels - 64, widthSquare)
            valueAnimatorHeight = ValueAnimator.ofInt(dp.heightPixels - 128, heightSquare)
            blackView.layoutParams.height = heightSquare
            blackView.layoutParams.width = widthSquare
        } else {
            valueAnimatorWidth = ValueAnimator.ofInt(widthSquare, dp.widthPixels - 64)
            valueAnimatorHeight = ValueAnimator.ofInt(heightSquare, dp.heightPixels - 128)
            blackView.layoutParams.height = dp.widthPixels - 64
            blackView.layoutParams.width = dp.heightPixels - 128
        }
        valueAnimatorHeight.addUpdateListener {
            blackView.layoutParams.height = valueAnimatorHeight.animatedValue as Int
        }
        valueAnimatorWidth.addUpdateListener {
            blackView.layoutParams.width = valueAnimatorWidth.animatedValue as Int
        }

        valueAnimatorHeight.start()
        valueAnimatorWidth.start()*/
        /*val animatorSet = AnimatorSet()
        animatorSet.duration = 1500
        animatorSet.playSequentially(valueAnimatorHeight, valueAnimatorWidth)
        animatorSet.start()*/
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
