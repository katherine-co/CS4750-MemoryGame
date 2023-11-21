package com.android.memoryGame

class CardModel (private var cardValue: Int, private var imageId: Int){
    fun getCardValue(): Int {
        return cardValue;
    }

    fun setCardValue(cardValue: Int) {
        this.cardValue = cardValue
    }

    fun getImageId(): Int {
        return imageId
    }

    fun setImageId(imageId: Int) {
        this.imageId = imageId
    }
}