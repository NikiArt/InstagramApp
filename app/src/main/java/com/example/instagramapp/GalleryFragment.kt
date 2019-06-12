package com.example.instagramapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.instagramapp.adapters.GalleryAdapter
import com.example.instagramapp.uiservices.MySimpleCallback
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GalleryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val imageGalleryAdapter = GalleryAdapter()

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
        val inflatedView = inflater.inflate(R.layout.fragment_gallery, container, false)
        val galleryList = inflatedView.fragment_dashboard_gallery_list
        galleryList.layoutManager = LinearLayoutManager(context)
        galleryList.adapter = imageGalleryAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(galleryList)

        val itemTouchHelper = ItemTouchHelper(MySimpleCallback(imageGalleryAdapter))
        itemTouchHelper.attachToRecyclerView(galleryList)


        imageGalleryAdapter.imageList.clear()
        for (i in 1..9) {
            val sdf = SimpleDateFormat("YYYY-MM-dd HH:mm")
            val rand = Math.random()
            val priority = ((rand * 10) % 2).toInt()
            val image = Image(
                if (priority == 0) 0 else 1,
                "Image$i",
                "${(rand * 1000).toInt()} Kb",
                sdf.format(Date((rand * 1000000000000).toLong()))
            )


            imageGalleryAdapter.imageList.add(image)
        }

        return inflatedView
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
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
