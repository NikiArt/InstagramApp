package com.example.instagramapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var buttonApply: Button
    lateinit var shake: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        login = findViewById(R.id.content_main_login)
        password = findViewById(R.id.content_main_password)
        buttonApply = findViewById(R.id.content_main_button_apply)
        shake = TranslateAnimation(-10f, 10f, 0f, 0f)
        shake.duration = 10
        shake.repeatCount = 5

        buttonApply.setOnClickListener {
            applyActivityValues()
        }


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun applyActivityValues() {
        if (login.text.isEmpty()) {
            Toast.makeText(baseContext, "не заполнено поле 'логин'", Toast.LENGTH_SHORT).show()
            buttonApply.startAnimation(shake)
            return
        }
        if (password.text.isEmpty()) {
            Toast.makeText(baseContext, "не заполнено поле 'пароль'", Toast.LENGTH_SHORT).show()
            buttonApply.startAnimation(shake)
            return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
