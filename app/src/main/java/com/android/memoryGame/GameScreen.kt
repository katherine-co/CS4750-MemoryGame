package com.android.memoryGame

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
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
);

private val cardModelArrayList: ArrayList<CardModel> = ArrayList<CardModel>();
private lateinit var adapter: CardAdapter;
class GameScreen : AppCompatActivity() {
    private lateinit var gameGrid: GridView;
    private lateinit var counter: TextView;
    private lateinit var highScore: TextView;
    private var previousCardPosition: Int = -1;
    private var numMatches: Int = 0; //score counter
    private var numPairs: Int = 0;
    private var numComparisons: Int = 0;
    private lateinit var exitButton: Button;
    private var wait: Boolean = false;
    private var click: Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        cardModelArrayList.clear();

        val bundle = intent.extras;


        val dimensions: IntArray? = bundle!!.getIntArray("dimension");

        counter = findViewById(R.id.counter);
        highScore = findViewById(R.id.highscore);
        highScore.text = "High Score: ${getExistingHighscore()}";
        gameGrid = findViewById(R.id.game_grid);
        gameGrid.numColumns = dimensions!!.elementAt(0);
        exitButton = findViewById(R.id.exitGame);

        exitButton.setOnClickListener {
            buttonClicked();
        }

        numPairs = dimensions!!.elementAt(0) * dimensions!!.elementAt(1) / 2
        for(i in 0 until numPairs) {
            cardModelArrayList.add(CardModel(i, ICONS.get(i), true, false));
            cardModelArrayList.add(CardModel(i, ICONS.get(i), true, false));
        }
        cardModelArrayList.shuffle();

        adapter = CardAdapter(this, cardModelArrayList, object: CardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                if(!wait) {
                    cardClicked(position)
                    Log.d("Card Clicked", position.toString());
                }
            }
        })
        gameGrid.adapter = adapter;
    }




    fun cardClicked(position: Int) {
        var card = cardModelArrayList.get(position);

        // If card has already been matched
        if(!card.getIsHidden()) {
            return;
        }

        flipCard(position);

        // Check if there is a card to match it to
        if (previousCardPosition == -1) {
            previousCardPosition = position;
            return;
        }

        var previousCard = cardModelArrayList.get(previousCardPosition);
        numComparisons++;
        counter.text = "Comparisons: $numComparisons";

        //Check if the cards match
        if(previousCard.getCardValue() == card.getCardValue()) {
            Log.d("Card Clicked", "Match: $numMatches");
            numMatches++;
            previousCardPosition = -1;

            // Check if all cards have been found
            if(numMatches == numPairs) {
                Log.d("Card Clicked", "All cards have been found");
                click = 1;
                updateHighscore(numComparisons)
                val toast = Toast.makeText(this, "You Win", Toast.LENGTH_SHORT) // in Activity
                toast.show()
            }
            return;
        }
        wait = true;

        // Flip cards back over if cards do not match
        Handler().postDelayed({
            flipCard(previousCardPosition);
            flipCard(position);
            previousCardPosition = -1;
            wait = false;
        }, 1000) // Flip cards back over after 1 second
    }
    fun flipCard(position: Int) {
        var card = cardModelArrayList.get(position);
        card.setIsHidden(!card.getIsHidden());
        adapter.notifyDataSetChanged();
    }

    fun buttonClicked() {
        if(wait) {
            return;
        }
        if (click == 0) {
            click = 1;
            showCards();
        }
        else {
            finish();
            click = 0;
        }
    }

    fun showCards() {
        if(previousCardPosition != -1) {
            cardModelArrayList[previousCardPosition].setNotFound(true);
        }
        for(i in 0 until cardModelArrayList.size) {
            if (cardModelArrayList[i].getIsHidden() == true) {
                cardModelArrayList[i].setIsHidden(false);
                cardModelArrayList[i].setNotFound(true);
            }
        }
        adapter.notifyDataSetChanged();
    }



    //return highscore based off the difficulty
    fun getExistingHighscore(): Int {
        // Retrieve difficulty from Intent
        var sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var difficulty = intent.getStringExtra("difficulty") ?: "easy"
        Log.d("difficulty", difficulty);
        return when (difficulty) {
            "easy" -> sharedPreferences.getInt("highscoreEasy", 999)
            "medium" -> sharedPreferences.getInt("highscoreMedium", 999)
            "hard" -> sharedPreferences.getInt("highscoreHard", 999)
            else -> 0

        }
    }

    fun updateHighscore(currentScore: Int) {
        var sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var difficulty = intent.getStringExtra("difficulty") ?: "easy"
        // Retrieve the existing highscore for the specified difficulty
        val existingHighscore = getExistingHighscore()
        Log.d("existing high score:", "$existingHighscore");
        // Check if the new highscore is greater than the existing highscore
        if (currentScore < existingHighscore) {
            // Create highscore achieved message
            val toast = Toast.makeText(this, "New Highscore!", Toast.LENGTH_SHORT) // in Activity
            toast.show()
            // Update the highscore for the specified difficulty
            with(sharedPreferences.edit()) {
                when (difficulty) {
                    "easy" -> putInt("highscoreEasy", currentScore)
                    "medium" -> putInt("highscoreMedium", currentScore)
                    "hard" -> putInt("highscoreHard", currentScore)
                }
                apply()
            }
        }
    }


}