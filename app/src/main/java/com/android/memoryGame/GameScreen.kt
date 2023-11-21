package com.android.memoryGame

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


private val ICONS = listOf(
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test,
    R.drawable.test
)
class GameScreen : AppCompatActivity() {

    private lateinit var gameGrid: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        val bundle = intent.extras
        val dimensions: IntArray? = bundle!!.getIntArray("dimension")

        gameGrid = findViewById(R.id.game_grid)
        gameGrid.numColumns = dimensions!!.elementAt(0)

        val cardModelArrayList: ArrayList<CardModel> = ArrayList<CardModel>()

        val numPairs = dimensions!!.elementAt(0) * dimensions!!.elementAt(1) / 2
        for(i in 0 until numPairs) {
            cardModelArrayList.add(CardModel(i, ICONS.get(i)))
            cardModelArrayList.add(CardModel(i, ICONS.get(i)))
        }
        cardModelArrayList.shuffle();

        val adapter = CardAdapter(this, cardModelArrayList)
        gameGrid.adapter = adapter
    }
}