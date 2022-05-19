package com.example.game.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.Graphics.SpriteSheet;

abstract class Tile {
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType{
        TILE_TILE,
        GROUND_TILE,
        BOOK_TILE
    }
    public static Tile getTile(int idTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idTileType]){

            case TILE_TILE:
                return new TileTile(spriteSheet, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            case BOOK_TILE:
                return new BookTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
