package com.android.memoryGame

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class GameScreen : AppCompatActivity() {

    private lateinit var gameGrid: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        val bundle = intent.extras
        val dimensions: IntArray? = bundle!!.getIntArray("dimension")

        gameGrid = findViewById(R.id.game_grid)
        gameGrid.numColumns = dimensions!!.elementAt(0)

        val courseModelArrayList: ArrayList<CardModel> = ArrayList<CardModel>()

        courseModelArrayList.add(CardModel(1, R.drawable.test))
        courseModelArrayList.add(CardModel(2, R.drawable.test))
        courseModelArrayList.add(CardModel(3, R.drawable.test))
        courseModelArrayList.add(CardModel(4, R.drawable.test))
        courseModelArrayList.add(CardModel(5, R.drawable.test))
        courseModelArrayList.add(CardModel(6, R.drawable.test))

        val adapter = CardAdapter(this, courseModelArrayList)
        gameGrid.adapter = adapter
    }
}