package com.example.game;

import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.example.game.Objects.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameDisplayCoordinatesOffsetX;
    private double gameDisplayCoordinatesOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0,0,widthPixels,heightPixels);
        this.centerObject = centerObject;
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
        update();
    }

    public void update(){
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();
        gameDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    public double gameDisplayCoordinatesX(double x) {
        return x + gameDisplayCoordinatesOffsetX;
    }

    public double gameDisplayCoordinatesY(double y) {
        return y + gameDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int)gameCenterX - widthPixels/2,
                (int)gameCenterY - heightPixels/2,
                (int)gameCenterX + widthPixels/2,
                (int)gameCenterY + heightPixels/2
        );
    }
}
