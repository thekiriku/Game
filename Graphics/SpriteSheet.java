package com.example.game.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.game.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 300;
    private static final int SPRITE_HEIGHT_PIXELS = 300;
    private Bitmap bitmap;
    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.game, bitmapOptions);
    }
    public Sprite[] getPlayerSpriteList(){
        Sprite[] spriteList = new Sprite[3];
        spriteList[0] = new Sprite(this, new Rect(0,0,300,300));
        spriteList[1] = new Sprite(this, new Rect(300,0,600,300));
        spriteList[2] = new Sprite(this, new Rect(600,0,900,300));
        return spriteList;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getTileSprite() {
        return getSpriteByIndex(1,0);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1,1);
    }

    public Sprite getBookSprite() {
        return getSpriteByIndex(1,2);
    }

    private Sprite getSpriteByIndex(int idRow, int idCol) {
        return new Sprite(this, new Rect(idCol*SPRITE_WIDTH_PIXELS,
                idRow*SPRITE_HEIGHT_PIXELS,
                (idCol+1)*SPRITE_WIDTH_PIXELS,
                (idRow+1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}
