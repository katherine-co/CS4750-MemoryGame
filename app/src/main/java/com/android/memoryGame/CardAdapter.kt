package com.android.memoryGame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CardAdapter(context: Context, cardModelArrayList: ArrayList<CardModel>, private val cardClickListener: CardClickListener) :
    ArrayAdapter<CardModel?>(context, 0, cardModelArrayList as List<CardModel?>) {

    interface CardClickListener {
        fun onCardClicked(position: Int)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        }

        val cardModel: CardModel? = getItem(position)
        val courseIV = listItemView!!.findViewById<ImageView>(R.id.idIVcourse)

        if (cardModel != null && !cardModel.getIsHidden()) {
            courseIV.setImageResource(cardModel.getImageId())
        }
        else if(cardModel != null && cardModel.getIsHidden()) {
            courseIV.setImageResource(R.drawable.ic_launcher_background)
        }

        listItemView.setOnClickListener {
            cardClickListener.onCardClicked(position);
        }

        return listItemView
    }
}