package com.example.instagramapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ToxicBakery.viewpager.transforms.DrawerTransformer
import com.example.instagramapp.adapters.MyPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class PagerFragment : Fragment() {
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activ = activity ?: return super.onCreateView(inflater, container, savedInstanceState)
        val inflatedView = inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewPager = inflatedView.fragment_view_pager_pager
        val pagerAdapter = MyPagerAdapter(activ.supportFragmentManager)
        pagerAdapter.addFragment(GalleryFragment(), "Gallery 1")
        pagerAdapter.addFragment(GalleryFragment(), "Gallery 2")
        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(true, DrawerTransformer())
        tabLayout = inflatedView.fragment_view_pager_tab
        tabLayout.setupWithViewPager(viewPager)
        return inflatedView
    }
}