package com.example.game.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.Graphics.Sprite;
import com.example.game.Graphics.SpriteSheet;

public class GroundTile extends Tile {
    private final Sprite sprite;

    public GroundTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getGroundSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
    }
}
