package com.example.instagramapp

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener,
    GalleryFragment.OnFragmentInteractionListener, NotificationsFragment.OnFragmentInteractionListener,
    ChangeSceneFragment.OnFragmentInteractionListener {
    val homeFragment = HomeFragment()
    val galleryFragment = PagerFragment()
    val notificationsFragment = NotificationsFragment()
    val changeSceneFragment = ChangeSceneFragment()
    lateinit var buttonHome: ImageButton
    lateinit var buttonDashboard: ImageButton
    lateinit var buttonNotifications: ImageButton

    var lastFragment = 1
    lateinit var anim: Animation
    var fabIsOpen = false

    override fun onFragmentInteraction(currentFragment: Int) {
        changeFragment(currentFragment)
    }

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                changeFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeFragment(3)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_scenes -> {
                changeFragment(4)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(fragment: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, getCurrentFragment(fragment))
        fragmentTransaction.commit()
    }

    private fun getCurrentFragment(fragmentNumber: Int): Fragment {
        return when (fragmentNumber) {
            1 -> homeFragment
            2 -> galleryFragment
            3 -> notificationsFragment
            4 -> changeSceneFragment
            else -> homeFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        buttonHome = findViewById(R.id.activity_main2_button_home)
        buttonDashboard = findViewById(R.id.activity_main2_button_dashboard)
        buttonNotifications = findViewById(R.id.activity_main2_button_notify)



        buttonHome.setOnClickListener {
            closeFabPanel()
            changeFragment(1)
        }
        buttonDashboard.setOnClickListener {
            closeFabPanel()
            changeFragment(2)
        }
        buttonNotifications.setOnClickListener {
            closeFabPanel()
            changeFragment(3)
        }
        floatingActionButton.setOnClickListener {
            if (fabIsOpen) {
                anim = AnimationUtils.loadAnimation(this, R.anim.anim_close)
                fabIsOpen = false
            } else {
                anim = AnimationUtils.loadAnimation(this, R.anim.anim_open)
                fabIsOpen = true
            }
            buttonHome.startAnimation(anim)
            buttonDashboard.startAnimation(anim)
            buttonNotifications.startAnimation(anim)
        }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, homeFragment)
        fragmentTransaction.commit()
    }

    fun closeFabPanel() {
        anim = AnimationUtils.loadAnimation(this, R.anim.anim_close)
        fabIsOpen = false
        buttonHome.startAnimation(anim)
        buttonDashboard.startAnimation(anim)
        buttonNotifications.startAnimation(anim)
    }
}
