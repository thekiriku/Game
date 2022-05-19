package com.example.game.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.GameDisplay;
import com.example.game.Graphics.SpriteSheet;

import static com.example.game.Map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.game.Map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.game.Map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.game.Map.MapLayout.TILE_WIDTH_PIXELS;

public class TileMap {
    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public TileMap(SpriteSheet spriteSheet){
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTileMap();
    }

    private void initializeTileMap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];
        for(int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++){
            for(int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++){
                tilemap[iRow][iCol] = Tile.getTile(layout[iRow][iCol],spriteSheet,getRectByIndex(iRow, iCol));
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                NUMBER_OF_COLUMN_TILES*TILE_WIDTH_PIXELS,
                NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS,
                config
        );
        Canvas mapCanvas = new Canvas(mapBitmap);
        for(int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++){
            for(int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++){
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }

    }

    private Rect getRectByIndex(int idRow, int idCol) {
        return new Rect(
                idCol*TILE_WIDTH_PIXELS,
                idRow*TILE_HEIGHT_PIXELS,
                (idCol+1)*TILE_WIDTH_PIXELS,
                (idRow+1)*TILE_HEIGHT_PIXELS
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.DISPLAY_RECT,
                null
        );
    }
}
