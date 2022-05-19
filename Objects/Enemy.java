package com.example.game.Objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.game.GameLoop;
import com.example.game.R;

public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private static final double SPAWNS_PEW_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PEW_MINUTE/60;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updateUntillNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
    }

    public Enemy(Context context, Player player) {
        super(context, ContextCompat.getColor(context, R.color.enemy),
                Math.random()*1000, Math.random()*1000, 30);
        this.player = player;
    }

    public static boolean readyToSpawn() {
        if(updateUntillNextSpawn <= 0){
            updateUntillNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }
        else{
            updateUntillNextSpawn --;
            return false;
        }
    }

    @Override
    public void update() {
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        if(distanceToPlayer > 0){
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        }
        else{
            velocityX = 0;
            velocityY = 0;
        }
        positionX += velocityX;
        positionY += velocityY;
    }
}
