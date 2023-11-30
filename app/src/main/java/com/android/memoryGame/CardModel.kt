package com.android.memoryGame

class CardModel (private var cardValue: Int, private var imageId: Int, private var isHidden: Boolean, private var notFound: Boolean){
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

    fun getIsHidden(): Boolean {
        return isHidden;
    }

    fun setIsHidden(isHidden: Boolean) {
        this.isHidden = isHidden;
    }

    fun setNotFound(notFound: Boolean) {

    }
}