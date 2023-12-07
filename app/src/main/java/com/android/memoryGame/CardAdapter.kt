package com.android.memoryGame

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView


class CardAdapter(context: Context, cardModelArrayList: ArrayList<CardModel>, private val cardClickListener: CardClickListener) :
    ArrayAdapter<CardModel?>(context, 0, cardModelArrayList as List<CardModel?>) {

    interface CardClickListener {
        fun onCardClicked(position: Int);
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        }

        val cardModel: CardModel? = getItem(position)
        val courseIV = listItemView!!.findViewById<ImageView>(R.id.idIVcourse);

        if (cardModel != null && !cardModel.getIsHidden()) {
            courseIV.setImageResource(cardModel.getImageId());
            courseIV.scaleType = ImageView.ScaleType.FIT_CENTER;

            //Convert dp to px
            val width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                95F,
                context.resources.displayMetrics
            ).toInt()

            courseIV.layoutParams.width = width;
        }
        else if(cardModel != null && cardModel.getIsHidden()) {
            courseIV.setImageResource(R.drawable.background);
            courseIV.scaleType = ImageView.ScaleType.FIT_XY
            courseIV.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        if(cardModel != null && cardModel.getNotFound()) {
            listItemView.setBackgroundColor(Color.rgb(220, 14, 60));
        }

        listItemView.setOnClickListener {
            cardClickListener.onCardClicked(position);
        }

        return listItemView;
    }
}