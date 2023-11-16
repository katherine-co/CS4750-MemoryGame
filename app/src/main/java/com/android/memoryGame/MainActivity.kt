package com.android.memoryGame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.memoryGame.R

import android.content.Intent
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity




class MainActivity : AppCompatActivity() {

    private lateinit var b1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById(R.id.game_button)
        b1.setOnClickListener {
            val i = Intent(this,GameScreen::class.java)
            startActivity(i)
        }
    }
}