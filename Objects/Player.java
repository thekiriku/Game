package com.example.game.Objects;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.game.GameDisplay;
import com.example.game.GameLoop;
import com.example.game.Graphics.Animator;
import com.example.game.Graphics.Sprite;
import com.example.game.Panels.Joystick;
import com.example.game.Panels.HealthBar;
import com.example.game.R;
import com.example.game.Utils;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 600.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 5;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;
    private Animator animator;
    private  PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator){
        super(context, ContextCompat.getColor(context, R.color.player),positionX,positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context,this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }


    public void update() {
        //Скорость
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        //Движение
        positionX += velocityX;
        positionY += velocityY;

        if(velocityX != 0 || velocityY != 0){
            double distance = Utils.getDistanceBetweenPoints(0,0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        playerState.update();
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        animator.draw(canvas, gameDisplay, this);
        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public  PlayerState getPlayerState(){
        return  playerState;
    }
}
