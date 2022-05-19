package com.example.game.Panels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game.GameDisplay;
import com.example.game.Objects.Player;
import com.example.game.R;

public class HealthBar {
    private Player player;
    private int width, height, margin;
    private Paint borderPaint, healthPaint;

    public HealthBar(Context context, Player player){
        this.player = player;
        this.width = 200;
        this.height = 30;
        this.margin = 2;
        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);
        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBar);
        healthPaint.setColor(healthColor);
    }
    public void draw(Canvas canvas, GameDisplay gameDisplay){
        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 30;
        float healthPointPercentage = (float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;

        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;
        canvas.drawRect((float) gameDisplay.gameDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.gameDisplayCoordinatesY(borderTop),
                (float) gameDisplay.gameDisplayCoordinatesX(borderRight),
                (float) gameDisplay.gameDisplayCoordinatesY(borderBottom),
                borderPaint);

        float healthLeft,healthTop,healthRight,healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2*margin;
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth*healthPointPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;

        canvas.drawRect((float) gameDisplay.gameDisplayCoordinatesX(healthLeft),
                (float) gameDisplay.gameDisplayCoordinatesY(healthTop),
                (float) gameDisplay.gameDisplayCoordinatesX(healthRight),
                (float) gameDisplay.gameDisplayCoordinatesY(healthBottom),
                healthPaint);
    }
}
