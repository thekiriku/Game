package com.example.game.Graphics;

import android.graphics.Canvas;

import com.example.game.GameDisplay;
import com.example.game.Objects.Player;
import com.example.game.Objects.PlayerState;

public class Animator {
    private Sprite[] playerSpriteList;
    private int idNotMovingFrame = 0;
    private int idMovingFrame = 1;
    private int updatesBeforeNextMoveFrame;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE = 10;

    public Animator(Sprite[] playerSpriteList) {
        this.playerSpriteList = playerSpriteList;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()){
                case NOT_MOVING:
                    drawFrame(canvas, gameDisplay, player, playerSpriteList[idNotMovingFrame]);
                    break;
                case START_MOVING:
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE;
                    drawFrame(canvas, gameDisplay, player, playerSpriteList[idMovingFrame]);
                    break;
                case IS_MOVING:
                    updatesBeforeNextMoveFrame--;
                    if(updatesBeforeNextMoveFrame == 0){
                        updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE;
                        toggleMovingFrame();
                    }
                    drawFrame(canvas, gameDisplay, player, playerSpriteList[idMovingFrame]);
                    break;
                default:
                    break;
        }
    }

    private void toggleMovingFrame() {
        if(idMovingFrame == 1) idMovingFrame = 2;
        else idMovingFrame = 1;
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(canvas,
                (int)gameDisplay.gameDisplayCoordinatesX(player.getPositionX()) - 180,
                (int)gameDisplay.gameDisplayCoordinatesY(player.getPositionY()) - 30);
    }
}
