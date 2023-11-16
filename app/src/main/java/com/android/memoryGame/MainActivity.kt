package com.android.memoryGame

import androidx.appcompat.app.AppCompatActivity
import com.android.memoryGame.R

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity




class MainActivity : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easyButton = findViewById(R.id.easy)
        mediumButton = findViewById(R.id.medium)
        hardButton = findViewById(R.id.hard)

        easyButton.setOnClickListener {
            launchGameScreen(intArrayOf(3, 4))
        }
        mediumButton.setOnClickListener {
            launchGameScreen(intArrayOf(3, 6))
        }
        hardButton.setOnClickListener {
            launchGameScreen(intArrayOf(4, 6))
        }
    }

    fun launchGameScreen(dimensions: IntArray) {
        val i = Intent(this,GameScreen::class.java)

        var bundle = Bundle()
        //Add your data to bundle
        bundle.putIntArray("dimension", dimensions);

        //Add the bundle to the intent
        i.putExtras(bundle);

        startActivity(i)
    }
}