package com.example.game.Objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.GameDisplay;

public abstract class   Circle extends GameObject {
    protected double radius;
    protected Paint paint;
    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceCollision = obj1.getRadius() + obj2.getRadius();
        if(distance < distanceCollision) return true;
        else return false;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle((float)gameDisplay.gameDisplayCoordinatesX(positionX),
                (float)gameDisplay.gameDisplayCoordinatesY(positionY),
                (float)radius,
                paint);
    }
}
