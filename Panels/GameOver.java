package com.example.game.Panels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game.R;

public class GameOver {
    private Context context;

    public GameOver(Context context){
        this.context = context;
    }

    public void draw(Canvas canvas){
        String text = "Игра Окончена";
        float x = 800;
        float y = 200;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 100;
        paint.setTextSize(textSize);
        canvas.drawText(text,x,y,paint);
    }
}
