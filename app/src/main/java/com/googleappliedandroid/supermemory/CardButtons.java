package com.googleappliedandroid.supermemory;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by andyliang on 5/12/17.
 */

public class CardButtons extends android.support.v7.widget.AppCompatImageButton {
    private Card card;
    public CardButtons(Context context){
        super(context);
    }

    public CardButtons(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public CardButtons(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
    }
    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return card;
    }

}
