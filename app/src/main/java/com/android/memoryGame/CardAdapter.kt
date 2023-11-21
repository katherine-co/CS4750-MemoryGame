package com.android.memoryGame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CardAdapter(context: Context, cardModelArrayList: ArrayList<CardModel>) :
    ArrayAdapter<CardModel?>(context, 0, cardModelArrayList as List<CardModel?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        }

        val cardModel: CardModel? = getItem(position)
        val courseTV = listitemView!!.findViewById<TextView>(R.id.idTVCourse)
        val courseIV = listitemView.findViewById<ImageView>(R.id.idIVcourse)

        if (cardModel != null) {
            courseTV.setText(cardModel.getCardValue().toString())
            courseIV.setImageResource(cardModel.getImageId())
        }
        return listitemView
    }
}