package com.example.instagramapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.Scene
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.fragment_change_scenes.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChangeSceneFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var button: Button
    lateinit var firstSceneButton: Button
    lateinit var secondSceneButton: Button
    lateinit var secondScene: Scene
    lateinit var firstScene: Scene
    lateinit var root: ViewGroup
    var isFirstScene = true


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
        val inflatedView = inflater.inflate(R.layout.fragment_change_scenes, container, false)
        root = inflatedView.fragment_change_scenes_root
        secondScene = Scene.getSceneForLayout(root, R.layout.scene2, context as Context)
        firstScene = Scene.getSceneForLayout(root, R.layout.scene2, context as Context)
/*        secondSceneButton = inflatedView.scene2_button
        firstSceneButton = inflatedView.scene1_button*/
        button = inflatedView.fragment_change_scenes_button

        button.setOnClickListener {
            changeScene()
        }

        /*firstSceneButton.setOnClickListener {
            changeScene()
        }*/

        return inflatedView
    }

    private fun changeScene() {
        val set = TransitionSet()
        set.addTransition(ChangeBounds())
        set.ordering = TransitionSet.ORDERING_SEQUENTIAL
        set.duration = 2000
        set.interpolator = OvershootInterpolator(1.5f)
        TransitionManager.go(secondScene, set)
        //TransitionManager.go(if (isFirst) secondScene else firstScene, set)


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
