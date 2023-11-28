package com.android.memoryGame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


private val ICONS = listOf(
    R.drawable.c1,
    R.drawable.c2,
    R.drawable.c3,
    R.drawable.c4,
    R.drawable.c5,
    R.drawable.c6,
    R.drawable.c7,
    R.drawable.c8,
    R.drawable.c9,
    R.drawable.c10,
    R.drawable.c11,
    R.drawable.c12
)

private val cardModelArrayList: ArrayList<CardModel> = ArrayList<CardModel>()
private lateinit var adapter: CardAdapter;
class GameScreen : AppCompatActivity() {

    private lateinit var gameGrid: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        val bundle = intent.extras
        val dimensions: IntArray? = bundle!!.getIntArray("dimension")

        gameGrid = findViewById(R.id.game_grid)
        gameGrid.numColumns = dimensions!!.elementAt(0)



        val numPairs = dimensions!!.elementAt(0) * dimensions!!.elementAt(1) / 2
        for(i in 0 until numPairs) {
            cardModelArrayList.add(CardModel(i, ICONS.get(i), true))
            cardModelArrayList.add(CardModel(i, ICONS.get(i), true))
        }
        cardModelArrayList.shuffle();

        adapter = CardAdapter(this, cardModelArrayList, object: CardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                cardClicked(position)
                Log.d("Card Clicked", position.toString());
            }
        })
        gameGrid.adapter = adapter
    }
    fun cardClicked(position: Int) {
        cardModelArrayList.get(position).setIsHidden(false);
        adapter.notifyDataSetChanged();

    }
}