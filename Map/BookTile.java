package com.example.game.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.Graphics.Sprite;
import com.example.game.Graphics.SpriteSheet;

public class BookTile extends Tile {
    private final Sprite sprite;

    public BookTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getBookSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
    }
}
