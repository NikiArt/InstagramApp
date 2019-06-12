package com.example.instagramapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    val homeSet = ConstraintSet()
    val homeDetailSet = ConstraintSet()
    lateinit var constraintLayout: ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_home, container, false)
        val imageHome = inflatedView.fragment_home_image
        constraintLayout = inflatedView.fragment_home_root
        homeSet.clone(constraintLayout)
        homeDetailSet.clone(context, R.layout.fragment_home_details)

        imageHome.setOnClickListener {
            /* val set = TransitionSet()
             val changeBounds = ChangeBounds()
             changeBounds.pathMotion = ArcMotion()

             set.addTransition(Fade())
             set.addTransition(changeBounds)
             set.addTransition(Slide(Gravity.RIGHT))
             set.ordering = TransitionSet.ORDERING_TOGETHER
             set.duration = 500
             set.interpolator = AccelerateInterpolator()*/

            TransitionManager.beginDelayedTransition(constraintLayout)

            if (inflatedView.fragment_home_image_alfa_a.visibility == View.GONE) {

                homeSet.applyTo(constraintLayout)
            } else {
                homeDetailSet.applyTo(constraintLayout)
            }


        }

        return inflatedView
    }
}