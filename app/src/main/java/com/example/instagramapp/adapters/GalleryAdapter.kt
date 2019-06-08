package com.example.instagramapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.Image
import com.example.instagramapp.R
import com.example.instagramapp.uiservices.MySimpleAdapter
import kotlinx.android.synthetic.main.item_photo_gallery.view.*
import kotlinx.android.synthetic.main.item_photo_gallery_center_big.view.*
import java.util.*
import kotlin.collections.ArrayList

class GalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MySimpleAdapter {
    var imageList = ArrayList<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        0 -> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_gallery, parent, false)
            ViewHolderGallery(view)
        }
        1 -> {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_photo_gallery_center_big, parent, false)
            ViewHolderGalleryVenterBig(view)
        }
        else -> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_gallery, parent, false)
            ViewHolderGallery(view)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderGallery -> {
                holder.photo.setImageResource(R.drawable.photo_man_img)
                holder.namePhoto.text = imageList[position].name
                holder.datePhoto.text = imageList[position].date
                holder.sizePhoto.text = imageList[position].size
            }
            is ViewHolderGalleryVenterBig -> holder.photo.setImageResource(R.drawable.photo_girl)
        }
        holder.itemView.tag = position
    }

    override fun getItemViewType(position: Int): Int {
        return imageList[position].priority
    }

    class ViewHolderGallery(view: View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView = view.item_photo_gallery_photo
        var namePhoto: TextView = view.item_photo_gallery_name
        var sizePhoto: TextView = view.item_photo_gallery_size
        var datePhoto: TextView = view.item_photo_gallery_date
    }

    class ViewHolderGalleryVenterBig(view: View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView = view.item_photo_gallery_center_big_photo

    }

    override fun onItemDelete(position: Int) {
        imageList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(imageList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

}