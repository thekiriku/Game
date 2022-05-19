package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.service.quicksettings.Tile;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.game.Graphics.Animator;
import com.example.game.Graphics.SpriteSheet;
import com.example.game.Map.TileMap;
import com.example.game.Objects.Circle;
import com.example.game.Objects.Enemy;
import com.example.game.Objects.Player;
import com.example.game.Objects.Spell;
import com.example.game.Panels.GameOver;
import com.example.game.Panels.Joystick;
import com.example.game.Panels.Performance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private final TileMap tileMap;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellist = new ArrayList<Spell>();
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;

    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);
        performance= new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        joystick = new Joystick(275, 700,70,40);
        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animator animator = new Animator(spriteSheet.getPlayerSpriteList());
        player = new Player(context, joystick, 1500,1500,300, animator);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        tileMap = new TileMap(spriteSheet);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()){
                    numberOfSpellsToCast++;
                }
                else if(joystick.isPressed((double) event.getX(), (double)event.getY())){
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }
                else {
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:

                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(), (double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tileMap.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);
        for(Enemy enemy:enemyList){
            enemy.draw(canvas, gameDisplay);
        }
        for(Spell spell:spellist){
            spell.draw(canvas, gameDisplay);
        }
        joystick.draw(canvas);
        performance.draw(canvas);
        if(player.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }
    }

    public void update() {

        if (player.getHealthPoints() <= 0){
            return;
        }

        joystick.update();
        player.update();
        if(Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(), player));
        }

        while(numberOfSpellsToCast > 0){
            spellist.add(new Spell(getContext(), player));
            numberOfSpellsToCast--;
        }
        for(Enemy enemy:enemyList){
            enemy.update();
        }

        for(Spell spell:spellist){
            spell.update();
        }
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while(iteratorEnemy.hasNext()){
            Circle enemy = iteratorEnemy.next();
            if(Circle.isColliding(enemy, player)){
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 1);
                continue;
            }
            Iterator<Spell> iteratorSpell = spellist.iterator();
            while (iteratorSpell.hasNext()){
                Circle spell = iteratorSpell.next();
                if(Circle.isColliding(spell, enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
