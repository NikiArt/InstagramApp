package com.example.instagramapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main2Activity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener,
    DashboardFragment.OnFragmentInteractionListener, NotificationsFragment.OnFragmentInteractionListener {
    val homeFragment = HomeFragment()
    val dashboardFragment = DashboardFragment()
    val notificationsFragment = NotificationsFragment()
    var lastFragment = 1

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
            2 -> dashboardFragment
            3 -> notificationsFragment
            else -> homeFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, homeFragment)
        fragmentTransaction.commit()
    }
}
