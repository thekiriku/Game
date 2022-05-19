package com.example.game.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.Graphics.Sprite;
import com.example.game.Graphics.SpriteSheet;

public class TileTile extends Tile {
    private final Sprite sprite;

    public TileTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getTileSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
    }
}
