package com.android.memoryGame

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class GameScreen : AppCompatActivity() {

    private lateinit var gameGrid: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        gameGrid = findViewById(R.id.game_grid)
//        val arrayList : ArrayList<String> = ArrayList<String>()
//
//        arrayList.add("1")
//        arrayList.add("2")
//        arrayList.add("3")
//        arrayList.add("4")
//        arrayList.add("5")

//        val adapter =
//        gameGrid.adapter =
    }
}